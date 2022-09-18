package com.easyspring.core.callback;

import com.easyspring.core.model.Model;

@FunctionalInterface
public interface GetCallback<M extends Model, T> {
    T get(M m);
}
