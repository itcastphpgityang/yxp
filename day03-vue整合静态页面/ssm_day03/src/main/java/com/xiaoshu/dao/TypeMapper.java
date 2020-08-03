package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.Type;
import com.xiaoshu.entity.TypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TypeMapper extends BaseMapper<Type> {
    long countByExample(TypeExample example);

    int deleteByExample(TypeExample example);

    List<Type> selectByExample(TypeExample example);

    int updateByExampleSelective(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByExample(@Param("record") Type record, @Param("example") TypeExample example);
}