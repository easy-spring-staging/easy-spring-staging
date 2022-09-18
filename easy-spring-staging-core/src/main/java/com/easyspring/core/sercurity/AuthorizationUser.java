/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.sercurity;

import java.util.List;

/**
 * 用户抽象接口
 * @param <AK> 账号主键数据类型
 * @param <ROK> 角色主键数据类型
 * @param <REK> 资源主键数据类型
 * @param <RTK> 资源类型的数据类型
 */
public interface AuthorizationUser <AK,ROK,REK,RTK>{
    String DEFAULT_USER_KEY_NAME = "user-key";

    // 账号ID
    AK getAccountId();
    // 用户名
    String getUserName();
    // 账号
    String getAccount();
    // 是否管理员
    Boolean isAdmin();
    // 获得角色ID
    List<ROK> getRole();

    // 通过资源类型获得该类型资源的资源ID
    List<REK> getResourceId(RTK resourceType);
}
