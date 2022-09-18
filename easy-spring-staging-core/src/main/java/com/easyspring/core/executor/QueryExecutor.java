package com.easyspring.core.executor;

import com.easyspring.core.model.Page;
import com.easyspring.core.model.QueryParameter;
import com.easyspring.core.model.Model;
import com.easyspring.core.sercurity.AuthorizationUser;

@FunctionalInterface
public interface QueryExecutor<K, M extends Model<K>> {
    void execute(QueryParameter q, AuthorizationUser u, Page<M> p);
}