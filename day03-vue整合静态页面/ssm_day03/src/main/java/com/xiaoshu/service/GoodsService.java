package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.GoodsMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Goods;
import com.xiaoshu.entity.Type;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class GoodsService {

	@Autowired
	GoodsMapper gm;

	

	public PageInfo<Goods> findGoodsAll(Goods g ,int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Goods> goodsList = gm.queryAll(g);
		PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
		return pageInfo;
	}
	
	// 新增
	public void add(Goods g) throws Exception {
		gm.insert(g);
	};


	public List<Type> queryType(){
		return gm.queryType();
	}

	public void delete(Integer gId){
		gm.deleteByPrimaryKey(gId);
	}
	
	public void update(Goods g){
		gm.updateGoods(g);
	}
	
}
