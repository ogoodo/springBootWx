package com.ogoodo.wx.utils.valid.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;

//import com.ogoodo.valid.formatter.factory.MyPhoneNumberModel;

public class MyMobileValidator implements ConstraintValidator<MyMobile, String> {

    Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");

    // @Resource
    // private ResourceBundleMessageSource messageSource;
    
    private MyMobile myTel;
    
    @Override
    public void initialize(MyMobile myTel) {
        this.myTel = myTel;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
        boolean isValid;
        if(!StringUtils.hasLength(value)) {  
             value = "";
        }
        Matcher matcher = pattern.matcher(value);  
        if(matcher.matches()) { 
            isValid = true;
        } else {
            isValid = false;
        }
        
        if(!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate(myTel.message()).addConstraintViolation();
        }
        return isValid;
    }

}
