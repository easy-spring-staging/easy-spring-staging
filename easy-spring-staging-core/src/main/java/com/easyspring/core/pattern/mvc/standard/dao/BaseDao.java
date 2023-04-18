/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.pattern.mvc.standard.dao;

import com.easyspring.core.model.QueryParameter;
import com.easyspring.core.model.po.AbstractPO;
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
public interface BaseDao<K, M extends AbstractPO<K>> {

  /**
   * 通过主键查询详情信息 .
   *
   * <p>
   * 通过主键查询详情信息
   *
   * @param u 鉴权用户模型
   * @param k 主键
   * @return D 数据模型
   * @throws Exception 数据库异常
   * @author caobaoyu
   * @date 2020/5/15 14:59
   */
  M load(@Param(value = "u") AuthorizationUser<?, ?, ?, ?> u, @Param(value = "k") K k);

  /**
   * 通过多个条件查询列表
   *
   * <p>
   * 通过多个条件查询列表
   *
   * @param u 鉴权用户模型
   * @param q 请求参数模型
   * @return java.util.List<M> 数据List
   * @throws Exception 数据库异常
   * @author caobaoyu
   * @date 2020/5/15 15:04
   */
  List<M> query(@Param(value = "u") AuthorizationUser<?, ?, ?, ?> u, @Param(value = "q") QueryParameter q);

  /**
   * 通过主键集合查询列表数据 .
   *
   * <p>
   * 通过主键集合查询列表数据 .
   *
   * @param u  鉴权用户模型
   * @param fn 字段名称
   * @param ks key数组
   * @return java.util.List<M> 数据List
   * @throws Exception 数据库异常
   * @author caobaoyu
   * @date 2020/5/15 15:24
   */
  List<M> list(@Param(value = "u") AuthorizationUser<?, ?, ?, ?> u, @Param(value = "fn") String fn, @Param(value = "ks") List<?> ks) throws Exception;

  /**
   * 通过多个条件查询总记录数
   *
   * @param u 鉴权用户模型
   * @param q 请求参数模型
   * @return java.lang.Integer 总记录数
   * @throws Exception 数据库异常
   * @author caobaoyu
   * @date 2021/9/4 9:18
   */
  Integer count(@Param(value = "u") AuthorizationUser<?, ?, ?, ?> u, @Param(value = "q") QueryParameter q) throws Exception;

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
   * @param u 鉴权用户模型
   * @param k 主键
   * @return java.lang.Boolean 删除是否成功
   * @throws Exception 数据库异常
   * @author caobaoyu
   * @date 2020/5/15 15:17
   */
  Integer delete(@Param(value = "u") AuthorizationUser<?, ?, ?, ?> u, @Param(value = "k") K k);

  /**
   * 通过主键List删除数据 .
   *
   * <p>
   * 通过主键List删除数据 .
   *
   * @param u  鉴权用户模型
   * @param ks 主键数组
   * @return java.lang.Integer 删除记录数
   * @throws Exception 数据库异常
   * @author caobaoyu
   * @date 2020/5/15 15:24
   */
  Integer deleteMulti(@Param(value = "u") AuthorizationUser<?, ?, ?, ?> u, @Param(value = "ks") List<K> ks);

  /**
   * 修改数据 .
   *
   * <p>
   * 修改数据
   *
   * @param u 鉴权用户模型
   * @param k 主键
   * @param m 数据模型
   * @return java.lang.Boolean
   * @throws Exception 数据库异常
   * @author caobaoyu
   * @date 2020/5/15 15:28
   */
  Integer update(@Param(value = "u") AuthorizationUser<?, ?, ?, ?> u, @Param(value = "k") K k, @Param(value = "m") M m);

  /**
   * 修改数据 .
   *
   * <p>
   * 修改数据
   *
   * @param u 鉴权用户模型
   * @param k 主键
   * @param m 数据模型
   * @return java.lang.Boolean
   * @throws Exception 数据库异常
   * @author caobaoyu
   * @date 2020/5/15 15:28
   */
  Boolean updateAll(@Param(value = "u") AuthorizationUser<?, ?, ?, ?> u, @Param(value = "k") K k, @Param(value = "m") M m);
}
