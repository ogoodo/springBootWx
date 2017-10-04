package com.ogoodo.wx.utils.valid.converter;

import org.springframework.core.convert.converter.Converter;  


public class StringToGenderConverter implements Converter<String, Gender> {  
  
    @Override  
    public Gender convert(String source) {  
        return Gender.getByValue(Integer.parseInt(source));  
    }  
}  
