package com.ogoodo.wx.utils.valid.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LocalDateSerializer extends JsonSerializer<LocalDate> {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        // jsonGenerator.writeString(DATE_FORMAT.format(localDate));
        jsonGenerator.writeString("test-123-456");

        // jsonGenerator.writeStartObject();
        // jsonGenerator.writeFieldName("id");
        // jsonGenerator.writeNumber("value.getId()");
        // jsonGenerator.writeFieldName("name");
        // jsonGenerator.writeString("value.getName()");
        // jsonGenerator.writeEndObject();
    
    }
}