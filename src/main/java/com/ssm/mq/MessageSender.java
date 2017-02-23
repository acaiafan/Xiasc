package com.ssm.mq;


import javax.annotation.Resource;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;

public class MessageSender{
	
	private String destination="";
	
	@Resource(name="jmsQueueTemplate")
	private JmsTemplate queueSender;
	
	@Resource(name="jmsTopicTemplate")
	private JmsTemplate topicSender;
	
	

	public void topicSender(Object obj,String destination){
		topicSender.convertAndSend(destination, obj);
	}
	
	public void queueSender(Object obj,String destination){
		queueSender.convertAndSend(destination, obj);		
	}

}
