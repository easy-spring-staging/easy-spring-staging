/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.design.mvc.standard.service;

import com.easyspring.core.callback.GetCallback;
import com.easyspring.core.callback.SetCallback;
import com.easyspring.core.design.mvc.standard.dao.BaseDao;
import com.easyspring.core.executor.*;
import com.easyspring.core.model.Model;
import com.easyspring.core.model.Page;
import com.easyspring.core.model.QueryParameter;
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
public abstract class AbstractService<K, M extends Model<K>> implements BaseService<K, M> {

    public abstract BaseDao<K, M> getDao();

    @Override
    public M load(K k, AuthorizationUser u, DetailsExecutor<K, M>... executors) throws Exception {
        M m = getDao().load(k, u);
        if(executors!=null){
            for(DetailsExecutor<K, M> e : executors){
                e.execute(k, u, m);
            }
        }
        return m;
    }

    @Override
    public Page<M> query(QueryParameter q, AuthorizationUser u, QueryExecutor<K, M>... executors) throws Exception {
        Page page = null;
        q.initPage();
        if(q.getPageModel().getPageSize() == 0){
            List<M> list = getDao().query(q,u);
            page = new Page(list);
        }else {
            com.github.pagehelper.Page<M> pageHelperPage = PageHelper.startPage(q.getPageModel().getPageNum(), q.getPageModel().getPageSize()).doSelectPage(() -> getDao().query(q, u));
            page = new Page(pageHelperPage);
        }
        if(executors!=null){
            for(QueryExecutor<K, M> e : executors){
                e.execute(q, u, page);
            }
        }
        return page;
    }

    @Override
    public List<M> list(String fn,List<? extends Object> ks, AuthorizationUser u) throws Exception{
        List<M> dataList = null;
        dataList = getDao().list(fn,ks, u);
        return dataList;
    }

    @Override
    public Integer count(QueryParameter q, AuthorizationUser u) throws Exception {
        return getDao().count(q, u);
    }

    @Override
    public K add(M m, AuthorizationUser u, AddExecutor<K, M >... executors) throws Exception {
        K k = null;
        if(getDao().insert(m)>0){
            k = m.getKey();
        }
        if(executors!=null){
            for(AddExecutor<K, M> e : executors){
                e.execute(k, u, m);
            }
        }
        return k;
    }

    @Override
    public Boolean delete(K k, AuthorizationUser u, DeleteExecutor<K>... executors) throws Exception {
        Boolean result = false;
        if(executors!=null){
            for(DeleteExecutor<K> e : executors){
                e.execute(k, u);
            }
        }
        int count = getDao().delete(k, u);
        if(count>0){
            result = true;
        }
        return result;
    }

    @Override
    public Integer batchDelete(List<K> ks, AuthorizationUser u, BatchDeleteExecutor<K>... executors) throws Exception {
        Integer count = 0;
        if(executors!=null){
            for(BatchDeleteExecutor<K> e : executors){
                e.execute(ks, u);
            }
        }
        count = getDao().batchDelete(ks, u);
        return count;
    }

    @Override
    public Boolean update(K k, M m, AuthorizationUser u, UpdateExecutor<K, M>... executors) throws Exception {
        Boolean result = false;
        int count = getDao().update(k, m, u);
        if(count>0){
            result = true;
        }
        if(executors!=null){
            for(UpdateExecutor<K, M> e : executors){
                e.execute(k, u, m);
            }
        }
        return result;
    }

    @Override
    public Boolean updateNull(K k, M m, AuthorizationUser u, UpdateNullExecutor<K, M>... executors) throws Exception {
        Boolean result = false;
        result = getDao().updateNull(k, m, u);
        if(executors!=null){
            for(UpdateNullExecutor<K, M> e : executors){
                e.execute(k, u, m);
            }
        }
        return result;
    }
    protected static<K, M1 extends Model, M2 extends Model> void mappingObject(List<M1> list1, List<M2> list2, GetCallback<M1, K> getKeyCallback1, GetCallback<M2, K> getKeyCallback2, SetCallback<M1,M2> setValueCallback1){
        if(list1!=null && list2!=null){
            Map<K,M2> map2 = list2.stream().collect(Collectors.toMap( t -> getKeyCallback2.get(t), t -> t));
            for (M1 m1:list1){
                K key = getKeyCallback1.get(m1);
                M2 m2 = map2.get(key);
                if(m2!=null) {
                    setValueCallback1.set(m1, m2);
                }
            }
        }
    }

    protected static<K, M1 extends Model, M2 extends Model> void mappingList(List<M1> list1, List<M2> list2, GetCallback<M1, K> getKeyCallback1, GetCallback<M2, K> getKeyCallback2, SetCallback<M1,List<M2>> setValueCallback1){
        if(list1!=null && list2!=null){
            Map<K,List<M2>> map2 = list2.stream().collect(Collectors.groupingBy( t -> getKeyCallback2.get(t)));
            for (M1 m1:list1){
                K key = getKeyCallback1.get(m1);
                List<M2> m2List = map2.get(key);
                if(m2List!=null) {
                    setValueCallback1.set(m1, m2List);
                }
            }
        }
    }
}
