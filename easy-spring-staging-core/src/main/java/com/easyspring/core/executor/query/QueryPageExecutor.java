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
    /**
     * 查询列表附加执行器
     *
     * @param u 用户
     * @param q 查询参数模型
     * @param p 分页模型
     *
     * @author caobaoyu
     * @date 2023/4/17 10:00
     */
    void execute(AuthorizationUser<?, ?, ?, ?> u, QueryParameter q, Page<M> p) throws Exception;
}