package com.easyspring.core.executor.remove;

/**
 * 后置单删除执行器
 * @param <K> 模型主键类型
 *
 * @author caobaoyu
 * @date 2022/10/1 23:10
 */
@FunctionalInterface
public interface RemovePostExecutor<K> extends RemoveExecutor <K>{
}