package com.easyspring.core.executor;

import com.easyspring.core.model.Model;
import com.easyspring.core.sercurity.AuthorizationUser;

@FunctionalInterface
public interface QueryDetailsPerExecutor<K, M extends Model<K>> extends QueryDetailsExecutor<K, M>{
}