package com.spring.dallija.exception;

import com.spring.dallija.exception.cart.CartNotFoundException;
import com.spring.dallija.exception.category.DuplicateCategoryException;
import com.spring.dallija.exception.category.NotFoundCategoryException;
import com.spring.dallija.exception.item.ItemNotFoundException;
import com.spring.dallija.exception.order.NotEnoughStockException;
import com.spring.dallija.exception.order.OrderNotFoundException;
import com.spring.dallija.exception.user.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice(basePackages = "com.spring.dallija")
public class GlobalExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleNotMatchPasswordException(UserNotMatchPasswordException e) {
        log.debug("not match password :: {}", e.getMessage());
        return new ResponseEntity(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException e) {
        log.debug("이메일 중복", e);
        return new ResponseEntity(new ErrorResponse(e.getMessage()), HttpStatus.CONFLICT);
    }

    //validation 오류
    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.debug(e.getFieldError().getDefaultMessage(), e);
        return new ResponseEntity(new ErrorResponse(e.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleUnLoginException(UnLoginException e, WebRequest request) {
        log.debug("fail address ::  {}", request.getDescription(false), e);
        return new ResponseEntity(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleUnRoleUserException(UnRoleUserException e) {
        log.debug(e.getMessage(), e);
        return new ResponseEntity(new ErrorResponse(e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.debug(e.getMessage(), e);
        return new ResponseEntity(new ErrorResponse("json 타입을 확인해주세요."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleNotFoundItemException(ItemNotFoundException e) {
        log.debug(e.getMessage(), e);
        return new ResponseEntity(new ErrorResponse("상품을 찾을 수 없습니다."), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        log.debug(e.getMessage(), e);
        return new ResponseEntity(new ErrorResponse("유저를 찾을 수 없습니다."), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException e) {
        log.debug(e.getMessage(), e);
        return new ResponseEntity(new ErrorResponse("주문을 찾을 수 없습니다."), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleNotEnoughStockException(NotEnoughStockException e) {
        log.debug(e.getMessage(), e);
        return new ResponseEntity(new ErrorResponse("재고가 없습니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleDuplicateCategoryException(DuplicateCategoryException e) {
        log.debug(e.getMessage(), e);
        return new ResponseEntity(new ErrorResponse("카테고리가 중복 됩니다."), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleCartNotFoundException(CartNotFoundException e) {
        log.debug(e.getMessage(), e);
        return new ResponseEntity(new ErrorResponse("카트를 찾을 수 없습니다."), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<ErrorResponse> handleNotFoundCategoryException(NotFoundCategoryException e) {
        log.debug(e.getMessage(), e);
        return new ResponseEntity(new ErrorResponse("카테고리를 찾을 수 없습니다."), HttpStatus.NOT_FOUND);
    }
}
