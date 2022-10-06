package com.easyspring.demo.mvc.simple.modules.test.service;

import com.easyspring.core.pattern.mvc.simple.service.BaseService;
import com.easyspring.demo.mvc.simple.modules.test.model.AccountInfo;

public interface AccountInfoService extends BaseService<String, AccountInfo> {
    String id = "id";
    String accountName = "account_name";
    String gender = "gender";
    String createTime = "create_time";
}


