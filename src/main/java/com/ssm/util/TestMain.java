package com.ssm.util;

import org.apache.log4j.Logger;

import com.ssm.bean.People;

public class TestMain {
	
	
	public static void main(String[] args) {
		Logger log = Logger.getLogger(TestMain.class);
		People people = new People();
		people.setSex("男");
		System.out.println(BeanUtil.checkEmpty(people));
	}

}
