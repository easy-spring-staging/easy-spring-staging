package com.easyspring.core.executor;

import com.easyspring.core.model.Model;
import com.easyspring.core.sercurity.AuthorizationUser;

@FunctionalInterface
public interface EditPerExecutor<K, M extends Model<K>> extends EditExecutor<K, M>{
}