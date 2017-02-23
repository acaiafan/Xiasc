package com.ssm.mq;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Repository;

@Repository(value="queueReceiver")
public class QueuesListener implements MessageListener{
	

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		//News news =(News)((ObjectMessage)message).getObject();
//		System.out.println(news.getnContent());
//		String result = testService.getNewsMessage(news);
//		System.out.println(result);
	}
	
}
