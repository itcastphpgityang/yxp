package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.ContentMapper;
import com.xiaoshu.dao.ContentcategoryMapper;
import com.xiaoshu.entity.Content;
import com.xiaoshu.entity.ContentVo;
import com.xiaoshu.entity.Contentcategory;
import com.xiaoshu.entity.QueryVo;
@Service
public class ContentService {

	@Autowired
	ContentMapper userMapper;
	
	@Autowired
	ContentcategoryMapper contentcategoryMapper;

	// 查询所有
	public List<Contentcategory> findUser() throws Exception {
		return contentcategoryMapper.selectAll();
	};

	// 新增
	public void addUser(Content t) throws Exception {
		userMapper.insert(t);
	};

	// 修改
	public void updateUser(Content t) throws Exception {
		userMapper.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		userMapper.deleteByPrimaryKey(id);
	};

	public PageInfo<ContentVo> findUserPage(ContentVo user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<ContentVo> userList = userMapper.findContentVo(user);
		PageInfo<ContentVo> pageInfo = new PageInfo<ContentVo>(userList);
		return pageInfo;
	}

	public List<ContentVo> findEcharts() {
		// TODO Auto-generated method stub
		return userMapper.findEcharts();
	}


}
