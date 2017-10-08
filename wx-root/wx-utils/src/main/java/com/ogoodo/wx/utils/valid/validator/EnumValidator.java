package com.ogoodo.wx.utils.valid.validator;


import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * 判断类型是否是枚举, 不管什么枚举都能判断出来
 * @demo
 * @EnumValidator
 * private MyEnum enum1;
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= {EnumValidator.EnumValidatorImpl.class}) // 这里可以多个类
public @interface EnumValidator {
    String message() default "Invalid enum type(无效的枚举类型)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    

	public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, Object> {
	
	    @Override
	    public void initialize(EnumValidator constraintAnnotation) {
	    }
	
	    @Override
	    public boolean isValid(Object value, ConstraintValidatorContext context) {
	    		if(value == null) {
	    			return false;
	    		}
	        if (value.getClass().isEnum()) {
	            return true;
	        }
	        return false;
	    }
	}
}
