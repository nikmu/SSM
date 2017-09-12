package com.ssm.rest.demo.service;

import java.util.List;
import java.util.Set;

import com.ssm.rest.demo.entity.User;

public interface IUserService {

	public User addUser(User user);
	
	public User updateUser(User user);
	
	public boolean deleteUser(Long userId);
	
	public void changePassword(Long userId, String newPassword);
	
	public User findById(Long id);
	
	public List<User> findAll();
	
	public User findByUsername(String username);
	
	public Set<String> findRoles(String username);
	
	public Set<String> findPermissions(String username);
	
	public void correlationRoles(Long userId, Long...roleIds);
	
	public void uncorrelationRoles(Long userId, Long...roleIds);
	
}
