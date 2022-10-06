package com.easyspring.core.executor.edit;

import com.easyspring.core.model.Model;

/**
 * 前置完全修改执行器
 * @param <K> 模型主键类型
 * @param <M> 模型类型
 *
 * @author caobaoyu
 * @date 2022/10/1 23:00
 */
@FunctionalInterface
public interface EditAllPerExecutor<K, M extends Model<K>> extends EditAllExecutor<K, M>{
}