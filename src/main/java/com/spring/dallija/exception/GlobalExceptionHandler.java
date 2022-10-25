package com.spring.dallija.exception;

import com.spring.dallija.exception.user.DuplicateEmailException;
import com.spring.dallija.exception.user.UserNotMatchPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.spring.dallija.api")
public class GlobalExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleNotMatchPasswordException(UserNotMatchPasswordException e){
        log.debug("not match password :: {}" ,e.getMessage());
        return new ResponseEntity(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException e){
        log.debug("이메일 중복",e);
        return new ResponseEntity( new ErrorResponse(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleDuplicateEmailException(MethodArgumentNotValidException e){
        log.debug(e.getFieldError().getDefaultMessage(), e);
        return new ResponseEntity( new ErrorResponse(e.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

}
