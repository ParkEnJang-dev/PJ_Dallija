package com.spring.dallija.exception.user;

public class DuplicateEmailException extends IllegalArgumentException{

    public DuplicateEmailException(String s) {
        super(s);
    }
}
