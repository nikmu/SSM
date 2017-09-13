package com.ssm.rest.demo.security;

import org.apache.shiro.authc.AuthenticationException;

public class TokenException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenException(String message) {
		super(message);
	}
}
