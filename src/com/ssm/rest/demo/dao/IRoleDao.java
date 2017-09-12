package com.ssm.rest.demo.dao;

import java.util.List;

import com.ssm.rest.demo.entity.Role;

public interface IRoleDao {
	/**
	 *  create update delete
	 * @param record
	 * @return
	 */
	int insert(Role record);

    int insertSelective(Role record);
    
    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    int deleteByPrimaryKey(Long id);
    
    /**
     * Query
     * @param id
     * @return
     */
    Role selectByPrimaryKey(Long id);
    
    List<Role> findAll();
}