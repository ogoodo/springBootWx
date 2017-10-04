package com.ogoodo.wx.utils.valid.validator;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=MyTelValidator.class)
public @interface MyTel {

    int min() default 0;
    
    String pattern() default "defaultPattern";

    String message();

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};

}
