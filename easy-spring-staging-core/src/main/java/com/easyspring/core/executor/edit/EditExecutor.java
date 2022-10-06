package com.easyspring.core.executor.edit;

import com.easyspring.core.model.Model;
import com.easyspring.core.sercurity.AuthorizationUser;

/**
 * 抽象部分修改执行器
 * @param <K> 模型主键类型
 * @param <M> 模型类型
 *
 * @author caobaoyu
 * @date 2022/10/1 23:02
 */
public interface EditExecutor<K, M extends Model<K>> {
    void execute(K k, AuthorizationUser u, M m);
}