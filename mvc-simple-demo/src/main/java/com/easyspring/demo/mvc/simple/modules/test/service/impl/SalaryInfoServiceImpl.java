package com.easyspring.demo.mvc.simple.modules.test.service.impl;

import com.easyspring.core.pattern.mvc.simple.service.AbstractService;
import com.easyspring.core.pattern.mvc.simple.dao.BaseDao;
import com.easyspring.core.sercurity.AuthorizationUser;
import com.easyspring.demo.mvc.simple.modules.test.model.SalaryInfo;
import com.easyspring.demo.mvc.simple.modules.test.dao.SalaryInfoDao;
import com.easyspring.demo.mvc.simple.modules.test.service.SalaryInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SalaryInfoServiceImpl extends AbstractService<String, SalaryInfo> implements SalaryInfoService {

  @Resource
  private SalaryInfoDao salaryInfoDao;


  @Override
  public BaseDao<String, SalaryInfo> getDao() {
    return this.salaryInfoDao;
  }
}


