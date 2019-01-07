/**
 * 云畅网络科技
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.yc.trip.api.core.annotation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomJsonDateDeserializer extends JsonDeserializer<Date> {

	private static final String yyyyMMddFormat = "yyyyMMdd";
	private static final String dateFormat = "yyyy-MM-dd"; 
	private static final String yyyyMMddHHmmssFormat = "yyyyMMddHHmmss";
	private static final String customFormat = "yyyy-MM-dd HH:mm:ss";

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// 时间字符串
		String date = jp.getText();
		SimpleDateFormat format = null;
		switch (date.length()) {
		case 8:
			format = new SimpleDateFormat(yyyyMMddFormat);
			break;
		case 10:
			format = new SimpleDateFormat(dateFormat);
			break;
		case 14:
			format = new SimpleDateFormat(yyyyMMddHHmmssFormat);
			break;
		default:
			format = new SimpleDateFormat(customFormat);
		}
		try {
			return format.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
