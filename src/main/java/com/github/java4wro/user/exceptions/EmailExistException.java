package com.github.java4wro.user.exceptions;

import lombok.Getter;

@Getter
public class EmailExistException extends RuntimeException {

    private String email;

    public EmailExistException(String email) {
        this.email = email;
    }
}