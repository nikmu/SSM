package com.ssm.rest.demo.dao;

import com.ssm.rest.demo.entity.RolesPermissions;

public interface IRolesPermissionsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RolesPermissions record);

    int insertSelective(RolesPermissions record);

    RolesPermissions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolesPermissions record);

    int updateByPrimaryKey(RolesPermissions record);
}