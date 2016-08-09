package com.lib.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//2016-08-18
	@Override
	public Date convert(String source) {
		// 将日期串转成日起类型(格式是 yyyy-MM-dd)
		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			return null;
		}
	}

}
