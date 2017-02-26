package com.ssm.service;

import com.ssm.bean.Testt;
import com.ssm.bean.User;

public interface IUserService {

	public Testt getUserById(int userId);
	
	User getUser(String username,String password);
	
	int registUser(String name,String password,int age);
	
	int checkUser(String userName);

}
