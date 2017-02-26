package com.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.bean.Testt;
import com.ssm.bean.User;
import com.ssm.dao.TestMapper;
import com.ssm.service.IUserService;
import com.ssm.util.MD5Util;

@Service("UserService")
public class UserService implements IUserService {
	

    @Resource
    private TestMapper userDao;

	@Override
	public Testt getUserById(int userId) {
		// TODO Auto-generated method stub
		  return this.userDao.selectByPrimaryKey(userId);
	}

	@Override
	public User getUser(String username,String password) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setPassword(password);
		user.setUserName(username);
		return userDao.getUser(user);
	}

	@Override
	public int registUser(String name, String password,int age) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setAge(String.valueOf(age));
		user.setUserName(name);
		user.setPassword(MD5Util.MD5(password));
		return userDao.userRegist(user);
	}

	@Override
	public int checkUser(String userName) {
		// TODO Auto-generated method stub
		return userDao.ifUserExist(userName);
	}
	

}
