package com.ssm.rest.demo.dao;

import java.util.List;

import com.ssm.rest.demo.entity.User;
import com.ssm.rest.demo.model.ConditionQueryModel;

public interface IUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByUserName(String userName);
    
    List<User> findUser(ConditionQueryModel cqModel);
}