package com.ssm.rest.demo.common;


import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ssm.rest.demo.security.TokenException;

@ControllerAdvice
@ResponseBody
public class ExceptionActive {

	private Logger logger = LoggerFactory.getLogger(ExceptionActive.class);
	
	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Response handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		logger.error("缺少请求参数", e);
		return new Response().failure("required_parameter_is_no_present");
	}
	
	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Response handleHttpMessageNotReadableException(HttpRequestMethodNotSupportedException e) {
		logger.error("参数解析失败", e);
		return new Response().failure("could_not_read_json");
	}
	
	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		logger.error("参数验证失败", e);
		BindingResult result = e.getBindingResult();
		FieldError error = result.getFieldError();
		String field = error.getField();
		String code = error.getDefaultMessage();
		String message = String.format("%s:%s", field, code);
		return new Response().failure(message);
	}
	
	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public Response handleBindException(BindException e) {
		logger.error("参数绑定失败", e);
		BindingResult result = e.getBindingResult();
		FieldError error = result.getFieldError();
		String field = error.getField();
		String code = error.getDefaultMessage();
		String message = String.format("%s:%s", field, code);
		return new Response().failure(message);
	}
	
	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Response handleConstraintViolationException(ConstraintViolationException e) {
		logger.error("参数验证失败", e);
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		ConstraintViolation<?> violation = violations.iterator().next();
		String message = violation.getMessage();
		return new Response().failure("parameter:" + message);
	}
	
	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	public Response handleValidationException(ValidationException e) {
		logger.error("参数验证失败", e);
		return new Response().failure("validation_exception");
	}
	
	/**
	 * 401 - Unauthorized
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(TokenException.class)
	public Response handleTokenException(TokenException e) {
		logger.error("令牌验证失败", e);
		return new Response().failure(e.getMessage());
	}
	
	@ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response handleUnauthenticatedException(UnauthorizedException e) {
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("exception", e);
//        mv.setViewName("unauthorized");
        return new Response().failure(e.getMessage());
    }
	
	@ExceptionHandler({UnknownAccountException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response handleUnknownAccountException(UnknownAccountException e) {
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("exception", e);
//        mv.setViewName("unauthorized");
        return new Response().failure(e.getMessage());
    }
	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Response handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		logger.error("不支持当前请求方法", e);
		return new Response().failure("request_method_not_supported");
	}
	
	/**
	 * 415 - Unsupperted Media Type
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Response handleHttpMediaTypeNotSupportedException(
			HttpMediaTypeNotSupportedException e) {
		logger.error("不支持当前媒体类型", e);
		return new Response().failure("content type not supported");
	}
	
	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ServiceException.class)
	public Response handleServiceException(ServiceException e) {
		logger.error("服务运行异常", e);
		return new Response().failure(e.getMessage());
	}
	
	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Response handleException(Exception e) {
		logger.error("通用异常", e);
		return new Response().failure(e.getMessage());
	}
}
