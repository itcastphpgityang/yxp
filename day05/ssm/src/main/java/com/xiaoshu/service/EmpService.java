package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.DeptMapper;
import com.xiaoshu.dao.EmpMapper;
import com.xiaoshu.entity.Dept;
import com.xiaoshu.entity.Emp;
import com.xiaoshu.entity.EmpExample;
import com.xiaoshu.entity.EmpExample.Criteria;
import com.xiaoshu.entity.EmpVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;

@Service
public class EmpService {

	@Autowired
	EmpMapper empMapper;
	
	@Autowired
	DeptMapper deptMapper;

	// 查询所有
	public List<Dept> findEmp() throws Exception {
		return deptMapper.selectAll();
	};


	// 新增
	public void addEmp(Emp t) throws Exception {
		empMapper.insert(t);
	};

	// 修改
	public void updateEmp(Emp t) throws Exception {
		empMapper.updateByPrimaryKeySelective(t);
	};

	
	// 删除
	public void deleteEmp(Integer id) throws Exception {
		empMapper.deleteByPrimaryKey(id);
	};

	// 通过用户名判断是否存在，（新增时不能重名）
	public Emp existUserWithEmpName(String userName) throws Exception {
		EmpExample empExample = new EmpExample();
		Criteria criteria = empExample.createCriteria();
		criteria.andENameEqualTo(userName);
		List<Emp> userList = empMapper.selectByExample(empExample);
		return userList.isEmpty()?null:userList.get(0);
	};

	public PageInfo<EmpVo> findEmpPage(EmpVo emp, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<EmpVo> userList = empMapper.findEmp(emp);
		PageInfo<EmpVo> pageInfo = new PageInfo<EmpVo>(userList);
		return pageInfo;
	}

}
