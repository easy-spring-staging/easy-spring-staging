package com.easyspring.demo.mvc.simple.modules.campus.dao;

import com.easyspring.core.pattern.mvc.simple.dao.BaseDao;
import com.easyspring.demo.mvc.simple.modules.campus.model.AccountInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountInfoDao extends BaseDao<String, AccountInfo> {
}


