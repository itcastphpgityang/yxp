package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.dao.TeacherMapper;
import com.xiaoshu.entity.PersonVo;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentVo;
import com.xiaoshu.entity.Teacher;

@Service
public class StuService {

	@Autowired
	StudentMapper userMapper;
	@Autowired
	TeacherMapper teacherMapper;

	// 查询所有
	public List<Teacher> findAllStu() throws Exception {
		return teacherMapper.selectAll();
	};
	
	// 新增
	public void addUser(Student t) throws Exception {
		userMapper.insert(t);
	};

	// 修改
	public void updateUser(Student t) throws Exception {
		userMapper.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		userMapper.deleteByPrimaryKey(id);
	};

	public PageInfo<StudentVo> findUserPage(StudentVo user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StudentVo> userList = userMapper.findAllStu(user);
		PageInfo<StudentVo> pageInfo = new PageInfo<StudentVo>(userList);
		return pageInfo;
	}

	public List<StudentVo> findEcharts() {
		// TODO Auto-generated method stub
		return userMapper.findEcharts();
	}


}
