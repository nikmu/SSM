package com.ssm.rest.demo.dao;


import com.ssm.rest.demo.entity.UserRolesKey;

public interface IUserRolesDao {
    int deleteByPrimaryKey(UserRolesKey key);

    int insert(UserRolesKey record);

    int insertSelective(UserRolesKey record);
    
    Long[] findByUserId(Long userId);
    
    Long[] findByRoleId(Long roleId);
    
}