package com.pku.common;

import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;




//@Component
public class JsonDateTimeSerializer extends JsonSerializer<Date> {

	 
	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			com.fasterxml.jackson.core.JsonProcessingException {

		jsonGenerator.writeString(CommonUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
	}
}
