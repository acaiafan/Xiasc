package com.ssm.mq;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Repository;
@Repository(value="topicReceiver")
public class TopicListener implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}

}
