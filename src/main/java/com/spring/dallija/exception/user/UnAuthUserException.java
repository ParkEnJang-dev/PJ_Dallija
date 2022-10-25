package com.spring.dallija.exception.user;

public class UnAuthUserException extends RuntimeException{
    public UnAuthUserException(String message) {
        super(message);
    }
}
