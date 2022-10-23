package com.spring.dallija.exception.user;

public class UserNotMatchPasswordException extends RuntimeException{

    public UserNotMatchPasswordException(String message){
        super(message);
    }
}
