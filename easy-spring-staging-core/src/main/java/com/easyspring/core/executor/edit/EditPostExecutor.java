package com.easyspring.core.executor.edit;

import com.easyspring.core.model.Model;

/**
 * 后置部分修改执行器
 * @param <K> 模型主键类型
 * @param <M> 模型类型
 *
 * @author caobaoyu
 * @date 2022/10/1 23:02
 */
@FunctionalInterface
public interface EditPostExecutor<K, M extends Model<K>> extends EditExecutor<K, M>{
}