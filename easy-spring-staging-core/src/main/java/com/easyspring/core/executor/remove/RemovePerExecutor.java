package com.easyspring.core.executor.remove;

/**
 * 前置单删除执行器
 * @param <K> 模型主键类型
 *
 * @author caobaoyu
 * @date 2022/10/1 23:10
 */
@FunctionalInterface
public interface RemovePerExecutor<K> extends RemoveExecutor <K>{
}