package com.easyspring.core.model.callback;

import com.easyspring.core.model.Model;

@FunctionalInterface
public interface SetCallback<M extends Model, V> {
    void set(M m, V v);
}
