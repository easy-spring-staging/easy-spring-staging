package com.easyspring.core.executor.query;

import com.easyspring.core.model.Page;
import com.easyspring.core.model.QueryParameter;
import com.easyspring.core.model.Model;
import com.easyspring.core.sercurity.AuthorizationUser;

/**
 * 抽象查询列表执行器
 * @param <K> 模型主键类型
 * @param <M> 模型类型
 *
 * @author caobaoyu
 * @date 2022/10/1 23:06
 */
@FunctionalInterface
public interface QueryPageExecutor<K, M extends Model<K>> {
    void execute(QueryParameter q, AuthorizationUser u, Page<M> p);
}