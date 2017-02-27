package com.ssm.shiro;

import java.io.Serializable;

public class Principal implements Serializable{
	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = 1L;
	//用户id
	private int userId;
	//用户名
	private String userName;
	//是否游客
	private String isAdmin;
	//是否管理员
	private String isTourist;
	
	public Principal(int userId,String userName,String isTourist, String isAdmin){
		this.userId=userId;
		this.userName = userName;
		this.isAdmin = isAdmin;
		this.isTourist = isTourist;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getIsTourist() {
		return isTourist;
	}
	public void setIsTourist(String isTourist) {
		this.isTourist = isTourist;
	}
	
	
	

}
