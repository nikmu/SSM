package com.ssm.rest.demo.common.redis.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.rest.demo.common.redis.RedisDateSource;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisException;

@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDateSource {

	private static final Logger log = LoggerFactory.getLogger(RedisDataSourceImpl.class);
	
	@Autowired(required = false)
	private ShardedJedisPool shardedJedisPool;
	
	@Override
	public ShardedJedis getRedisClient() {
		try {
			ShardedJedis shardedJedis = shardedJedisPool.getResource();
			return shardedJedis;
		} catch (JedisException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void returnResource(ShardedJedis shardedJedis) {
		shardedJedis.close();
	}

	@Override
	public void returnResource(ShardedJedis shardedJedis, boolean broken) {
		// TODO Auto-generated method stub

	}

}
