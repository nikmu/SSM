package com.ssm.demo.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomObjectMapper extends ObjectMapper{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean camelCaseToLowerCaseWithUnderscores = false;
	private String dataFormatPattern;
	
	public void setCamelCaseToLowerCaseWithUnderscores(boolean camelCaseToLowerCaseWithUnderscores) {
		this.camelCaseToLowerCaseWithUnderscores = camelCaseToLowerCaseWithUnderscores;
	}

	public void setDateFormatPattern(String dataFormatPattern) {
		this.dataFormatPattern = dataFormatPattern;
	}
	
	public void init() {
		setSerializationInclusion(Include.NON_NULL);
		configure(SerializationFeature.INDENT_OUTPUT, true);
		if (camelCaseToLowerCaseWithUnderscores) {
			setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		}
		if(!StringUtils.isEmpty(dataFormatPattern)) {
			DateFormat dateFormat = new SimpleDateFormat(dataFormatPattern);
			setDateFormat(dateFormat);
		}
	}
	
	
}
