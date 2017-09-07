package com.ssm.rest.demo.security.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.ssm.rest.demo.common.utils.CodecUtil;
import com.ssm.rest.demo.common.utils.StringUtil;
import com.ssm.rest.demo.model.TokenModel;
import com.ssm.rest.demo.security.TokenManager;

@Component
public class RedisTokenManager implements TokenManager {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public TokenModel createToken(String userName) {
		String token = CodecUtil.createUUID();
		TokenModel model = new TokenModel(userName, token);
		redisTemplate.boundValueOps(token).set(userName, 15, TimeUnit.MINUTES);
		return model;
	}

	@Override
	public boolean checkToken(TokenModel tokenModel) {
		if (tokenModel == null) {
			return false;
		}
		String userName = redisTemplate.boundValueOps(tokenModel.getToken()).get();
		if(userName != null && userName.equals(tokenModel.getUserName())) {
			redisTemplate.boundValueOps(tokenModel.getToken())
			.expire(15, TimeUnit.MINUTES);
			return true;
		}
		
		return false;
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
