package com.ogoodo.wx.utils.valid.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidatorImpl implements ConstraintValidator<GenderValidator, Enum<Gender>> {

    @Override
    public void initialize(GenderValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(Enum<Gender> value, ConstraintValidatorContext context) {
        if (value instanceof Gender) {
            return true;
        }
        return false;
    }
}
