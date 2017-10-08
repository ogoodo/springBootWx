package com.ogoodo.wx.utils.valid.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDate;

/**
 * 参考: http://multifrontgarden.tistory.com/172
 */

public class CustomObjectMapper extends ObjectMapper {
	private static final long serialVersionUID = 1L;

	public CustomObjectMapper(){
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());

        registerModule(simpleModule);
    }
}
