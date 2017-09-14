package com.ssm.demo.service.system.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.demo.common.utils.PasswordHelper;
import com.ssm.demo.dao.system.IUserDao;
import com.ssm.demo.dao.system.IUserRolesDao;
import com.ssm.demo.entity.system.User;
import com.ssm.demo.service.system.IRoleService;
import com.ssm.demo.service.system.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IUserRolesDao userRoleDao;
	@Autowired
	private PasswordHelper passwordHelper;
	@Autowired
	private IRoleService roleService;
	
	
	@Override
	public User addUser(User user) {
		passwordHelper.encryptPassword(user);
		userDao.insert(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		userDao.updateByPrimaryKey(user);
		return user;
	}

	@Override
	public boolean deleteUser(Long userId) {
		userDao.deleteByPrimaryKey(userId);
		return true;
	}

	@Override
	public void changePassword(Long userId, String newPassword) {
		User user =userDao.selectByPrimaryKey(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateByPrimaryKey(user);
	}

	@Override
	public User findById(Long id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public Set<String> findRoles(String username) {
		User user = findByUsername(username);
        if(user == null) {
            return Collections.emptySet();
        }
        Long[] roleIds = userRoleDao.findByUserId(user.getId());
        return roleService.findRoles(roleIds);
	}

	@Override
	public Set<String> findPermissions(String username) {
		User user = findByUsername(username);
	    if(user == null) {
	        return Collections.emptySet();
	    }
	    Long[] roleIds = userRoleDao.findByUserId(user.getId());
	    return roleService.findPermissions(roleIds);
	}

	@Override
	public void correlationRoles(Long userId, Long... roleIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		// TODO Auto-generated method stub
		
	}

}
