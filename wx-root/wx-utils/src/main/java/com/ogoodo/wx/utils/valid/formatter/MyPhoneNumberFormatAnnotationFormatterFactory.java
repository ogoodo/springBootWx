package com.ogoodo.wx.utils.valid.formatter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//①指定可以解析/格式化的字段注解类型  
public class MyPhoneNumberFormatAnnotationFormatterFactory
	implements AnnotationFormatterFactory<MyPhoneNumberFormat> {

	// private static final Set<Class<?>> FIELD_TYPES;

    // static {
    //     Set<Class<?>> fieldTypes = new HashSet<Class<?>>();
    //     fieldTypes.add(MyPhoneNumberModel.class);
    //     FIELD_TYPES = Collections.unmodifiableSet(fieldTypes);
    // }

	// private final MyPhoneNumberFormatter formatter;  
//	public MyPhoneNumberFormatAnnotationFormatterFactory() {
//	    // this.formatter = new MyPhoneNumberFormatter();//此处使用之前定义的Formatter实现
//	}

	public Set<Class<?>> getFieldTypes() {
		return new HashSet<Class<?>>(Arrays.asList(new Class<?>[] {
			MyPhoneNumberModel.class
		}));
	}

	// //②指定可以被解析/格式化的字段类型集合  
	// @Override  
	// public Set<Class<?>> getFieldTypes() {  
	//     return FIELD_TYPES;  
	// }  
	//③根据注解信息和字段类型获取解析器  
	@Override  
	public Parser<?> getParser(MyPhoneNumberFormat annotation, Class<?> fieldType) {
	    return configureFormatterFrom(annotation, fieldType);
	}  
	//④根据注解信息和字段类型获取格式化器  
	@Override     
	public Printer<?> getPrinter(MyPhoneNumberFormat annotation, Class<?> fieldType) {  
	    return configureFormatterFrom(annotation, fieldType);
	}
	
	private Formatter<?> configureFormatterFrom(MyPhoneNumberFormat annotation, Class<?> fieldType) { 
		MyPhoneNumberFormatter formatter = new MyPhoneNumberFormatter();
		// formatter.setMessageSource(messageSource);
		formatter.setMessage(annotation.message());
	    return formatter; 
	}
	
}  

