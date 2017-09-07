package com.ssm.rest.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.rest.demo.dao.IUserDao;
import com.ssm.rest.demo.entity.User;
import com.ssm.rest.demo.model.ConditionQueryModel;
import com.ssm.rest.demo.model.PageModel;
import com.ssm.rest.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userdao;
	
	@Override
	public User getUserById(Integer id) {
		return userdao.selectByPrimaryKey(id);
	}

	@Override
	public User getUserByName(String userName) {
		return userdao.selectByUserName(userName);
	}

	@Override
	public User addUser(User user) {
		System.out.println(userdao.insert(user));
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUserBy(User Name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> findUser(ConditionQueryModel cqModel) {
		return userdao.findUser(cqModel);
	}

}
