package com.ogoodo.wx.utils.valid.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=GenderValidatorImpl.class)
public @interface GenderValidator {
    String message() default "Invalid gender type(无效的枚举类型)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}