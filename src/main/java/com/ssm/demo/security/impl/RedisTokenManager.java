package com.ssm.demo.security.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.ssm.demo.common.utils.CodecUtil;
import com.ssm.demo.common.utils.StringUtil;
import com.ssm.demo.model.TokenModel;
import com.ssm.demo.security.TokenManager;

@Component
public class RedisTokenManager implements TokenManager {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public TokenModel createToken(String username) {
		String token = CodecUtil.createUUID();
		redisTemplate.boundValueOps(token).set(username, 15, TimeUnit.MINUTES);
		return new TokenModel(username, token);
	}
	
	@Override
	public boolean checkToken(TokenModel tokenModel) {
		if (tokenModel == null) {
			return false;
		}
		
		String username = redisTemplate.boundValueOps(tokenModel.getToken()).get();
		if(username != null && username.equals(tokenModel.getUsername())) {
			redisTemplate.boundValueOps(tokenModel.getToken())
			.expire(15, TimeUnit.MINUTES);
			return true;
		}
		
		return false;
	}

	@Override
	public String getUser(String token) {
		String username = redisTemplate.boundValueOps(token).get();
		if (username != null) {
			redisTemplate.boundValueOps(token)
			.expire(15, TimeUnit.MINUTES);
			return username;
		}
		return null;
	}
	
	@Override
	public TokenModel getToken(String auth) {
		if (auth == null || auth.length() == 0) {
			return null;
		}
		
		String[] param = auth.split("_");
		if (param.length != 2) {
			return null;
		}
		String userName = param[0];
		String token = param[1];
		return new TokenModel(userName, token);
	}

	@Override
	public void deleteToken(String token) {
		redisTemplate.delete(token);
	}


	@Override
	public boolean checkToken(String token) {
		String userName = redisTemplate.boundValueOps(token).get();
		if (StringUtil.isNotEmpty(userName)) {
			return true;
		}
		return false;
	}
}
