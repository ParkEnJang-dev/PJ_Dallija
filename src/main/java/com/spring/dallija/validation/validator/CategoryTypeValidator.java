package com.spring.dallija.validation.validator;

import com.spring.dallija.domain.category.CategoryType;
import com.spring.dallija.validation.annotation.OnlyCategoryType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryTypeValidator implements ConstraintValidator<OnlyCategoryType, String> {
    @Override
    public void initialize(OnlyCategoryType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return checkCategoryType(value);
    }

    private boolean checkCategoryType(String s) {
        CategoryType[] types = CategoryType.class.getEnumConstants();
        if (types != null){
            for (CategoryType type : types) {
                if (s.equals(type.toString()))
                    return true;
            }
        }
        return false;
    }
}