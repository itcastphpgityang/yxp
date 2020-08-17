package com.xiaoshu.service;

import java.util.Date;
import java.util.List;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.BankMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.entity.Bank;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.PersonVo;

@Service
public class PersonService {
	@Autowired
	private PersonMapper pm;
	
	@Autowired
	private BankMapper bm;
	
	public PageInfo<PersonVo> findUserPage(PersonVo pv, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<PersonVo> userList = pm.findAll(pv);
		PageInfo<PersonVo> pageInfo = new PageInfo<PersonVo>(userList);
		return pageInfo;
	}

	public List<Bank> findBank() {
		// TODO Auto-generated method stub
		return bm.selectAll();
	}

	public void deleteUser(int parseInt) {
		pm.deleteByPrimaryKey(parseInt);
		
	}

	public void update(Person p) {
		pm.updateByPrimaryKey(p);
		
	}

	public void add(Person p) {
		pm.insert(p);
		
	}

	public List<PersonVo> exp() {
		// TODO Auto-generated method stub
		return pm.findAll(null);
	}
	@Autowired
	Destination queueTextDestination;
	
	@Autowired
	JmsTemplate jmsTemplate;
	public void addBank(Bank b) {
		b.setCreatetime(new Date());
		bm.insert(b);
		Bank bank = new Bank();
		bank.setbName(b.getbName());
		
		Bank one = bm.selectOne(bank);
		System.out.println(one);
		jmsTemplate.convertAndSend(queueTextDestination,JSONObject.toJSONString(one));
		
	}

	public List<PersonVo> getTj() {
		// TODO Auto-generated method stub
		return pm.getTj();
	}
}
