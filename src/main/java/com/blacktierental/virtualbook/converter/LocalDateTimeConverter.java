package com.blacktierental.virtualbook.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeConverter implements Converter<String,LocalDateTime> {
	static final Logger logger = LoggerFactory.getLogger(LocalDateTimeConverter.class);

	@Override
	public LocalDateTime convert(String attribute) {
		LocalDateTime result = LocalDateTime.parse(attribute , DateTimeFormatter.ISO_DATE_TIME);
		logger.info("LocalDateTime: ",attribute);
		return result;
	}

}
