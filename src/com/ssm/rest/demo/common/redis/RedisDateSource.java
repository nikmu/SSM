package com.ssm.rest.demo.common.redis;

import redis.clients.jedis.ShardedJedis;

public interface RedisDateSource {

	public abstract ShardedJedis getRedisClient();
	
	public void returnResource(ShardedJedis shardedJedis);
	
	public void returnResource(ShardedJedis shardedJedis, boolean broken);
}
