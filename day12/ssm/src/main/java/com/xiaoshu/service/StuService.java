package com.xiaoshu.service;

import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.MajorMapper;
import com.xiaoshu.dao.StuMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Major;
import com.xiaoshu.entity.Stu;
import com.xiaoshu.entity.StuVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class StuService {

	@Autowired
	private StuMapper sm;

	@Autowired
	private MajorMapper mm;
	
	// 新增
	public void add(Stu t) throws Exception {
		sm.insert(t);
	};

	
	// 修改
	public void updateStu(Stu t) throws Exception {
		sm.updateByPrimaryKeySelective(t);
	};
	 

	// 删除
	public void deleteStu(Integer id) throws Exception {
		sm.deleteByPrimaryKey(id);
	};
	
	public PageInfo<StuVo> findStuPage(StuVo sVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<StuVo> userList = sm.findStu(sVo);
		PageInfo<StuVo> pageInfo = new PageInfo<StuVo>(userList);
		return pageInfo;
	}

	public List<Major> findMajor() {
		return mm.selectAll();
	}


	public List<StuVo> findExport(StuVo stuVo) {
		return sm.findStu(stuVo);
	}


	public List<StuVo> findEcharts() {
		// TODO Auto-generated method stub
		return sm.findEcharts();
	}
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private Destination queueTextDestination;
	
	//添加部门
	public void addDept(final Major major){
		mm.insert(major);
		
		//发送消息
		jmsTemplate.send(queueTextDestination,new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				String json = JSONObject.toJSONString(major);
				return session.createTextMessage(json);
			}
		});
		
		
	}
	
	
	


}
