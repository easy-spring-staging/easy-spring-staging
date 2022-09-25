package com.easyspring.core.executor;

import com.easyspring.core.model.Model;

@FunctionalInterface
public interface AddPostExecutor<K, M extends Model<K>> extends AddExecutor<K, M>{
}