package com.ssm.rest.demo.security;

public class TokenException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenException(String message) {
		super(message);
	}
}
