package com.ssm.rest.demo.dao;

import java.util.List;

import com.ssm.rest.demo.entity.Resource;

public interface IResourceDao {
	int insert(Resource record);

    int insertSelective(Resource record);
    
    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    int deleteByPrimaryKey(Long id);

    
    Resource selectByPrimaryKey(Long id);

    List<Resource> findAll();
}