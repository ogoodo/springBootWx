package com.ogoodo.wx.utils.valid.converter;

import java.util.HashMap;  
import java.util.Map;  
  
public enum GenderConverterEnum implements BaseEnum {  
    MALE(1), FEMALE(2);  
  
    private int value;  
    private static Map<Integer, GenderConverterEnum> valueMap = new HashMap<>();  
      
    static {  
        for(GenderConverterEnum gender : GenderConverterEnum.values()) {  
            valueMap.put(gender.value, gender);  
        }  
    }  
      
    GenderConverterEnum(int value) {  
        this.value = value;  
    }  
      
    @Override  
    public int getValue() {  
        return value;  
    }  
  
    public static GenderConverterEnum getByValue(int value) {  
        GenderConverterEnum result = valueMap.get(value);  
        if(result == null) {  
            throw new IllegalArgumentException("No element matches " + value);  
        }  
        return result;  
    }  
} 

