package com.spring.dallija.validation.validator;

import com.spring.dallija.domain.user.GenderStatus;
import com.spring.dallija.validation.annotation.OnlyGender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<OnlyGender, String> {
    @Override
    public void initialize(OnlyGender constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return checkGender(value);
    }

    private boolean checkGender(String s) {
        GenderStatus[] types = GenderStatus.class.getEnumConstants();
        if (types != null){
            for (GenderStatus type : types) {
                if (s.equals(type.toString()))
                    return true;
            }
        }
        return false;
    }
}
