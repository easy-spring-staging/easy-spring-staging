package com.easyspring.core.executor.add;

import com.easyspring.core.model.Model;
import com.easyspring.core.sercurity.AuthorizationUser;

/**
 * 抽象新增执行器
 * @param <K> 模型主键类型
 * @param <M> 模型类型
 *
 * @author caobaoyu
 * @date 2022/10/1 22:56
 */
public interface AddExecutor<K, M extends Model<K>> {
    /**
     *
     * 添加附加执行器
     *
     * @param u 用户
     * @param k 模型主键
     * @param m 模型
     *
     * @author caobaoyu
     * @date 2023/4/17 10:11
     */
    void execute(AuthorizationUser<?, ?, ?, ?> u, K k, M m) throws Exception;
}