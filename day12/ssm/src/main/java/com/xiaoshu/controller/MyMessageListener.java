package com.xiaoshu.controller;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshu.entity.Major;

public class MyMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {

		TextMessage textMessage = (TextMessage) message;
		
		try {
			String text = textMessage.getText();
			Major major = JSONObject.parseObject(text,Major.class);
			System.out.println("接收到的消息是-------------"+major);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	

}
