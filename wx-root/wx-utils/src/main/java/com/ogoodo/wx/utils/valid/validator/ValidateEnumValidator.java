package com.ogoodo.wx.utils.valid.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidateEnumValidator implements ConstraintValidator<ValidateEnum, String> {

  private ValidateEnum annotation;

  @Override
  public void initialize(ValidateEnum constraintAnnotation) {
    this.annotation = constraintAnnotation;
  }

  @Override
  public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext)
  {
      boolean result = false;
       
      Object[] enumValues = this.annotation.enumClazz().getEnumConstants();
       
      if(enumValues != null)
      {
          for(Object enumValue:enumValues)
          {
              if(valueForValidation.equals(enumValue.toString()) 
                 || (this.annotation.ignoreCase() && valueForValidation.equalsIgnoreCase(enumValue.toString())))
              {
                  result = true; 
                  break;
              }
          }
      }
       
      return result;
  }
 

//  List<String> valueList = null;
//  
//  @Override
//  public void initialize(ValidateEnum constraintAnnotation) {
//    this.annotation = constraintAnnotation;
//
//    valueList = new ArrayList<String>();
//    Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();
//
//    @SuppressWarnings("rawtypes")
//    Enum[] enumValArr = enumClass.getEnumConstants();
//
//    for(@SuppressWarnings("rawtypes")
//    Enum enumVal : enumValArr) {
//      valueList.add(enumVal.toString().toUpperCase());
//    }
//
//  }
//  
//  @Override
//  public boolean isValid(String value, ConstraintValidatorContext context) {
//    if(!valueList.contains(value.toUpperCase())) {
//      return false;
//    }
//    return true;
//  }

}