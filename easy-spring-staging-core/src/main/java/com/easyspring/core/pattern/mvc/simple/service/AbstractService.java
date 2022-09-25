/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.pattern.mvc.simple.service;

import com.easyspring.core.model.AbstractModel;
import com.easyspring.core.model.Page;
import com.easyspring.core.model.QueryParameter;
import com.easyspring.core.model.callback.GetCallback;
import com.easyspring.core.model.callback.SetCallback;
import com.easyspring.core.pattern.mvc.simple.dao.BaseDao;
import com.easyspring.core.executor.*;
import com.easyspring.core.sercurity.AuthorizationUser;
import com.github.pagehelper.PageHelper;

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
public abstract class AbstractService<K, M extends AbstractModel<K>> implements BaseService<K, M> {

    public abstract BaseDao<K, M> getDao();

    private void queryDetailsExecute(Class<? extends QueryDetailsExecutor> clazz, K k, AuthorizationUser u, M m, QueryDetailsExecutor<K, M>... executors) throws Exception{
        if (executors == null) {
            for (QueryDetailsExecutor<K, M> executor : executors) {
                if(executor!=null && QueryDetailsExecutor.class == executor.getClass().getInterfaces()[0]){
                    throw new Exception("执行器不能空，且不能执行QueryDetailsExecutor,请使用QueryDetailsExecutor的子接口！");
                }else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(k, u, m);
                    }
                }
            }
        }
    }

    @Override
    public M queryDetails(K k, AuthorizationUser u, QueryDetailsExecutor<K, M>... executors) throws Exception {
        M m = null;
        queryDetailsExecute(QueryDetailsPerExecutor.class, k, u, m, executors);
        m = getDao().load(k, u);
        queryDetailsExecute(QueryDetailsPostExecutor.class, k, u, m, executors);
        return m;
    }

    private void queryPageExecute(Class<? extends QueryPageExecutor> clazz, QueryParameter q, AuthorizationUser u, Page<M> p, QueryPageExecutor<K, M>... executors) throws Exception{
        if (executors != null) {
            for (QueryPageExecutor<K, M> executor : executors) {
                if(executor==null && QueryPageExecutor.class == executor.getClass().getInterfaces()[0]){
                    throw new Exception("执行器不能空，且不能执行QueryPageExecutor,请使用QueryPageExecutor的子接口！");
                }else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(q, u, p);
                    }
                }
            }
        }
    }

    @Override
    public Page<M> queryPage(QueryParameter q, AuthorizationUser u, QueryPageExecutor<K, M>... executors) throws Exception {
        Page page = null;
        q.initPage();
        queryPageExecute(QueryPagePerExecutor.class, q, u, page, executors);
        if (q.getPageModel().getPageSize() == 0) {
            List<M> list = getDao().query(q, u);
            page = new Page(list);
        } else {
            com.github.pagehelper.Page<M> pageHelperPage = PageHelper.startPage(q.getPageModel().getPageNum(), q.getPageModel().getPageSize()).doSelectPage(() -> getDao().query(q, u));
            page = new Page(pageHelperPage);
        }
        queryPageExecute(QueryPagePostExecutor.class, q, u, page, executors);
        return page;
    }

//    @Override
//    public List<M> queryList(String fn, List<? extends Object> ks, AuthorizationUser u) throws Exception{
//        List<M> dataList = null;
//        dataList = getDao().list(fn,ks, u);
//        return dataList;
//    }

//    @Override
//    public Integer count(QueryParameter q, AuthorizationUser u) throws Exception {
//        return getDao().count(q, u);
//    }


    private void addExecute(Class<? extends AddExecutor> clazz, K k, AuthorizationUser u, M m, AddExecutor<K, M>... executors) throws Exception{
        if (executors != null) {
            for (AddExecutor<K, M> executor : executors) {
                if(executor==null && AddExecutor.class == executor.getClass().getInterfaces()[0]){
                    throw new Exception("执行器不能空，且不能执行AddExecutor,请使用AddExecutor的子接口！");
                }else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(k, u, m);
                    }
                }
            }
        }
    }

    @Override
    public K add(M m, AuthorizationUser u, AddExecutor<K, M>... executors) throws Exception {
        K k = null;
        addExecute(AddPerExecutor.class, k, u, m, executors);
        if (getDao().insert(m) > 0) {
            k = m.getKey();
        }
        addExecute(AddPostExecutor.class, k, u, m, executors);
        return k;
    }


    private void removeExecute(Class<? extends RemoveExecutor> clazz, K k, AuthorizationUser u, RemoveExecutor<K>... executors) throws Exception{
        if (executors != null) {
            for (RemoveExecutor<K> executor : executors) {
                if(executor==null && RemoveExecutor.class == executor.getClass().getInterfaces()[0]){
                    throw new Exception("执行器不能空，且不能执行RemoveExecutor,请使用RemoveExecutor的子接口！");
                }else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(k, u);
                    }
                }
            }
        }
    }

    @Override
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

    private void removeMultiExecute(Class<? extends RemoveMultiExecutor> clazz, List<K> ks, AuthorizationUser u, RemoveMultiExecutor<K>... executors) throws Exception{
        if (executors != null) {
            for (RemoveMultiExecutor<K> executor : executors) {
                if(executor==null && RemoveMultiExecutor.class == executor.getClass().getInterfaces()[0]){
                    throw new Exception("执行器不能空，且不能执行RemoveMultiExecutor,请使用RemoveMultiExecutor的子接口！");
                }else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(ks, u);
                    }
                }
            }
        }
    }

    @Override
    public Integer removeMulti(List<K> ks, AuthorizationUser u, RemoveMultiExecutor<K>... executors) throws Exception {
        Integer count = 0;
        removeMultiExecute(RemoveMultiPerExecutor.class, ks, u, executors);
        count = getDao().deleteMulti(ks, u);
        removeMultiExecute(RemoveMultiPostExecutor.class, ks, u, executors);
        return count;
    }


    private void editExecute(Class<? extends EditExecutor> clazz, K k, AuthorizationUser u, M m, EditExecutor<K, M>... executors) throws Exception{
        if (executors != null) {
            for (EditExecutor<K, M> executor : executors) {
                if(executor==null && EditExecutor.class == executor.getClass().getInterfaces()[0]){
                    throw new Exception("执行器不能空，且不能执行EditExecutor,请使用EditExecutor的子接口！");
                }else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(k, u, m);
                    }
                }
            }
        }
    }

    @Override
    public Boolean edit(K k, M m, AuthorizationUser u, EditExecutor<K, M>... executors) throws Exception {
        Boolean result = false;
        editExecute(EditPerExecutor.class, k, u, m, executors);
        int count = getDao().update(k, m, u);
        if (count > 0) {
            result = true;
        }
        editExecute(EditPostExecutor.class, k, u, m, executors);
        return result;
    }

    private void editAllExecute(Class<? extends EditAllExecutor> clazz, K k, AuthorizationUser u, M m, EditAllExecutor<K, M>... executors) throws Exception{
        if (executors != null) {
            for (EditAllExecutor<K, M> executor : executors) {
                if(executor==null && EditAllExecutor.class == executor.getClass().getInterfaces()[0]){
                    throw new Exception("执行器不能空，且不能执行EditAllExecutor,请使用EditAllExecutor的子接口！");
                }else {
                    if (clazz.isInstance(executor)) {
                        executor.execute(k, u, m);
                    }
                }
            }
        }
    }

    @Override
    public Boolean editAll(K k, M m, AuthorizationUser u, EditAllExecutor<K, M>... executors) throws Exception {
        Boolean result = false;
        editAllExecute(EditAllPerExecutor.class, k, u, m, executors);
        result = getDao().updateAll(k, m, u);
        editAllExecute(EditAllPostExecutor.class, k, u, m, executors);
        return result;
    }

    protected static <MK, M1 extends AbstractModel<MK>, M2 extends AbstractModel> void mappingObject(List<M1> list1, List<M2> list2, GetCallback<M1, MK> getKeyCallback1, GetCallback<M2, MK> getKeyCallback2, SetCallback<M1, M2> setValueCallback1) {
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

    protected static <MK, M1 extends AbstractModel<MK>, M2 extends AbstractModel> void mappingList(List<M1> list1, List<M2> list2, GetCallback<M1, MK> getKeyCallback1, GetCallback<M2, MK> getKeyCallback2, SetCallback<M1, List<M2>> setValueCallback1) {
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

    protected List<K> getPageKeyList(Page<M> p){
        List<K> keys = null;
        if(p!=null && p.getList()!=null && p.getList().size()>0){
            List<K> keyList = p.getList().stream().map(e -> e.getKey()).filter(k-> k!=null).collect(Collectors.toList());
            if(keyList!=null && keyList.size()>0){
                keys = keyList;
            }
        }
        return keys;
    }
}
