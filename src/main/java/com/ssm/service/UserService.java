package com.ssm.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.bean.Testt;
import com.ssm.dao.TestMapper;

@Service("UserService")
public class UserService implements IUserService {
	

    @Resource
    private TestMapper userDao;

	@Override
	public Testt getUserById(int userId) {
		// TODO Auto-generated method stub
		  return this.userDao.selectByPrimaryKey(userId);
	}

}
