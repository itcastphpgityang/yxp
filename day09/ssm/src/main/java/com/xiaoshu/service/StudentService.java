package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.CourseMapper;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.entity.Course;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentVo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class StudentService {
	@Autowired
	StudentMapper sm;
	
	@Autowired
	CourseMapper cm;
	
	@Autowired
	JedisPool jedisPool;
	
	public PageInfo<StudentVo> findUserPage(StudentVo sv, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<StudentVo> userList = sm.findAll(sv);
		PageInfo<StudentVo> pageInfo = new PageInfo<StudentVo>(userList);
		return pageInfo;
	}

	public List<Course> findCourse() {
		// TODO Auto-generated method stub
		return cm.selectAll();
	}

	public List<Student> findGrade() {
		// TODO Auto-generated method stub
		return sm.findGrade();
	}

	public void update(Student student) {
		sm.updateByPrimaryKey(student);
		
	}

	public void add(Student student) {
		sm.insert(student);
		
	}

	public void addCourse(Course course) {
		cm.insert(course);
		Course one = cm.selectOne(course);
		Jedis jedis = jedisPool.getResource();
		jedis.hset("添加专业:",one.getId()+"", one.toString());
	}

	public List<StudentVo> exp() {
		// TODO Auto-generated method stub
		return sm.findAll(null);
	}

	public List<StudentVo> getTj() {
		return sm.getTj();
	}
}
