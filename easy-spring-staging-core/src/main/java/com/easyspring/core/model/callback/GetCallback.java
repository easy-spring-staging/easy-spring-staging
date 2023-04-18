package com.easyspring.core.model.callback;

import com.easyspring.core.model.Model;

/**
 * 模型获取值回调
 *
 * @param <M> 模型类型
 * @param <R> 返回值类型
 */
@FunctionalInterface
public interface GetCallback<M extends Model<?>, R> {
    R get(M m);
}
