package com.ssm.rest.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.rest.demo.dao.IUserDao;
import com.ssm.rest.demo.entity.User;
import com.ssm.rest.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userdao;
	
	@Override
	public User getUserById(Integer id) {
		return userdao.selectByPrimaryKey(id);
	}

}
