/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.pattern.mvc.simple.controller;

import java.util.Map;

/**
 * 功能简介  .
 *
 * <p>
 * 功能详细描述
 *
 * @author caobaoyu
 * @date 2020/5/16 23:51
 */
public interface BaseRest {
    /**
     * 获取排序字段映射
     *
     * @return 字段映射
     * @author caobaoyu
     * @date 2020/5/16 23:53
     */
    Map<String, String> getColumnMap();

}
