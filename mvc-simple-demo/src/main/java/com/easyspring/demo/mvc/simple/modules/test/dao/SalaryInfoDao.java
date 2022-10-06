package com.easyspring.demo.mvc.simple.modules.test.dao;

import com.easyspring.core.pattern.mvc.simple.dao.BaseDao;
import com.easyspring.demo.mvc.simple.modules.test.model.SalaryInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalaryInfoDao extends BaseDao<String, SalaryInfo> {
}


