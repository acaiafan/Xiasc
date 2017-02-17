package com.ssm.dao;

import com.ssm.bean.Testt;

public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Testt record);

    int insertSelective(Testt record);

    Testt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Testt record);

    int updateByPrimaryKey(Testt record);
}