package com.easyspring.core.executor;

import com.easyspring.core.model.Model;

@FunctionalInterface
public interface EditAllPostExecutor<K, M extends Model<K>> extends EditAllExecutor<K, M>{
}