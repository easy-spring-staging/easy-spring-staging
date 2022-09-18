package com.easyspring.core.callback;

import com.easyspring.core.model.Model;

@FunctionalInterface
public interface SetCallback<M extends Model, T> {
    void set(M m, T t);
}
