package com.easyspring.core.executor.remove;

import com.easyspring.core.sercurity.AuthorizationUser;

/**
 * 抽象单删除执行器
 * @param <K> 模型主键类型
 *
 * @author caobaoyu
 * @date 2022/10/1 23:10
 */
public interface RemoveExecutor<K> {

     /**
     * 删除附加执行器
     *
     * @param u 用户
     * @param k 模型主键
     *
     * @author caobaoyu
     * @date 2023/4/17 10:18
     */
    void execute(AuthorizationUser<?, ?, ?, ?> u, K k) throws Exception;
}