package com.ssm.rest.demo.security.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ssm.rest.demo.common.utils.CodecUtil;
import com.ssm.rest.demo.common.utils.StringUtil;
import com.ssm.rest.demo.model.TokenModel;
import com.ssm.rest.demo.security.TokenManager;

public class DefaultTokenManager implements TokenManager {

	private static Map<String, String> tokenMap =  new ConcurrentHashMap<>();
	
	@Override
	public TokenModel createToken(String userName) {
		String token = CodecUtil.createUUID();
		tokenMap.put(token, userName);
		return new TokenModel(userName, token);
	}

	@Override
	public boolean checkToken(String token) {
		return StringUtil.isNotEmpty(token) && tokenMap.containsKey(token);
	}

	@Override
	public boolean checkToken(TokenModel tokenModel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TokenModel getToken(String auth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteToken(String userName) {
		// TODO Auto-generated method stub
		
	}

}
