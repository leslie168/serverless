package com.multicloud.notify.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multicloud.notify.model.ServiceException;

public class JsonUtils {
	static final ObjectMapper JACKSON = new ObjectMapper();

	static {
		//JACKSON.setSerializationInclusion(Inclusion.NON_NULL);
		//JACKSON.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static final <T> T fromJson(Reader r, Class<T> clazz) throws IOException {
		return JACKSON.readValue(r, clazz);
	}

	public static final <T> T fromJson(String json, Class<T> clazz) {
		try {
			StringReader sr = new StringReader(json);
			return fromJson(sr, clazz);

		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public static final void toJson(Writer w, Object value) throws IOException {
		JACKSON.writeValue(w, value);
	}

	public static final String toJson(Object value) {
		try {
			StringWriter sw = new StringWriter();
			toJson(sw, value);
			return sw.toString();

		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public static final void toPrettyJson(Writer w, Object value) throws IOException {
		JACKSON.writerWithDefaultPrettyPrinter().writeValue(w, value);
	}

	public static final String toPrettyJson(Object value) {
		try {
			StringWriter sw = new StringWriter();
			toPrettyJson(sw, value);
			return sw.toString();

		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
