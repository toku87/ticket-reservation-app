package com.github.java4wro.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO {

    private String email;
    private String password;
    private String confirmedPassword;

}
