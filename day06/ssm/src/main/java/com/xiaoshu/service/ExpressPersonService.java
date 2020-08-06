package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.ExpressCompanyMapper;
import com.xiaoshu.dao.ExpressPersonMapper;
import com.xiaoshu.entity.ExpressCompany;
import com.xiaoshu.entity.ExpressPerson;
import com.xiaoshu.entity.QueryVo;
import com.xiaoshu.entity.User;

@Service
public class ExpressPersonService {

	@Autowired
	ExpressPersonMapper expressPersonMapper;
	
	@Autowired
	ExpressCompanyMapper expressCompanyMapper;

	// 查询所有
	public List<ExpressCompany> findExpressCompany() throws Exception {
		return expressCompanyMapper.selectAll();
	};

	// 新增
	public void addUser(ExpressPerson t) throws Exception {
		expressPersonMapper.insert(t);
	};

	// 修改
	public void updateUser(ExpressPerson t) throws Exception {
		expressPersonMapper.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		expressPersonMapper.deleteByPrimaryKey(id);
	};

	public PageInfo<QueryVo> findUserPage(QueryVo qv, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<QueryVo> userList = expressPersonMapper.findAllPerson(qv);
		PageInfo<QueryVo> pageInfo = new PageInfo<QueryVo>(userList);
		return pageInfo;
	}


}
