package com.ssm.rest.demo.dao;

import com.ssm.rest.demo.entity.UserRoles;

public interface IUserRolesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRoles record);

    int insertSelective(UserRoles record);

    UserRoles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRoles record);

    int updateByPrimaryKey(UserRoles record);
}