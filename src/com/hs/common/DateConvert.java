package com.hs.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

public class DateConvert implements Converter{

	@Override
	public Object convert(Class type, Object value) {
		if(value ==null) return null;
		else if(!(value instanceof String)) return value;
		else{
			String s = (String) value;
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return sdFormat.parse(s);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
