package com.ogoodo.wx.utils.valid.validator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumDemo {

    A("1"), B("2"), C("3"), D("4"), E("5"), F("6"), G("7"),;  
      
  
    private String code;  
  
    EnumDemo(String code) {  
        this.code = code;  
    }  
  
    @JsonValue  
    public String getCode() {  
        return code;  
    }  

    // 这个一定要, 不然输入枚举之外的值, 会有全局异常, 到不了vadilator那里
    @JsonCreator
    public static EnumDemo getEnumDemo(String code) {
        for (EnumDemo gender : EnumDemo.values()) {
            if (gender.getCode().equals(code)) {
                return gender;
            }
        }
        return null;
    }
 
} 