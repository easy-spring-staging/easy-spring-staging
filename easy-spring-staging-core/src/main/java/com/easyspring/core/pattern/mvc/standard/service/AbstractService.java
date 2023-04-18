/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.pattern.mvc.standard.service;

import com.easyspring.core.executor.add.AddExecutor;
import com.easyspring.core.executor.add.AddPerExecutor;
import com.easyspring.core.executor.add.AddPostExecutor;
import com.easyspring.core.executor.edit.*;
import com.easyspring.core.executor.query.*;
import com.easyspring.core.executor.remove.*;
import com.easyspring.core.model.Model;
import com.easyspring.core.model.Page;
import com.easyspring.core.model.QueryParameter;
import com.easyspring.core.model.callback.GetCallback;
import com.easyspring.core.model.callback.SetCallback;
import com.easyspring.core.model.convert.ServiceConverter;
import com.easyspring.core.model.dto.AbstractDTO;
import com.easyspring.core.model.po.AbstractPO;
import com.easyspring.core.pattern.mvc.standard.dao.BaseDao;
import com.easyspring.core.sercurity.AuthorizationUser;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service基础实现 .
 *
 * <p>
 * Service基础实现
 *
 * @author caobaoyu
 * @date 2020/5/15 15:46
 */
public abstract class AbstractService<K, M extends AbstractDTO<K>, P extends AbstractPO<K>>{

    public abstract BaseDao<K, P> getDao();

    public abstract ServiceConverter<K, M,P> getConverter();

    /**
     * 执行查询详情执行执行器
     *
     * @param clazz     执行器类型
     * @param k         模型主键
     * @param u         用户
     * @param m         模型
     * @param executors 执行器列表
     * @throws Exception 异常
     * @author caobaoyu
     * @date 2022/10/02 14:02
     */
    private void queryDetailsExecute(Class<? extends QueryDetailsExecutor> clazz, K k, AuthorizationUser<?, ?, ?, ?> u, M m, QueryDetailsExecutor<K, M>... executors) throws Exception {
        if (executors != null) {
            for (QueryDetailsExecutor<K, M> executor : executors) {
                if (executor == null || QueryDetailsExecutor.class == executor.getClass().getInterfaces()[0]) {
                    throw new Exception("执行器不能空，且不能执行QueryDetailsExecutor,请使用QueryDetailsExecutor的子接口！");
                } else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(u, k, m);
                    }
                }
            }
        }
    }

    @Transactional(readOnly = true)
    public M queryDetails(K k, AuthorizationUser<?, ?, ?, ?> u, QueryDetailsExecutor<K, M>... executors) throws Exception {
        M m ;
        queryDetailsExecute(QueryDetailsPerExecutor.class, k, u, null, executors);
        m = getConverter().poToDto(getDao().load(u, k));
        queryDetailsExecute(QueryDetailsPostExecutor.class, k, u, m, executors);
        return m;
    }

    /**
     * 执行查询列表执行执行器
     *
     * @param clazz  执行器类型
     * @param q 查询参数模型
     * @param u 用户
     * @param p 分页模型
     * @param executors
     * @throws Exception
     * @author caobaoyu
     * @date 2022/10/02 14:04
     */
    private void queryPageExecute(Class<? extends QueryPageExecutor> clazz, QueryParameter q, AuthorizationUser<?, ?, ?, ?> u, Page<M> p, QueryPageExecutor<K, M>... executors) throws Exception {
        if (executors != null) {
            for (QueryPageExecutor<K, M> executor : executors) {
                if (clazz.isInstance(executor)) {
                    executor.execute(u, q, p);
                }
            }
        }
    }

    @Transactional(readOnly = true)
    public Page<M> queryPage(QueryParameter q, AuthorizationUser<?, ?, ?, ?> u, QueryPageExecutor<K, M>... executors) throws Exception {
        Page<M> page;
        q.initPage();
        queryPageExecute(QueryPagePerExecutor.class, q, u, null, executors);
        if (q.isPage()) {
            com.github.pagehelper.Page<M> pageHelperPage = PageHelper.startPage(q.getPageModel().getPageNo(), q.getPageModel().getPageSize()).doSelectPage(() -> getDao().query(u, q));
            page = new Page<>(pageHelperPage);
        } else {
            List<M> list = getConverter().poToDto(getDao().query(u, q));
            page = new Page<>(list);
        }
        queryPageExecute(QueryPagePostExecutor.class, q, u, page, executors);
        return page;
    }

    @Transactional(readOnly = true)
    public List<M> queryList(String fn, List<?> ks, AuthorizationUser<?, ?, ?, ?> u) throws Exception {
        List<M> dataList;
        dataList = getConverter().poToDto(getDao().list(u,fn, ks));
        return dataList;
    }

    @Transactional(readOnly = true)
    public Integer count(QueryParameter q, AuthorizationUser<?, ?, ?, ?> u) throws Exception {
        return getDao().count(u, q);
    }

    /**
     * 执行新增执行执行器
     *
     * @param clazz     执行器类型
     * @param k         模型主键
     * @param u         用户
     * @param m         模型
     * @param executors 执行器列表
     * @throws Exception
     * @author caobaoyu
     * @date 2022/10/02 14:06
     */
    private void addExecute(Class<? extends AddExecutor> clazz, K k, AuthorizationUser<?, ?, ?, ?> u, M m, AddExecutor<K, M>... executors) throws Exception {
        if (executors != null) {
            for (AddExecutor<K, M> executor : executors) {
                if (clazz.isInstance(executor)) {
                    executor.execute(u, k, m);
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public K add(M m, AuthorizationUser<?, ?, ?, ?> u, AddExecutor<K, M>... executors) throws Exception {
        K k = null;
        addExecute(AddPerExecutor.class, null, u, m, executors);
        P p = getConverter().dtoToPo(m);
        if (getDao().insert(p) > 0) {
            k = p.getKey();
        }
        addExecute(AddPostExecutor.class, k, u, m, executors);
        return k;
    }

    /**
     * 执行单删除执行执行器
     *
     * @param clazz     执行器类型
     * @param k         模型主键
     * @param u         用户
     * @param executors 执行器列表
     * @throws Exception
     * @author caobaoyu
     * @date 2022/10/02 14:07
     */
    private void removeExecute(Class<? extends RemoveExecutor> clazz, K k, AuthorizationUser<?, ?, ?, ?> u, RemoveExecutor<K>... executors) throws Exception {
        if (executors != null) {
            for (RemoveExecutor<K> executor : executors) {
                if (clazz.isInstance(executor)) {
                    executor.execute(u, k);
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean remove(AuthorizationUser<?, ?, ?, ?> u, K k, RemoveExecutor<K>... executors) throws Exception {
        boolean result = false;
        removeExecute(RemovePerExecutor.class, k, u, executors);
        int count = getDao().delete(u, k);
        if (count > 0) {
            result = true;
        }
        removeExecute(RemovePostExecutor.class, k, u, executors);
        return result;
    }

    /**
     * 执行多删除执行执行器
     *
     * @param clazz     执行器类型
     * @param ks        模型主键列表
     * @param u         用户
     * @param executors 执行器列表
     * @throws Exception
     * @author caobaoyu
     * @date 2022/10/02 14:08
     */
    private void removeMultiExecute(Class<? extends RemoveMultiExecutor> clazz, List<K> ks, AuthorizationUser<?, ?, ?, ?> u, RemoveMultiExecutor<K>... executors) throws Exception {
        if (executors != null) {
            for (RemoveMultiExecutor<K> executor : executors) {
                if (clazz.isInstance(executor)) {
                    executor.execute(u, ks);
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer removeMulti(List<K> ks, AuthorizationUser<?, ?, ?, ?> u, RemoveMultiExecutor<K>... executors) throws Exception {
        Integer count;
        removeMultiExecute(RemoveMultiPerExecutor.class, ks, u, executors);
        count = getDao().deleteMulti(u, ks);
        removeMultiExecute(RemoveMultiPostExecutor.class, ks, u, executors);
        return count;
    }

    /**
     * 执行部分修改执行执行器
     *
     * @param clazz     执行器类型
     * @param k         模型主键
     * @param u         用户
     * @param m         模型
     * @param executors 执行器列表
     * @throws Exception
     * @author caobaoyu
     * @date 2022/10/02 14:08
     */
    private void editExecute(Class<? extends EditExecutor> clazz, K k, AuthorizationUser<?, ?, ?, ?> u, M m, EditExecutor<K, M>... executors) throws Exception {
        if (executors != null) {
            for (EditExecutor<K, M> executor : executors) {
                if (clazz.isInstance(executor)) {
                    executor.execute(u, k, m);
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean edit(AuthorizationUser<?, ?, ?, ?> u, K k, M m, EditExecutor<K, M>... executors) throws Exception {
        boolean result = false;
        editExecute(EditPerExecutor.class, k, u, m, executors);
        int count = getDao().update(u, k, getConverter().dtoToPo(m));
        if (count > 0) {
            result = true;
        }
        editExecute(EditPostExecutor.class, k, u, m, executors);
        return result;
    }

    /**
     * 执行全部修改执行执行器
     *
     * @param clazz     执行器类型
     * @param k         模型主键
     * @param u         用户
     * @param m         模型
     * @param executors 执行器列表
     * @throws Exception
     * @author caobaoyu
     * @date 2022/10/02 14:09
     */
    private void editAllExecute( AuthorizationUser<?, ?, ?, ?> u, Class<? extends EditAllExecutor> clazz, K k,M m, EditAllExecutor<K, M>... executors) throws Exception {
        if (executors != null) {
            for (EditAllExecutor<K, M> executor : executors) {
                if (clazz.isInstance(executor)) {
                    executor.execute(k, u, m);
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean editAll(AuthorizationUser<?, ?, ?, ?> u, K k, M m, EditAllExecutor<K, M>... executors) throws Exception {
        boolean result;
        editAllExecute(u,EditAllPerExecutor.class, k, m, executors);
        result = getDao().updateAll(u, k, getConverter().dtoToPo(m));
        editAllExecute(u, EditAllPostExecutor.class, k, m, executors);
        return result;
    }



    /**
     * @param p 分页模型
     * @param getCallback 获取属性回调
     * @return 属性列表
     * @param <R> 返回值类型
     * @param <M> 模型类型
     *
     * @author caobaoyu
     * @date 2023/4/14 16:23
     */
    protected static <R, M extends Model<?>> List<R> getModelPropertyList(Page<M> p, GetCallback<M, R> getCallback) {
        List<R> keys = null;
        if (p != null && p.getItems() != null && p.getItems().size() > 0) {
            List<R> keyList = p.getItems().stream().map(getCallback::get).filter(Objects::nonNull).collect(Collectors.toList());
            if (keyList.size() > 0) {
                keys = keyList;
            }
        }
        return keys;
    }


    /**
     * 两个List一对一关联
     *
     * @param list1 list1
     * @param list2 list2
     * @param getCall1 获取list1的关联属性回调
     * @param getCall2 获取list2的关联属性回调
     * @param setCall 关联赋值回调
     * @param <P1> list1关联属性的类型
     * @param <P2> list2关联属性的类型
     * @param <M1> list1元素类型
     * @param <M2> list2元素类型
     *
     * @author caobaoyu
     * @date 2023/4/14 16:42
     */
    protected static <P1, P2, M1 extends Model<?>, M2 extends Model<?>> void joinOne(List<M1> list1, List<M2> list2, GetCallback<M1, P1> getCall1, GetCallback<M2, P2> getCall2, SetCallback<M1, M2> setCall) {
        if (list1 != null && list2 != null) {
            Map<P2, M2> map2 = list2.stream().collect(Collectors.toMap(getCall2::get, t -> t));
            list1.forEach(e -> {
                        P1 p = getCall1.get(e);
                        M2 m2 = map2.get(p);
                        if (m2 != null) {
                            setCall.set(e, m2);
                        }
                    }
            );
        }
    }


    /**
     * 两个ist一对多关联
     *
     * @param list1 list1
     * @param list2 list2
     * @param getCall1 获取list1的关联属性回调
     * @param getCall2 获取list2的关联属性回调
     * @param setCall 关联赋值回调
     * @param <P1> list1关联属性的类型
     * @param <P2> list2关联属性的类型
     * @param <M1> list1元素类型
     * @param <M2> list2元素类型
     *
     * @author caobaoyu
     * @date 2023/4/14 17:00
     */
    protected static <P1, P2, M1 extends Model<?>, M2 extends Model<?>> void joinMany(List<M1> list1, List<M2> list2, GetCallback<M1, P1> getCall1, GetCallback<M2, P2> getCall2, SetCallback<M1, List<M2>> setCall) {
        if (list1 != null && list2 != null) {
            Map<P2, List<M2>> map2 = list2.stream().collect(Collectors.groupingBy(getCall2::get));
            list1.forEach(e -> {
                        P1 p = getCall1.get(e);
                        List<M2> m2List = map2.get(p);
                        if (m2List != null) {
                            setCall.set(e, m2List);
                        }
                    }
            );
        }
    }

}

