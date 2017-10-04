package com.ogoodo.wx.utils.valid.validator;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.support.ResourceBundleMessageSource;

public class MyMinValidator implements ConstraintValidator<MyMin, String> {

    // @Resource
    // private ResourceBundleMessageSource messageSource;
    
    private MyMin myMin;
    
    @Override
    public void initialize(MyMin myMin) {
        this.myMin = myMin;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
        boolean isValid;
        
        if(value != null && value.length() >= myMin.min()) {
            isValid = true;
        }
        else {
            isValid = false;
        }
        
        if(!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate(myMin.message()).addConstraintViolation();
        }
        return isValid;
    }

}
