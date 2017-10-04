package com.ogoodo.wx.utils.valid.formatter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyPhoneNumberFormatter implements Formatter<MyPhoneNumberModel> {  

    Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");

	private String message;

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override  
    public String print(MyPhoneNumberModel phoneNumber, Locale locale) {//①格式化  
        if(phoneNumber == null) {  
            return "";  
        }  
        return new StringBuilder().append(phoneNumber.getAreaCode()).append("-")  
                                  .append(phoneNumber.getPhoneNumber()).toString();  
    }  
  
    @Override  
    public MyPhoneNumberModel parse(String text, Locale locale) throws ParseException {//②解析  
        if(!StringUtils.hasLength(text)) {  
            //①如果source为空 返回null  
            return null;  
        }
        Matcher matcher = pattern.matcher(text);  
        if(matcher.matches()) {  
            //②如果匹配 进行转换  
            MyPhoneNumberModel phoneNumber = new MyPhoneNumberModel();  
            phoneNumber.setAreaCode(matcher.group(1));  
            phoneNumber.setPhoneNumber(matcher.group(2));  
            return phoneNumber;  
        } else {  
//    			ParsePosition position = new ParsePosition(0);
//        		throw new ParseException("电话转换异常:" + text, position.getIndex());
            //③如果不匹配 转换失败  
            // messageSource.setBasename("messageSource");
                // String testLocale = messageSource.getMessage(this.message, null, locale);
                String testLocale = "要从国际化取值";
	    		String msg = String.format("类型转换失败，需要格式[010-12345678]，但格式是[%s], 自定义message[%s], 从i18n动态获取数据[%s]", text, this.message, testLocale);
            throw new IllegalArgumentException(msg, new ParseException("解析错误", 1122));
        }
    }  
}  

