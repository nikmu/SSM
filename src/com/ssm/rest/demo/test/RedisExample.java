package com.ssm.rest.demo.test;

import java.net.URL;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisExample {

//	@Autowired
//	private RedisTemplate<String, String> template;
	
	@Resource(name="redisTemplate")
	private ListOperations<String, String> listOps;
	
	public void addLink(String userId, URL url) {
		listOps.leftPush(userId, url.toExternalForm());
	}
}
