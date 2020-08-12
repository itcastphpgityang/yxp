package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.AreaMapper;
import com.xiaoshu.dao.SchoolMapper;
import com.xiaoshu.entity.Area;
import com.xiaoshu.entity.QueryVo;
import com.xiaoshu.entity.School;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class SchoolService {

	@Autowired
	SchoolMapper userMapper;
	
	@Autowired
	AreaMapper am;

	// 查询所有
	public List<Area> findArea() throws Exception {
		return am.selectAll();
	};


	// 新增
	public void addUser(School t) throws Exception {
		userMapper.insert(t);
	};

	// 修改
	public void updateUser(School t) throws Exception {
		userMapper.updateByPrimaryKeySelective(t);
	};

/*	// 删除
	public void deleteUser(Integer id) throws Exception {
		userMapper.deleteByPrimaryKey(id);
	};*/


	public PageInfo<QueryVo> findUserPage(QueryVo user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<QueryVo> userList = userMapper.findSchool(user);
		PageInfo<QueryVo> pageInfo = new PageInfo<QueryVo>(userList);
		return pageInfo;
	}


	public List<QueryVo> findPage(QueryVo queryVo) {
		// TODO Auto-generated method stub
		return userMapper.findSchool(queryVo);
	}


	public School findSchool(String schoolname) {
		School school = new School();
		school.setSchoolname(schoolname);
		School school2 = userMapper.selectOne(school);
		return school2;
	}

	/*// 通过用户名判断是否存在，（新增时不能重名）
		public User existUserWithUserName(String userName) throws Exception {
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			criteria.andUsernameEqualTo(userName);
			List<User> userList = userMapper.selectByExample(example);
			return userList.isEmpty()?null:userList.get(0);
		};
*/
}
