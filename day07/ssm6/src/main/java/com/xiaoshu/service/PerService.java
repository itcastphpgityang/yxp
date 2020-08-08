package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.CompanyMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Company;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.PersonVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class PerService {

	@Autowired
	CompanyMapper cm;
	@Autowired
	PersonMapper userMapper;
	
	// 查询所有
	public List<Company> findCompany() throws Exception {
		return cm.selectAll();
	};

	// 新增
	public void addUser(Person t) throws Exception {
		userMapper.insert(t);
	};

	// 修改
	public void updateUser(Person t) throws Exception {
		userMapper.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		userMapper.deleteByPrimaryKey(id);
	};

	public PageInfo<PersonVo> findUserPage(PersonVo user, int pageNum, int pageSize) {
	
		List<PersonVo> userList = userMapper.findPerson(user);
		PageInfo<PersonVo> pageInfo = new PageInfo<PersonVo>(userList);
		return pageInfo;
	}


}
