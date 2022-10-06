
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
     * @throws Exception
     * @author caobaoyu
     * @date 2022/10/02 14:02
     */
    private void queryDetailsExecute(Class<? extends QueryDetailsExecutor> clazz, K k, AuthorizationUser u, M m, QueryDetailsExecutor<K, M>... executors) throws Exception {
        if (executors == null) {
            for (QueryDetailsExecutor<K, M> executor : executors) {
                if (executor != null && QueryDetailsExecutor.class == executor.getClass().getInterfaces()[0]) {
                    throw new Exception("执行器不能空，且不能执行QueryDetailsExecutor,请使用QueryDetailsExecutor的子接口！");
                } else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(k, u, m);
                    }
                }
            }
        }
    }

    @Transactional(readOnly = true)
    public M queryDetails(K k, AuthorizationUser u, QueryDetailsExecutor<K, M>... executors) throws Exception {
        M m = null;
        queryDetailsExecute(QueryDetailsPerExecutor.class, k, u, m, executors);
        m = getConverter().poToDto(getDao().load(k, u));
        queryDetailsExecute(QueryDetailsPostExecutor.class, k, u, m, executors);
        return m;
    }

    /**
     * 执行查询列表执行执行器
     *
     * @param clazz     执行器类型
     * @param q         查询参数模型
     * @param u         用户
     * @param p         分页模型
     * @param executors
     * @throws Exception
     * @author caobaoyu
     * @date 2022/10/02 14:04
     */
    private void queryPageExecute(Class<? extends QueryPageExecutor> clazz, QueryParameter q, AuthorizationUser u, Page<M> p, QueryPageExecutor<K, M>... executors) throws Exception {
        if (executors != null) {
            for (QueryPageExecutor<K, M> executor : executors) {
                if (executor == null && QueryPageExecutor.class == executor.getClass().getInterfaces()[0]) {
                    throw new Exception("执行器不能空，且不能执行QueryPageExecutor,请使用QueryPageExecutor的子接口！");
                } else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(q, u, p);
                    }
                }
            }
        }
    }

    @Transactional(readOnly = true)
    public Page<M> queryPage(QueryParameter q, AuthorizationUser u, QueryPageExecutor<K, M>... executors) throws Exception {
        Page page = null;
        q.initPage();
        queryPageExecute(QueryPagePerExecutor.class, q, u, page, executors);
        if (q.isPage()) {
            com.github.pagehelper.Page<M> pageHelperPage = PageHelper.startPage(q.getPageModel().getPageNum(), q.getPageModel().getPageSize()).doSelectPage(() -> getDao().query(q, u));
            page = new Page(pageHelperPage);
        } else {
            List<M> list = getConverter().poToDto(getDao().query(q, u));
            page = new Page(list);
        }
        queryPageExecute(QueryPagePostExecutor.class, q, u, page, executors);
        return page;
    }

    @Transactional(readOnly = true)
    public List<M> queryList(String fn, List<? extends Object> ks, AuthorizationUser u) throws Exception {
        List<M> dataList = null;
        dataList = getConverter().poToDto(getDao().list(fn, ks, u));
        return dataList;
    }

    @Transactional(readOnly = true)
    public Integer count(QueryParameter q, AuthorizationUser u) throws Exception {
        return getDao().count(q, u);
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
    private void addExecute(Class<? extends AddExecutor> clazz, K k, AuthorizationUser u, M m, AddExecutor<K, M>... executors) throws Exception {
        if (executors != null) {
            for (AddExecutor<K, M> executor : executors) {
                if (executor == null && AddExecutor.class == executor.getClass().getInterfaces()[0]) {
                    throw new Exception("执行器不能空，且不能执行AddExecutor,请使用AddExecutor的子接口！");
                } else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(k, u, m);
                    }
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public K add(M m, AuthorizationUser u, AddExecutor<K, M>... executors) throws Exception {
        K k = null;
        addExecute(AddPerExecutor.class, k, u, m, executors);
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
    private void removeExecute(Class<? extends RemoveExecutor> clazz, K k, AuthorizationUser u, RemoveExecutor<K>... executors) throws Exception {
        if (executors != null) {
            for (RemoveExecutor<K> executor : executors) {
                if (executor == null && RemoveExecutor.class == executor.getClass().getInterfaces()[0]) {
                    throw new Exception("执行器不能空，且不能执行RemoveExecutor,请使用RemoveExecutor的子接口！");
                } else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(k, u);
                    }
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean remove(K k, AuthorizationUser u, RemoveExecutor<K>... executors) throws Exception {
        Boolean result = false;
        removeExecute(RemovePerExecutor.class, k, u, executors);
        int count = getDao().delete(k, u);
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
    private void removeMultiExecute(Class<? extends RemoveMultiExecutor> clazz, List<K> ks, AuthorizationUser u, RemoveMultiExecutor<K>... executors) throws Exception {
        if (executors != null) {
            for (RemoveMultiExecutor<K> executor : executors) {
                if (executor == null && RemoveMultiExecutor.class == executor.getClass().getInterfaces()[0]) {
                    throw new Exception("执行器不能空，且不能执行RemoveMultiExecutor,请使用RemoveMultiExecutor的子接口！");
                } else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(ks, u);
                    }
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer removeMulti(List<K> ks, AuthorizationUser u, RemoveMultiExecutor<K>... executors) throws Exception {
        Integer count = 0;
        removeMultiExecute(RemoveMultiPerExecutor.class, ks, u, executors);
        count = getDao().deleteMulti(ks, u);
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
    private void editExecute(Class<? extends EditExecutor> clazz, K k, AuthorizationUser u, M m, EditExecutor<K, M>... executors) throws Exception {
        if (executors != null) {
            for (EditExecutor<K, M> executor : executors) {
                if (executor == null && EditExecutor.class == executor.getClass().getInterfaces()[0]) {
                    throw new Exception("执行器不能空，且不能执行EditExecutor,请使用EditExecutor的子接口！");
                } else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(k, u, m);
                    }
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean edit(K k, M m, AuthorizationUser u, EditExecutor<K, M>... executors) throws Exception {
        Boolean result = false;
        editExecute(EditPerExecutor.class, k, u, m, executors);
        int count = getDao().update(k, getConverter().dtoToPo(m), u);
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
    private void editAllExecute(Class<? extends EditAllExecutor> clazz, K k, AuthorizationUser u, M m, EditAllExecutor<K, M>... executors) throws Exception {
        if (executors != null) {
            for (EditAllExecutor<K, M> executor : executors) {
                if (executor == null && EditAllExecutor.class == executor.getClass().getInterfaces()[0]) {
                    throw new Exception("执行器不能空，且不能执行EditAllExecutor,请使用EditAllExecutor的子接口！");
                } else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(k, u, m);
                    }
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean editAll(K k, M m, AuthorizationUser u, EditAllExecutor<K, M>... executors) throws Exception {
        Boolean result = false;
        editAllExecute(EditAllPerExecutor.class, k, u, m, executors);
        result = getDao().updateAll(k, getConverter().dtoToPo(m), u);
        editAllExecute(EditAllPostExecutor.class, k, u, m, executors);
        return result;
    }

    /**
     * 两个模型List一对一关联
     *
     * @param list1             左侧List
     * @param list2             右侧List
     * @param getKeyCallback1   左侧List模型获取关联字段回调
     * @param getKeyCallback2   右侧List模型获取关联字段回调
     * @param setValueCallback1 将右侧关联模型赋值给左侧模型的赋值回调
     * @param <MK>              关联字段类型
     * @param <M1>              左侧List模型类型
     * @param <M2>              右侧List模型类型
     * @author caobaoyu
     * @date 2022/10/02 14:18
     */
    protected static <MK, M1 extends Model<MK>, M2 extends Model> void joinOne(List<M1> list1, List<M2> list2, GetCallback<M1, MK> getKeyCallback1, GetCallback<M2, MK> getKeyCallback2, SetCallback<M1, M2> setValueCallback1) {
        if (list1 != null && list2 != null) {
            Map<MK, M2> map2 = list2.stream().collect(Collectors.toMap(t -> getKeyCallback2.get(t), t -> t));
            for (M1 m1 : list1) {
                MK key = getKeyCallback1.get(m1);
                M2 m2 = map2.get(key);
                if (m2 != null) {
                    setValueCallback1.set(m1, m2);
                }
            }
        }
    }

    /**
     * 两个模型List一对多关联
     *
     * @param list1             左侧List
     * @param list2             右侧List
     * @param getKeyCallback1   左侧List模型获取关联字段回调
     * @param getKeyCallback2   右侧List模型获取关联字段回调
     * @param setValueCallback1 将右侧关联模型List赋值给左侧模型的赋值回调
     * @param <MK>              关联字段类型
     * @param <M1>              左侧List模型类型
     * @param <M2>              右侧List模型类型
     * @author caobaoyu
     * @date 2022/10/02 14:20
     */
    protected static <MK, M1 extends Model<MK>, M2 extends Model> void joinMany(List<M1> list1, List<M2> list2, GetCallback<M1, MK> getKeyCallback1, GetCallback<M2, MK> getKeyCallback2, SetCallback<M1, List<M2>> setValueCallback1) {
        if (list1 != null && list2 != null) {
            Map<MK, List<M2>> map2 = list2.stream().collect(Collectors.groupingBy(t -> getKeyCallback2.get(t)));
            for (M1 m1 : list1) {
                MK key = getKeyCallback1.get(m1);
                List<M2> m2List = map2.get(key);
                if (m2List != null) {
                    setValueCallback1.set(m1, m2List);
                }
            }
        }
    }

    /**
     * 获取分析模型的主键列表
     *
     * @param p 分页模型
     * @return 主键列表
     * @author caobaoyu
     * @date 2022/10/02 14:21
     */
    protected List<K> getModelKeyList(Page<M> p) {
        List<K> keys = null;
        if (p != null && p.getList() != null && p.getList().size() > 0) {
            List<K> keyList = p.getList().stream().map(e -> e.getKey()).filter(k -> k != null).collect(Collectors.toList());
            if (keyList != null && keyList.size() > 0) {
                keys = keyList;
            }
        }
        return keys;
    }
}

