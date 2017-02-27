package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.AuthDao;
import com.ssm.service.AuthService;

@Service(value="authService")
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private AuthDao authDao;

	@Override
	public List<String> getUserRoleById(int id) {
		// TODO Auto-generated method stub
		return authDao.getRoleById(id);
	}

}
