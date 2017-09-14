package com.ssm.demo.dao;

import com.ssm.demo.entity.UserRolesKey;

public interface UserRolesMapper {
    int deleteByPrimaryKey(UserRolesKey key);

    int insert(UserRolesKey record);

    int insertSelective(UserRolesKey record);
}