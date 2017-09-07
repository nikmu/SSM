package com.ssm.rest.demo.service;

import java.util.List;

import com.ssm.rest.demo.entity.User;
import com.ssm.rest.demo.model.ConditionQueryModel;

public interface IUserService {

	public List<User> findUser(ConditionQueryModel cqModel);
	
	public User getUserById(Integer id);
	
	public User getUserByName(String userName);
	
	public User addUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean deleteUserBy(User Name);
}
