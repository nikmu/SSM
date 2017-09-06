package com.ssm.rest.demo.test;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring-mybatis.xml"})
public class testRedis {
	@Resource
	private RedisExample redisExample;
	
	@Test
	public void test() {
		URL url;
		try {
			url = new URL("http://www.baidu.com");
			redisExample.addLink("baidu", url);
			System.out.println(redisExample.getLink("baidu"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
