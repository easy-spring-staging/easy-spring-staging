/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.design.mvc.simple.service;

import com.easyspring.core.model.AbstractModel;
import com.easyspring.core.model.Page;
import com.easyspring.core.model.QueryParameter;
import com.easyspring.core.executor.*;
import com.easyspring.core.sercurity.AuthorizationUser;

import java.util.List;

/**
 * Service基础接口  .
 *
 * <p>
 * Service基础接口
 *
 * @author caobaoyu
 * @date 2020/5/15 15:36
 */
public interface BaseService<K, M extends AbstractModel<K>> {

    /**
     * 通过主键查询详情信息 .
     *
     * <p>
     * 通过主键查询详情信息
     *
     * @param k 主键
     * @param u 鉴权用户模型
     * @param executors 执行器
     * @return M 数据模型
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 14:59
     */
    M load(K k, AuthorizationUser u, DetailsExecutor<K, M>... executors) throws Exception;


    /**
     * @param q 查询参数
     * @param u 鉴权用户模型
     * @param executors 执行器
     * @return Page<M> 分页数据模型
     * @throws Exception
     */
    Page<M> query(QueryParameter q, AuthorizationUser u, QueryExecutor<K, M>... executors) throws Exception;

    /**
     * 通过主键集合查询列表数据  .
     *
     * <p>
     * 通过主键集合查询列表数据  .
     *
     * @param ks 字段名称
     * @param ks key List
     * @param u  鉴权用户模型
     * @return List<M> 数据列表
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 15:24
     */
    List<M> list(String fn,List<? extends Object> ks, AuthorizationUser u) throws Exception;


    /**
     * 通过多个条件查询总记录数
     *
     * @param q 请求参数模型
     * @param u 鉴权用户模型
     * @return java.lang.Integer 总记录数
     * @Exception 数据库异常
     * @autho caobaoyu
     * @date 2021/9/4 9:18
     */
    Integer count(QueryParameter q, AuthorizationUser u) throws Exception;

    /**
     * 添加数据 .
     *
     * <p>
     * 添加数据
     *
     * @param m 数据模型
     * @param u 鉴权用户模型
     * @param executors 执行器
     * @return K 主键
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 15:13
     */
    K add(M m, AuthorizationUser u, AddExecutor<K, M >... executors) throws Exception;


    /**
     * 通过主键删除数据 .
     *
     * <p>
     * 通过主键删除数据
     *
     * @param k 主键
     * @param u 鉴权用户模型
     * @param executors 执行器
     * @return java.lang.Boolean 删除是否成功
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 15:17
     */
    Boolean delete(K k, AuthorizationUser u, DeleteExecutor<K>... executors) throws Exception;


    /**
     * 通过主键List删除数据 .
     *
     * <p>
     * 通过主键List删除数据 .
     *
     * @param ks 主键List
     * @param u  鉴权用户模型
     * @param executors 执行器
     * @return java.lang.Integer 删除记录数
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 15:24
     */
    Integer batchDelete(List<K> ks, AuthorizationUser u, BatchDeleteExecutor<K>... executors) throws Exception;

    /**
     * 修改数据 .
     *
     * <p>
     * 修改数据,字段为空不修改
     *
     * @param k 主键
     * @param m 数据模型
     * @param u 鉴权用户模型
     * @param executors 执行器
     * @return java.lang.Boolean
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 15:28
     */
    Boolean update(K k, M m, AuthorizationUser u, UpdateExecutor<K, M>... executors) throws Exception;

    /**
     * 修改数据 .
     *
     * <p>
     * 修改数据,字段为空修改为空
     *
     * @param k 主键
     * @param m 数据模型
     * @param u 鉴权用户模型
     * @param executors 执行器
     * @return java.lang.Boolean
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 15:28
     */
    Boolean updateNull(K k, M m, AuthorizationUser u, UpdateNullExecutor<K, M>... executors) throws Exception;

}
