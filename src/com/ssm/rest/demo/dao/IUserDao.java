package com.ssm.rest.demo.dao;

import java.util.List;

import com.ssm.rest.demo.entity.User;

public interface IUserDao {
	
	/**
	 * Delete
	 * 
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Long id);

    /**
     * add
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * Query by id
     * 
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);

    /**
     * Query by username
     * @param username
     * @return
     */
    User findByUsername(String username);
    
    /**
     * Query all User
     * @return
     */
    List<User> findAll();
    /**
     * Update 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * Update
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);
    
    
}