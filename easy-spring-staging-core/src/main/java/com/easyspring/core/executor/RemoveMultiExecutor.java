package com.easyspring.core.executor;

import com.easyspring.core.sercurity.AuthorizationUser;

import java.util.List;

public interface RemoveMultiExecutor<K> {
    void execute(List<K> ks, AuthorizationUser u);
}