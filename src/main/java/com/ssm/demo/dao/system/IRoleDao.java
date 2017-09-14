package com.ssm.demo.dao.system;

import java.util.List;

import com.ssm.demo.entity.system.Role;


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