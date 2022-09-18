package com.easyspring.core.executor;

import com.easyspring.core.sercurity.AuthorizationUser;

import java.util.List;

@FunctionalInterface
public interface BatchDeleteExecutor<K> {
    void execute(List<K> ks, AuthorizationUser u);
}