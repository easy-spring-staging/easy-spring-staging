package com.easyspring.core.executor.add;

import com.easyspring.core.model.Model;

/**
 * 前置新增执行器
 * @param <K> 模型主键类型
 * @param <M> 模型类型
 *
 * @author caobaoyu
 * @date 2022/10/1 22:58
 */
@FunctionalInterface
public interface AddPerExecutor<K, M extends Model<K>> extends AddExecutor<K, M>{
}