package com.easyspring.demo.mvc.simple.modules.test.dao;

import com.easyspring.core.pattern.mvc.simple.dao.BaseDao;
import com.easyspring.demo.mvc.simple.modules.test.model.EmployeeInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeInfoDao extends BaseDao<String, EmployeeInfo> {
}


