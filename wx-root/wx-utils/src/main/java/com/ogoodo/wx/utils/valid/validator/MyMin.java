package com.ogoodo.wx.utils.valid.validator;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=MyMinValidator.class)
public @interface MyMin {

    int min() default 0;

    String message();

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};

}
