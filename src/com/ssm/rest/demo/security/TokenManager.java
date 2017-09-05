package com.ssm.rest.demo.security;

public interface TokenManager {

	String createToken(String username);
	
	boolean checkToken(String token);
}
