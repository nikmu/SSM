package com.ssm.demo.dao.system;

import java.util.List;

import com.ssm.demo.entity.system.Resource;


public interface IResourceDao {
	int insert(Resource record);

    int insertSelective(Resource record);
    
    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    int deleteByPrimaryKey(Long id);

    
    Resource selectByPrimaryKey(Long id);

    List<Resource> findAll();
}