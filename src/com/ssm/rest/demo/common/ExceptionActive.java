package com.ssm.rest.demo.common;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ExceptionActive {

	Logger logger = Logger.getLogger(ExceptionActive.class);
	/*
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Response handleHttpMessageNotReadableException(HttpRequestMethodNotSupportedException e) {
		logger.error("123", e);
		return new Response().failure("could_not_read_json");
	}
	
	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Response handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		logger.error("not support request method", e);
		return new Response().failure("request_method_not_supported");
	}
	
	/**
	 * 415 - Unsupperted Media Type
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Response handleHttpMediaTypeNotSupportedException(
			HttpMediaTypeNotSupportedException e) {
		return new Response().failure("content type not supported");
	}
	
	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Response handleException(Exception e) {
		return new Response().failure(e.getMessage());
	}
}
