package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.SchoolMapper;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.entity.QueryVo;
import com.xiaoshu.entity.School;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class StudentService {

	@Autowired
	StudentMapper userMapper;
	
	@Autowired
	SchoolMapper sc;

	// 查询所有
	public List<School> findSchool() throws Exception {
		return sc.selectAll();
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

	public PageInfo<QueryVo> findUserPage(QueryVo user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<QueryVo> userList = userMapper.selectAllStudent(user);
		PageInfo<QueryVo> pageInfo = new PageInfo<QueryVo>(userList);
		return pageInfo;
	}


}
