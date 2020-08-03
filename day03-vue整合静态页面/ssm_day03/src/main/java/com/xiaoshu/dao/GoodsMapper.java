package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.Goods;
import com.xiaoshu.entity.GoodsExample;
import com.xiaoshu.entity.Type;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper extends BaseMapper<Goods> {

	public List<Goods> queryAll(Goods g);
	
	public void add(Goods g);

	public List<Type> queryType();

	public void updateGoods(Goods g);
}