package com.ssm.rest.demo.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.rest.demo.entity.User;
import com.ssm.rest.demo.model.ConditionQueryModel;
import com.ssm.rest.demo.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring-mybatis.xml"})
public class MyBatisRadisCache {
	private static final Logger logger = LoggerFactory.getLogger(MyBatisRadisCache.class);
	
	@Autowired
	private IUserService userService;
	
	@Test
	public void testCache() {
		ConditionQueryModel cqModel = new ConditionQueryModel();
		List<User> list = new ArrayList<>();
		cqModel.setOffset(0);
		cqModel.setLimit(10);
		list = userService.findUser(cqModel);
		for (User user : list) {
			logger.info(user.toString());
		}
		
		cqModel.setOffset(10);
		cqModel.setLimit(10);
		list = userService.findUser(cqModel);
		for (User user : list) {
			logger.info(user.toString());
		}
		
		cqModel.setOffset(20);
		cqModel.setLimit(10);
		list = userService.findUser(cqModel);
		for (User user : list) {
			logger.info(user.toString());
		}
	}
}
