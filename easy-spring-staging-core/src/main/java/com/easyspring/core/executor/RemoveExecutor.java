package com.easyspring.core.executor;

import com.easyspring.core.sercurity.AuthorizationUser;

public interface RemoveExecutor<K> {
    void execute(K k, AuthorizationUser u);
}