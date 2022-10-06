package com.easyspring.core.executor.remove;

/**
 * 后置多删除执行器
 * @param <K> 模型主键类型
 *
 * @author caobaoyu
 * @date 2022/10/1 23:13
 */
@FunctionalInterface
public interface RemoveMultiPostExecutor<K> extends RemoveMultiExecutor<K>{
}