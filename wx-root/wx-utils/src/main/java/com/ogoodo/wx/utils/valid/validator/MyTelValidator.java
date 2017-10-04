package com.ogoodo.wx.utils.valid.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;


// 这里MyPhoneNumberModel改为String就可以验证String了
public class MyTelValidator implements ConstraintValidator<MyTel, MyTelModel> {

    Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");

    // @Resource
    // private ResourceBundleMessageSource messageSource;
    
    private MyTel myTel;
    
    @Override
    public void initialize(MyTel myTel) {
        this.myTel = myTel;
    }

    @Override
    public boolean isValid(MyTelModel value, ConstraintValidatorContext constraintContext) {
        boolean isValid;
//        if(!StringUtils.hasLength(value)) {  
//             value = "";
//        }
        Matcher matcher = pattern.matcher(value.getPhoneNumber());  
        if(matcher.matches()) { 
            isValid = true;
        } else {
            isValid = false;
        }
//        if(value == null) { 
//          isValid = true;
//          isValid = false;
//      } else {
//          isValid = false;
//      }
        
        if(!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate(myTel.message()).addConstraintViolation();
        }
        return isValid;
    }

}
