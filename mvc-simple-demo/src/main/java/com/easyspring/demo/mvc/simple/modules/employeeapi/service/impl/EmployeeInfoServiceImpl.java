package com.easyspring.demo.mvc.simple.modules.employeeapi.service.impl;

import com.easyspring.core.pattern.mvc.simple.service.AbstractService;
import com.easyspring.core.pattern.mvc.simple.dao.BaseDao;
import com.easyspring.core.sercurity.AuthorizationUser;
import com.easyspring.demo.mvc.simple.modules.employeeapi.model.EmployeeInfo;
import com.easyspring.demo.mvc.simple.modules.employeeapi.dao.EmployeeInfoDao;
import com.easyspring.demo.mvc.simple.modules.employeeapi.service.EmployeeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class EmployeeInfoServiceImpl extends AbstractService<String, EmployeeInfo> implements EmployeeInfoService {

    @Resource
    private EmployeeInfoDao employeeInfoDao;


    @Override
    public BaseDao<String, EmployeeInfo> getDao() {
        return this.employeeInfoDao;
    }
}


