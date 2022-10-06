package com.easyspring.core.executor.query;

import com.easyspring.core.model.Model;

/**
 * 前置查询列表执行器
 * @param <K> 模型主键类型
 * @param <M> 模型类型
 *
 * @author caobaoyu
 * @date 2022/10/1 23:06
 */
public interface QueryPagePerExecutor<K, M extends Model<K>> extends QueryPageExecutor<K, M>{
}