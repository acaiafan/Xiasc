package com.ssm.mq;

import java.io.Serializable;
import java.util.Map;

import javax.json.Json;

import com.alibaba.fastjson.JSONArray;

public class DefaultMessageDelegate implements MessageDelegate{

	@Override
	public void handleMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println("接受到一个String类型的消息");
		
	}

	@Override
	public void handleMessage(Map message) {
		// TODO Auto-generated method stub
		System.out.println("接受到一个Map 类型的消息");
	}

	@Override
	public void handleMessage(byte[] message) {
		// TODO Auto-generated method stub
		System.out.println("接受到一个byte 类型的消息");

	}

	@Override
	public void handleMessage(Serializable message) {
		// TODO Auto-generated method stub
		if(message instanceof Map){
			System.out.println("可以被转为map");
			System.out.println(JSONArray.toJSONString((Map)message));
		}
		System.out.println("接受到一个Serializable 类型的消息");
	}

}
