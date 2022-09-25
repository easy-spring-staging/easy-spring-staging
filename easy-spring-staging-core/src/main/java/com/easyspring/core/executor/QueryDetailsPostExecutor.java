package com.easyspring.core.executor;

import com.easyspring.core.model.Model;

@FunctionalInterface
public interface QueryDetailsPostExecutor<K, M extends Model<K>> extends QueryDetailsExecutor<K, M> {
}