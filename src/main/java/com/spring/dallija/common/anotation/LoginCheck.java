package com.spring.dallija.common.anotation;


import com.spring.dallija.domain.user.UserRole;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
public @interface LoginCheck {
    UserRole userRole() default UserRole.ANYUSER;
}
