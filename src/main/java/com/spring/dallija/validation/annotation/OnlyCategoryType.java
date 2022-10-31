package com.spring.dallija.validation.annotation;

import com.spring.dallija.validation.validator.CategoryTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CategoryTypeValidator.class)
public @interface OnlyCategoryType {
    String message() default "잘못된 카테고리 이름 입니다."; // 디폴트 message 설정

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}