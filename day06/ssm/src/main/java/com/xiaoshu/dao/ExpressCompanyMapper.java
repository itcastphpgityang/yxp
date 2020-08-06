package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.ExpressCompany;
import com.xiaoshu.entity.ExpressCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpressCompanyMapper extends BaseMapper<ExpressCompany> {
    long countByExample(ExpressCompanyExample example);

    int deleteByExample(ExpressCompanyExample example);

    List<ExpressCompany> selectByExample(ExpressCompanyExample example);

    int updateByExampleSelective(@Param("record") ExpressCompany record, @Param("example") ExpressCompanyExample example);

    int updateByExample(@Param("record") ExpressCompany record, @Param("example") ExpressCompanyExample example);
}