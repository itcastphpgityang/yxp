package com.xiaoshu.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshu.entity.Bank;

public class MyMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage massage= (TextMessage) message;
			String text = massage.getText();
			Bank b = JSONObject.parseObject(text, Bank.class);
			System.out.println("银行信息:"+b.toString());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
