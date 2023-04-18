package com.easyspring.core.executor.query;

import com.easyspring.core.model.Model;
import com.easyspring.core.sercurity.AuthorizationUser;

/**
 * 抽象查询详情执行器
 * @param <K> 模型主键类型
 * @param <M> 模型类型
 *
 * @author caobaoyu
 * @date 2022/10/1 23:04
 */
public interface QueryDetailsExecutor<K, M extends Model<K>> {
    /**
     * 查询详情附加执行器
     *
     * @param u 用户
     * @param k 模型主键
     * @param m 模型
     *
     * @author caobaoyu
     * @date 2023/4/17 10:00
     */
    void execute(AuthorizationUser<?, ?, ?, ?> u, K k, M m) throws Exception;
}