package com.xiaoshu.service;

import java.util.Date;
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
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.dao.TeacherMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentVo;
import com.xiaoshu.entity.Teacher;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class StudentService {

	@Autowired
	private StudentMapper sm;
	
	@Autowired
	private TeacherMapper tm;
	
	
	// 新增
	public void add(Student t) throws Exception {
		t.setCreatetime(new Date());
		sm.insert(t);
	};


	// 修改
	public void updateStudent(Student t) throws Exception {
		sm.updateByPrimaryKeySelective(t);
	};
/*
	// 删除
	public void deleteUser(Integer id) throws Exception {
		userMapper.deleteByPrimaryKey(id);
	};
*/
	

	public PageInfo<StudentVo> findStudentPage(StudentVo sVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<StudentVo> studentList = sm.findStudent(sVo);
		PageInfo<StudentVo> pageInfo = new PageInfo<StudentVo>(studentList);
		return pageInfo;
	}
	public List<Teacher> findTeacher() {
		// TODO Auto-generated method stub
		return tm.selectAll();
	}

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination queueTextDestination;
	//添加部门
	public void addDept(final Teacher t) {
		t.setCreatetime(new Date());
		tm.insert(t);
		//发送消息
		jmsTemplate.send(queueTextDestination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				String json = JSONObject.toJSONString(t);
				return session.createTextMessage(json);
			}
		});
	}


	public List<StudentVo> findEcharts() {
		// TODO Auto-generated method stub
		return sm.findEcharts();
	}
	
	
	
}