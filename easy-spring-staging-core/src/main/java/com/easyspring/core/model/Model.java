/**
 * Copyright(C) 2021 Company:easy-spring-staging Co.
 */
package com.easyspring.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 模型接口
 *
 * @param <K> 模型主键类型
 * @author caobaoyu
 * @date 2022/10/1 23:32
 */
public interface Model<K> extends Serializable {

    @JsonIgnore
    K getKey();
}
