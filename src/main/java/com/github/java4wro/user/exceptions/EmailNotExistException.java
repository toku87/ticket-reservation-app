package com.github.java4wro.user.exceptions;

import lombok.Getter;

@Getter
public class EmailNotExistException extends RuntimeException {

    private String email;

    public EmailNotExistException(String email) {
        this.email = email;
    }
}