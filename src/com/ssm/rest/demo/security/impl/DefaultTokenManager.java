package com.ssm.rest.demo.security.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ssm.rest.demo.common.utils.CodecUtil;
import com.ssm.rest.demo.common.utils.StringUtil;
import com.ssm.rest.demo.security.TokenManager;

public class DefaultTokenManager implements TokenManager {

	private static Map<String, String> tokenMap =  new ConcurrentHashMap<>();
	
	@Override
	public String createToken(String username) {
		String token = CodecUtil.createUUID();
		tokenMap.put(token, username);
		return token;
	}

	@Override
	public boolean checkToken(String token) {
		return StringUtil.isNotEmpty(token) && tokenMap.containsKey(token);
	}

}
