package com.ssm.mq;

public class AQueueListener {
	public void handleMessage(String message) {  
        System.out.println("ConsumerListener通过handleMessage接收到一个纯文本消息，消息内容是：" + message);  
    }  
      
    public void receiveMessage(String message) {  
        System.out.println("ConsumerListener通过receiveMessage接收到一个纯文本消息，消息内容是：" + message);  
    }  
}
