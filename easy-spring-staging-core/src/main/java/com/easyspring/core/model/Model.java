/**
 * Copyright(C) 2021 Company:easy-spring-staging Co.
 */
package com.easyspring.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 抽象DTO.
 *
 * @author caobaoyu
 * @create 2021-09-03 17:10
 **/
public interface Model<K> extends Serializable {

    @JsonIgnore
    K getKey();
}
