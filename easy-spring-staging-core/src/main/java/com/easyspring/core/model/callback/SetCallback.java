package com.easyspring.core.model.callback;

import com.easyspring.core.model.Model;


/**
 * 模型获设置回调
 *
 * @param <M> 模型类型
 * @param <V> 赋值类型
 */
@FunctionalInterface
public interface SetCallback<M extends Model, V> {
    void set(M m, V v);
}
