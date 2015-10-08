package com.actions.prototype.common;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * <p>
 * JsonDateSerializer class.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
public class JsonDateSerializer extends JsonSerializer<Date> {
	
	private static final DateFormat DF = new SimpleDateFormat("MM-dd-yyyy");
	
	/** {@inheritDoc} */
	@Override
	public void serialize(Date date, JsonGenerator jsonGen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jsonGen.writeString(DF.format(date));
	}
}
