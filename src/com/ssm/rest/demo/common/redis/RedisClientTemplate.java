package com.ssm.rest.demo.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedis;

public class RedisClientTemplate {

	private static final Logger log	= LoggerFactory.getLogger(RedisClientTemplate.class);
	
	@Autowired
	private RedisDateSource redisDateSource;
	
	/**
	 * 
	 */
	public void disconnect() {
		ShardedJedis shardedJedis = redisDateSource.getRedisClient();
		shardedJedis.disconnect();
	}
	
	public String set(String key, String value) {
		String result = null;
		
		ShardedJedis shardedJedis = redisDateSource.getRedisClient();
		if(shardedJedis == null) {
			return result;
		}
		
		boolean broken = false;
		
			result = shardedJedis.set(key, value);
		
		return result;
	}
}
