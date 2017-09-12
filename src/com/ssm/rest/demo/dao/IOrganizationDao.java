package com.ssm.rest.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.rest.demo.entity.Organization;

public interface IOrganizationDao {
	int insert(Organization record);

    int insertSelective(Organization record);
    
    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

    Organization selectByPrimaryKey(Long id);

    List<Organization> findAll();
    
    List<Organization> findAllWithExclude(@Param("Organization")Organization excludeOraganization,
    		@Param("asParentIds")String asParentIds);

//    void move(Organization source, Organization target);
}