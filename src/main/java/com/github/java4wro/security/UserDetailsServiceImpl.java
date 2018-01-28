package com.github.java4wro.security;

import com.github.java4wro.user.UserRepository;
import com.github.java4wro.user.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Getter
@Setter
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email){

        User user = userRepository.findOneByEmail(email);

        return new UserDetailsImpl(user.getEmail(),user.getPassword(),user.isEnabled(),user.getRole());
    }
}