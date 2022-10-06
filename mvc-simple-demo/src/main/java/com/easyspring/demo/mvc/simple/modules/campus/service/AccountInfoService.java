package com.easyspring.demo.mvc.simple.modules.campus.service;

import com.easyspring.core.pattern.mvc.simple.service.BaseService;
import com.easyspring.core.sercurity.AuthorizationUser;
import com.easyspring.demo.mvc.simple.modules.campus.model.AccountInfo;

public interface AccountInfoService extends BaseService<String, AccountInfo> {
    String id = "id";
    String accountName = "account_name";
    String gender = "gender";
    String createTime = "create_time";
}


