package com.easyspring.core.executor;

import com.easyspring.core.model.Model;
import com.easyspring.core.sercurity.AuthorizationUser;

@FunctionalInterface
public interface EditAllPerExecutor<K, M extends Model<K>> extends EditAllExecutor<K, M>{
}