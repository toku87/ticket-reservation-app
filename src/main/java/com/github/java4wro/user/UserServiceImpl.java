package com.github.java4wro.user;

import com.github.java4wro.emailService.EmailSender;
import com.github.java4wro.user.dto.RegisterUserDTO;
import com.github.java4wro.user.dto.UserDTO;
import com.github.java4wro.user.exceptions.*;
import com.github.java4wro.user.model.User;
import com.github.java4wro.user.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterUserMapperDTO registerUserMapperDTO;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailSender emailSender;


    @Override
    public UserDTO findUserbyEmail(String userEmail) {
        User user = userRepository.findOneByEmail(userEmail);

        if (user == null) {
            throw new EmailNotExistException(userEmail);
        }
        return userMapper.toUserDTO(user);
    }

    @Override
    public RegisterUserDTO addUser(RegisterUserDTO registerUserDTO)  {

        if (userRepository.existsByEmail(registerUserDTO.getEmail())) {
            throw new EmailExistException(registerUserDTO.getEmail());
        }

        User user = new User();
//        validationOfPasswordIdenitiy(registerUserDTO.getPassword(), registerUserDTO.getConfirmedPassword())
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setEmail(registerUserDTO.getEmail());
        user.setEnabled(false);
        user.setRole(UserRole.USER);

        sendEmailConfirmRegistration(user.getEmail(), user.getUuid());
        System.out.println(user.getRole().name());

        return registerUserMapperDTO.toRegistrationUserDTO(userRepository.save(user));
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> userList=userRepository.findAll();
        return userMapper.toUserDTO(userList);
    }

    @Override
    public void confirmRegistration(String token) {
        User user=userRepository.findOneByUuid(token);

        if (user!=null){
            Date now = new Date();
            Date expiryDate = user.getExpiryDate();

            if (expiryDate.compareTo(now) > 0) user.setEnabled(true);
            else throw new VerificationTimeExpiredException(user.getEmail(), expiryDate);
        }

        userRepository.save(user);
    }

    @Override
    public void sendEmailWhenForgotPassword(String email, String newPassword, String confirmNewPassword) {
//        User user = userRepository.findOneByEmail(email);
        if (!userRepository.existsByEmail(email)) {
            throw new EmailExistException(email);
        }
        if (!newPassword.equals(confirmNewPassword)) {
            throw new DifferentPasswordException();
        }
        User user = userRepository.findOneByUuid(email);

        user.setNewPassword(newPassword);
        userRepository.save(user);
        sendEmailForgotPassword(user.getEmail(), user.getUuid());

    }

    @Override
    public void changePasswordsWhenForgot(String token) {
        User user = userRepository.findOneByUuid(token);

        user.setPassword(passwordEncoder.encode(user.getNewPassword()));
        user.setNewPassword(null);

        userRepository.save(user);
    }


    private void sendEmailConfirmRegistration(String to, String token) {
        String content = "http://localhost:8099//api/users/confirmRegistration?token=" + token;
        String subject = "Confirm registration";

        emailSender.sendEmail(to,subject,content);
    }

    @Scheduled(cron ="* * 17-21 * * *")
    private void deleteUnconfirmedUsers(){
        Date dayAgo=new Date(new Date().getTime()- TimeUnit.DAYS.toMillis(1));
        List<User> users=userRepository.findAllByEnabledAndCreatedAtBefore(false,dayAgo);
        userRepository.delete(users);
    }
    @Override
    public void validationOfPasswordIdenitiy(String password, String confiremPassword){
        if (!password.equals(confiremPassword)){
        throw  new NotIdenticalPasswordException();
        }
    }
    private void sendEmailForgotPassword(String to, String token) {
        String content = "http://localhost:8099//api/users/forgotPassword?token=" + token;
        String subject = "Changing password";
        emailSender.sendEmail(to, subject, content);
    }
}
