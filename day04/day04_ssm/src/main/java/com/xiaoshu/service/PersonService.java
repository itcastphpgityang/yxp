package com.xiaoshu.service;

import java.util.List;

import javax.management.Query;

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
import com.xiaoshu.entity.QueryVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class PersonService {

	@Autowired
	CompanyMapper cm;
	
	@Autowired
	PersonMapper userMapper;

	public List<Company> findCompany() {
		// TODO Auto-generated method stub
		return cm.selectAll();
	}
	
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

	public PageInfo<QueryVo> findPersonPage(QueryVo qv, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<QueryVo> userList = userMapper.findAllPerson(qv);
		PageInfo<QueryVo> pageInfo = new PageInfo<QueryVo>(userList);
		return pageInfo;
	}


}
