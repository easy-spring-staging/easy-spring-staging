package com.easyspring.core.executor;

import com.easyspring.core.model.Model;
import com.easyspring.core.sercurity.AuthorizationUser;

@FunctionalInterface
public interface AddExecutor<K, M extends Model<K>> {
    void execute(K k, AuthorizationUser u, M m);
}