package com.github.java4wro.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(EmailExistException.class)
    @ResponseBody
    public ResponseEntity<String> handleEmailExistException (EmailExistException e){
        return new ResponseEntity<>
                (String.format("User with this email: %s already exists", e.getEmail()),
                        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotExistException.class)
    @ResponseBody
    public ResponseEntity<String> handleEmailExistException (EmailNotExistException e){
        return new ResponseEntity<>
                (String.format("User with this email: %s does not exist", e.getEmail()),
                        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VerificationTimeExpiredException.class)
    @ResponseBody
    public ResponseEntity<String> handleEmailExistException (VerificationTimeExpiredException e){
        return new ResponseEntity<>
                (String.format("User with this email: %s has expired at %s", e.getEmail(),e.getExpiredData().toString()),
                        HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler (NotIdenticalPasswordException.class)
    @ResponseBody
    public ResponseEntity<String> handleNotIdenticalPasswordException (NotIdenticalPasswordException e) {
        return new ResponseEntity<>
                (String.format("Password and confirmed password are not identical"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DifferentPasswordException.class)
    @ResponseBody
    public ResponseEntity<String> handleDifferentPasswordException (DifferentPasswordException e){
        return new ResponseEntity<>
                (String.format("The passwords are different!"),
                        HttpStatus.BAD_REQUEST);
    }

}
