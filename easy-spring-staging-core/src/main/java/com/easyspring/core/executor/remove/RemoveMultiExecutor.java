package com.easyspring.core.executor.remove;

import com.easyspring.core.sercurity.AuthorizationUser;

import java.util.List;


/**
 * 抽象多删除执行器
 * @param <K> 模型主键类型
 *
 * @author caobaoyu
 * @date 2022/10/1 23:13
 */
public interface RemoveMultiExecutor<K> {
    /**
     /**
     * 多删除附加执行器
     *
     * @param u 用户
     * @param ks 模型主键list
     *
     * @author caobaoyu
     * @date 2023/4/17 10:29
     */
    void execute(AuthorizationUser<?, ?, ?, ?> u, List<K> ks) throws Exception;
}