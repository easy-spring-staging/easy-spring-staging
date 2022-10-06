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
    void execute(K k, AuthorizationUser u);
}