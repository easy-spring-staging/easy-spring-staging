/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.pattern.mvc.simple.dao;

import com.easyspring.core.model.Model;
import com.easyspring.core.model.QueryParameter;
import com.easyspring.core.sercurity.AuthorizationUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dao基础接口  .
 *
 * <p>
 * Dao基础接口
 *
 * @author caobaoyu
 * @date 2020/5/15 14:37
 */
public interface BaseDao<K, M extends Model<K>> {

    /**
     * 通过主键查询详情信息 .
     *
     * <p>
     * 通过主键查询详情信息
     *
     * @param k 主键
     * @param u 用户模型
     * @return D 数据模型
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 14:59
     */
    M load(@Param(value = "k") K k, @Param(value = "u") AuthorizationUser u);

    /**
     * 通过多个条件查询列表
     *
     * <p>
     * 通过多个条件查询列表
     *
     * @param q 请求参数模型
     * @param u 用户模型
     * @return java.util.List<M> 数据List
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 15:04
     */
    List<M> query(@Param(value = "q") QueryParameter q, @Param(value = "u") AuthorizationUser u);

    /**
     * 通过主键集合查询列表数据 .
     *
     * <p>
     * 通过主键集合查询列表数据 .
     *
     * @param fn 字段名称
     * @param ks key数组
     * @param u  用户模型
     * @return java.util.List<M> 数据List
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 15:24
     */
    List<M> list(@Param(value = "fn") String fn, @Param(value = "ks") List<? extends Object> ks, @Param(value = "u") AuthorizationUser u);

    /**
     * 通过多个条件查询总记录数
     *
     * @param q 请求参数模型
     * @param u 用户模型
     * @return java.lang.Integer 总记录数
     * @Exception 数据库异常
     * @author caobaoyu
     * @date 2021/9/4 9:18
     */
    Integer count(@Param(value = "q") QueryParameter q, @Param(value = "u") AuthorizationUser u) throws Exception;

    /**
     * 添加数据 .
     *
     * <p>
     * 添加数据
     *
     * @param m 数据模型
     * @return Integer 影响数据数量
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 15:13
     */
    Integer insert(M m);

    /**
     * 通过主键删除数据 .
     *
     * <p>
     * 通过主键删除数据
     *
     * @param k 主键
     * @param u 用户模型
     * @return java.lang.Boolean 删除是否成功
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 15:17
     */
    Integer delete(@Param(value = "k") K k, @Param(value = "u") AuthorizationUser u);

    /**
     * 通过主键List删除数据 .
     *
     * <p>
     * 通过主键List删除数据 .
     *
     * @param ks 主键数组
     * @param u  用户模型
     * @return java.lang.Integer 删除记录数
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 15:24
     */
    Integer deleteMulti(@Param(value = "ks") List<K> ks, @Param(value = "u") AuthorizationUser u);

    /**
     * 修改数据 .
     *
     * <p>
     * 修改数据
     *
     * @param k 主键
     * @param m 数据模型
     * @param u 用户模型
     * @return java.lang.Boolean
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 15:28
     */
    Integer update(@Param(value = "k") K k, @Param(value = "m") M m, @Param(value = "u") AuthorizationUser u);

    /**
     * 修改数据 .
     *
     * <p>
     * 修改数据
     *
     * @param k 主键
     * @param m 数据模型
     * @param u 用户模型
     * @return java.lang.Boolean
     * @throws Exception 数据库异常
     * @author caobaoyu
     * @date 2020/5/15 15:28
     */
    Boolean updateAll(@Param(value = "k") K k, @Param(value = "m") M m, @Param(value = "u") AuthorizationUser u);
}
