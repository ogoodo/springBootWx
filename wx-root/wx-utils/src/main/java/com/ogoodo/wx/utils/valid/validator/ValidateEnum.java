package com.ogoodo.wx.utils.valid.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;

@Documented
@Constraint(validatedBy = ValidateEnumValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NotNull(message = "Value cannot be null")
@ReportAsSingleViolation
public @interface ValidateEnum {

Class<? extends Enum<?>> enumClazz();

String message() default "Value is not valid";

Class<?>[] groups() default {};

Class<? extends Payload>[] payload() default {};

public abstract boolean ignoreCase() default false;

}
