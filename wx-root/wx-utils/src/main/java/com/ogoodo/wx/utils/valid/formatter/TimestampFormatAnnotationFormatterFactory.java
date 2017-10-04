package com.ogoodo.wx.utils.valid.formatter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;



/**
 * Timestamp注解格式化工厂
 */
public class TimestampFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<TimestampFormat>{
    private final Set<Class<?>> fieldTypes;  
    private final TimestampFormatter formatter;  

    public TimestampFormatAnnotationFormatterFactory() {
        Set<Class<?>> set = new HashSet<Class<?>>();  
        set.add(Timestamp.class);  
        this.fieldTypes = set;  
        this.formatter = new TimestampFormatter();
    }


    public Set<Class<?>> getFieldTypes() {
        return fieldTypes;
    }


    public Parser<Timestamp> getParser(TimestampFormat annotation, Class<?> fieldType) {
        return formatter;  
    }


    public Printer<Timestamp> getPrinter(TimestampFormat annotation, Class<?> fieldType) {
        return formatter;  
    }

    /**
     * 字符转Timestamp类型格式化类
     */
    private class TimestampFormatter implements Formatter<Timestamp>,Serializable{
        private static final long serialVersionUID = -818656464607971661L;


        public String print(Timestamp value, Locale locale) {
    			System.out.println("print==============");
            if(value == null) {  
                return "";  
            }  
            return value.toString();
        }


        public Timestamp parse(String value, Locale locale) throws ParseException {
			System.out.println("parse==============");
            if(value.length() == 16){
                value = new StringBuffer(value).append(":00").toString();
            }
            return Timestamp.valueOf(value);
        }

    }

}
