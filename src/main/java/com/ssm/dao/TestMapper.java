package com.ssm.dao;

import com.ssm.bean.Testt;
import com.ssm.bean.User;

public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Testt record);

    int insertSelective(Testt record);

    Testt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Testt record);

    int updateByPrimaryKey(Testt record);
    
    User getUser(User user);
    
    int userRegist(User user);
    
    int ifUserExist(String userName);
}