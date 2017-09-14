package com.ssm.demo.dao.system;

import com.ssm.demo.entity.system.UserRolesKey;

public interface IUserRolesDao {
    int deleteByPrimaryKey(UserRolesKey key);

    int insert(UserRolesKey record);

    int insertSelective(UserRolesKey record);
    
    Long[] findByUserId(Long userId);
    
    Long[] findByRoleId(Long roleId);
    
}