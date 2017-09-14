package com.ssm.demo.model;

import org.apache.shiro.authc.AuthenticationToken;

public class TokenModel implements AuthenticationToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	
	private String token;

	public TokenModel(String username, String token) {
		super();
		this.username = username;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		return this.token;
	}

	@Override
	public Object getPrincipal() {
		return this.username;
	}
}
