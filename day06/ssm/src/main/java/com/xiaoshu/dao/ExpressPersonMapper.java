package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.ExpressPerson;
import com.xiaoshu.entity.ExpressPersonExample;
import com.xiaoshu.entity.QueryVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpressPersonMapper extends BaseMapper<ExpressPerson> {
    long countByExample(ExpressPersonExample example);

    int deleteByExample(ExpressPersonExample example);

    List<ExpressPerson> selectByExample(ExpressPersonExample example);

    int updateByExampleSelective(@Param("record") ExpressPerson record, @Param("example") ExpressPersonExample example);

    int updateByExample(@Param("record") ExpressPerson record, @Param("example") ExpressPersonExample example);

	List<QueryVo> findAllPerson(QueryVo qv);
}