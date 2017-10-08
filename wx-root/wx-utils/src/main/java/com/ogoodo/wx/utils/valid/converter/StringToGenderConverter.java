package com.ogoodo.wx.utils.valid.converter;

import org.springframework.core.convert.converter.Converter;  


public class StringToGenderConverter implements Converter<String, GenderConverterEnum> {  
  
    @Override  
    public GenderConverterEnum convert(String source) {  
        return GenderConverterEnum.getByValue(Integer.parseInt(source));  
    }  
}  
