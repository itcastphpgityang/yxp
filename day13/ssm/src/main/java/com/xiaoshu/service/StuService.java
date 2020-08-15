package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.MajorMapper;
import com.xiaoshu.dao.StuMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Major;
import com.xiaoshu.entity.Stu;
import com.xiaoshu.entity.StuVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class StuService {

	@Autowired
	StuMapper sm;


	// 新增
	public void addStu(Stu s) throws Exception {
		sm.insert(s);
	};

	// 修改
	public void updateStu(Stu t) throws Exception {
		sm.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteStu(Integer id) throws Exception {
		sm.deleteByPrimaryKey(id);
	};


	public PageInfo<StuVo> findStuPage(StuVo stu, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StuVo> stuList = sm.findPage(stu);
		PageInfo<StuVo> pageInfo = new PageInfo<StuVo>(stuList);
		return pageInfo;
	}
	@Autowired
	private MajorMapper mm;
	public List<Major> findMajor() {
		// TODO Auto-generated method stub
		return mm.selectAll();
	}

	public List<StuVo> findEcharts() {
		// TODO Auto-generated method stub
		return sm.findEcharts();
	}

	public List<StuVo> findExcel(StuVo stu) {
		// TODO Auto-generated method stub
		return sm.findPage(stu);
	}
	@Autowired
	private RedisTemplate redisTemplate;
	//添加部门
	public void addMajor(Major major){
		mm.insert(major);
		redisTemplate.delete("majorList");
	}


}
