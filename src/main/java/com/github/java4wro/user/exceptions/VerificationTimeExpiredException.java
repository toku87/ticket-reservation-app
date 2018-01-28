package com.github.java4wro.user.exceptions;

import lombok.Getter;

import java.util.Date;

@Getter
public class VerificationTimeExpiredException extends RuntimeException {

    private String email;
    private Date expiredData;

    public VerificationTimeExpiredException(String email, Date expiredData) {
        this.email = email;
        this.expiredData = expiredData;
    }
}
