package com.easyspring.demo.mvc.simple.modules.test.service;

import com.easyspring.core.pattern.mvc.simple.service.BaseService;
import com.easyspring.demo.mvc.simple.modules.test.model.EmployeeInfo;

public interface EmployeeInfoService extends BaseService<String, EmployeeInfo> {
    String id = "id";
    String accountId = "account_id";
    String employeeName = "employee_name";
    String entryTime = "entry_time";
}


