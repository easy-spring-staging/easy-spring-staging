package com.easyspring.core.executor.remove;


/**
 * 前置多删除执行器
 * @param <K> 模型主键类型
 *
 * @author caobaoyu
 * @date 2022/10/1 23:13
 */
@FunctionalInterface
public interface RemoveMultiPerExecutor<K> extends RemoveMultiExecutor<K>{
}