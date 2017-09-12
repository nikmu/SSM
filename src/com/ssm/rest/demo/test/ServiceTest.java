package com.ssm.rest.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.rest.demo.entity.Organization;
import com.ssm.rest.demo.entity.User;
import com.ssm.rest.demo.service.IOrganizationService;
import com.ssm.rest.demo.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring-mybatis.xml"})
public class ServiceTest {

private static final Logger logger = LoggerFactory.getLogger(MyBatisRadisCache.class);
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrganizationService orgaizationService;
	
	@Test
	public void testUserService() {
		User user = new User();
		user.setUsername("wang1");
		user.setPassword("123");
		user = userService.addUser(user);
		System.out.println(user.toString());
	}
	
	@Test
	public void testMoveOrg() {
		Organization source = orgaizationService.findOne(Long.valueOf(4));
		Organization target = orgaizationService.findOne(Long.valueOf(2));
		
		orgaizationService.move(source, target);
		
	}
}
