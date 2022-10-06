package com.easyspring.demo.mvc.simple.modules.campus.service;

import com.easyspring.core.pattern.mvc.simple.service.BaseService;
import com.easyspring.core.sercurity.AuthorizationUser;
import com.easyspring.demo.mvc.simple.modules.campus.model.EmployeeInfo;

public interface EmployeeInfoService extends BaseService<String, EmployeeInfo> {
    String id = "id";
    String accountId = "account_id";
    String employeeName = "employee_name";
    String entryTime = "entry_time";
}


