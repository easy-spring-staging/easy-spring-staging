package com.easyspring.core.model.callback;

import com.easyspring.core.model.Model;

@FunctionalInterface
public interface GetCallback<M extends Model, R> {
    R get(M m);
}
