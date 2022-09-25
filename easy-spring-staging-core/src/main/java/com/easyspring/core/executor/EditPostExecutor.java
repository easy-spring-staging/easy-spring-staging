package com.easyspring.core.executor;

import com.easyspring.core.model.Model;

@FunctionalInterface
public interface EditPostExecutor<K, M extends Model<K>> extends EditExecutor<K, M>{
}