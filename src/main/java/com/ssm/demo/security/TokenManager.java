package com.ssm.demo.security;

import com.ssm.demo.model.TokenModel;

public interface TokenManager {

	public TokenModel createToken(String username);
	
	boolean checkToken(TokenModel tokenModel);
	
	public String getUser(String token);
	
	public TokenModel getToken(String auth);
	
	public void deleteToken(String userName);

	boolean checkToken(String token);
}
