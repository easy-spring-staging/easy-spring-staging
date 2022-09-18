package com.easyspring.core.executor;

import com.easyspring.core.sercurity.AuthorizationUser;

@FunctionalInterface
public interface DeleteExecutor<K> {
    void execute(K k, AuthorizationUser u);
}