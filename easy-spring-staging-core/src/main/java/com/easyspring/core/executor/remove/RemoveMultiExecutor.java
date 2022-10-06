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
    void execute(List<K> ks, AuthorizationUser u);
}