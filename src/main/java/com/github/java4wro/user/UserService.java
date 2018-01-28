package com.github.java4wro.user;

import com.github.java4wro.user.dto.RegisterUserDTO;
import com.github.java4wro.user.dto.UserDTO;

import java.util.List;


public interface UserService {

    UserDTO findUserbyEmail(String userEmail);
    RegisterUserDTO addUser(RegisterUserDTO registerUserDTO);

    List<UserDTO> getAll();

    void confirmRegistration(String token);

    void sendEmailWhenForgotPassword(String email, String newPassword, String confirmNewPassword);

    void changePasswordsWhenForgot (String token);


    void validationOfPasswordIdenitiy(String password, String confiremPassword);
}
