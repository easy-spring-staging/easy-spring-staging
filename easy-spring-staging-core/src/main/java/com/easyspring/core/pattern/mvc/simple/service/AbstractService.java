/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.pattern.mvc.simple.service;

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
import com.easyspring.core.pattern.mvc.simple.dao.BaseDao;
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
public abstract class AbstractService<K, M extends Model<K>> {



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
    if (p != null && p.getList() != null && p.getList().size() > 0) {
      List<R> keyList = p.getList().stream().map(getCallback::get).filter(Objects::nonNull).collect(Collectors.toList());
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



  public abstract BaseDao<K, M> getDao();

  /**
   * 执行查询详情附加执行器
   *
   * @param u 用户
   * @param clazz 附加执行器类型
   * @param k 模型主键
   * @param m 模型
   * @param executors 附加执行器列表
   * @throws Exception 异常
   *
   * @author caobaoyu
   * @date 2023/4/14 17:12
   */
  //@SuppressWarnings("unchecked")
  private void queryDetailsExecute(AuthorizationUser<?, ?, ?, ?> u, Class<? extends QueryDetailsExecutor> clazz, K k,M m, QueryDetailsExecutor<K, M>... executors) throws Exception {
    if (executors != null) {
      for (QueryDetailsExecutor<K, M> executor : executors) {
        if (clazz.isInstance(executor)) {
          executor.execute(u, k, m);
        }
      }
    }
  }


  /**
   * 执行查询列表附加执行器
   *
   * @param u 用户
   * @param clazz 附加执行器类型
   * @param q 查询参数模型
   * @param p 分页模型
   * @param executors 附加执行器列表
   * @throws Exception 异常
   *
   * @author caobaoyu
   * @date 2023/4/14 17:36
   */
  //@SuppressWarnings("unchecked")
  private void queryPageExecute(AuthorizationUser<?, ?, ?, ?> u, Class<? extends QueryPageExecutor> clazz, QueryParameter q, Page<M> p, QueryPageExecutor<K, M>... executors) throws Exception {
    if (executors != null) {
      for (QueryPageExecutor<K, M> executor : executors) {
        if (clazz.isInstance(executor)) {
          executor.execute(u, q, p);
        }
      }
    }
  }

  /**
   * 执行添加附加执行器
   *
   * @param u 用户
   * @param clazz 附加执行器类型
   * @param k 模型主键
   * @param m 模型
   * @param executors 附加执行器
   * @throws Exception 异常
   *
   * @author caobaoyu
   * @date 2023/4/14 18:02
   */
  //@SuppressWarnings("unchecked")
  private void addExecute(AuthorizationUser<?, ?, ?, ?> u, Class<? extends AddExecutor> clazz, K k, M m, AddExecutor<K, M>... executors) throws Exception {
    if (executors != null) {
      for (AddExecutor<K, M> executor : executors) {
        if (clazz.isInstance(executor)) {
          executor.execute(u, k, m);
        }
      }
    }
  }


  /**
   *  执行删除附加执行器
   *
   * @param u 用户
   * @param clazz 执行器类型
   * @param k 模型主键
   * @param executors 执行器列表
   * @throws Exception 异常
   * @author caobaoyu
   * @date 2022/10/02 14:07
   */
  //@SuppressWarnings("unchecked")
  private void removeExecute(AuthorizationUser<?, ?, ?, ?> u, Class<? extends RemoveExecutor> clazz, K k, RemoveExecutor<K>... executors) throws Exception {
    if (executors != null) {
      for (RemoveExecutor<K> executor : executors) {
        if (clazz.isInstance(executor)) {
          executor.execute(u, k);
        }
      }
    }
  }


  /**
   * 执行多删除执行执行器
   *
   * @param clazz     执行器类型
   * @param ks        模型主键列表
   * @param u         用户
   * @param executors 执行器列表
   * @throws Exception 异常
   * @author caobaoyu
   * @date 2022/10/02 14:08
   */
  //@SuppressWarnings("unchecked")
  private void removeMultiExecute(AuthorizationUser<?, ?, ?, ?> u, Class<? extends RemoveMultiExecutor> clazz, List<K> ks, RemoveMultiExecutor<K>... executors) throws Exception {
    if (executors != null) {
      for (RemoveMultiExecutor<K> executor : executors) {
        if (clazz.isInstance(executor)) {
          executor.execute(u, ks);
        }
      }
    }
  }

  /**
   * 执行部分修改执行执行器
   *
   * @param clazz     执行器类型
   * @param k         模型主键
   * @param u         用户
   * @param m         模型
   * @param executors 执行器列表
   * @throws Exception 异常
   * @author caobaoyu
   * @date 2022/10/02 14:08
   */
  //@SuppressWarnings("unchecked")
  private void editExecute(Class<? extends EditExecutor> clazz, K k, AuthorizationUser<?, ?, ?, ?> u, M m, EditExecutor<K, M>... executors) throws Exception {
    if (executors != null) {
      for (EditExecutor<K, M> executor : executors) {
        if (clazz.isInstance(executor)) {
          executor.execute(u,k,  m);
        }
      }
    }
  }


  /**
   * 执行全部修改执行执行器
   *
   * @param clazz     执行器类型
   * @param k         模型主键
   * @param u         用户
   * @param m         模型
   * @param executors 执行器列表
   * @throws Exception 异常
   * @author caobaoyu
   * @date 2022/10/02 14:09
   */
  //@SuppressWarnings("unchecked")
  private void editAllExecute(AuthorizationUser<?, ?, ?, ?> u, Class<? extends EditAllExecutor> clazz, K k, M m, EditAllExecutor<K, M>... executors) throws Exception {
    if (executors != null) {
      for (EditAllExecutor<K, M> executor : executors) {
        if (clazz.isInstance(executor)) {
          executor.execute(k, u, m);
        }
      }
    }
  }

  //@SuppressWarnings("unchecked")
  @Transactional(readOnly = true)
  public M queryDetails(AuthorizationUser<?, ?, ?, ?> u, K k, QueryDetailsExecutor<K, M>... executors) throws Exception {
    M m;
    queryDetailsExecute(u, QueryDetailsPerExecutor.class, k, null, executors);
    m = getDao().load(k, u);
    queryDetailsExecute(u, QueryDetailsPostExecutor.class, k, m, executors);
    return m;
  }

  //@SuppressWarnings("unchecked")
  @Transactional(readOnly = true)
  public Page<M> queryPage(AuthorizationUser<?, ?, ?, ?> u, QueryParameter q, QueryPageExecutor<K, M>... executors) throws Exception {
    Page<M> page;
    q.initPage();
    queryPageExecute(u, QueryPagePerExecutor.class, q, null, executors);
    if (q.isPage()) {
      com.github.pagehelper.Page<M> pageHelperPage = PageHelper.startPage(q.getPageModel().getPageNum(), q.getPageModel().getPageSize()).doSelectPage(() -> getDao().query(q, u));
      page = new Page<>(pageHelperPage);
    } else {
      List<M> list = getDao().query(q, u);
      page = new Page<>(list);
    }
    queryPageExecute( u, QueryPagePostExecutor.class, q, page, executors);
    return page;
  }

  @Transactional(readOnly = true)
  public List<M> queryList(AuthorizationUser<?, ?, ?, ?> u,String fn, List<?> ks) throws Exception{
    List<M> dataList;
    dataList = getDao().list(u,fn, ks);
    return dataList;
  }

  @Transactional(readOnly = true)
  public Integer count(AuthorizationUser<?, ?, ?, ?> u, QueryParameter q) throws Exception {
    return getDao().count(u,q);
  }

  //@SuppressWarnings("unchecked")
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  public K add(AuthorizationUser<?, ?, ?, ?> u, M m, AddExecutor<K, M>... executors) throws Exception {
    K k = null;
    addExecute(u, AddPerExecutor.class, null, m, executors);
    if (getDao().insert(m) > 0) {
      k = m.getKey();
    }
    addExecute(u, AddPostExecutor.class, k, m, executors);
    return k;
  }

  //@SuppressWarnings("unchecked")
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  public Boolean remove(AuthorizationUser<?, ?, ?, ?> u, K k, RemoveExecutor<K>... executors) throws Exception {
    boolean result = false;
    removeExecute(u, RemovePerExecutor.class, k, executors);
    int count = getDao().delete(k, u);
    if (count > 0) {
      result = true;
    }
    removeExecute(u, RemovePostExecutor.class, k, executors);
    return result;
  }

  //@SuppressWarnings("unchecked")
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  public Integer removeMulti(AuthorizationUser<?, ?, ?, ?> u, List<K> ks, RemoveMultiExecutor<K>... executors) throws Exception {
    Integer count;
    removeMultiExecute(u,RemoveMultiPerExecutor.class, ks,executors);
    count = getDao().deleteMulti(ks, u);
    removeMultiExecute(u, RemoveMultiPostExecutor.class, ks,executors);
    return count;
  }


  //@SuppressWarnings("unchecked")
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  public Boolean edit(AuthorizationUser<?, ?, ?, ?> u, K k, M m, EditExecutor<K, M>... executors) throws Exception {
    boolean result = false;
    editExecute(EditPerExecutor.class, k, u, m, executors);
    int count = getDao().update(k, m, u);
    if (count > 0) {
      result = true;
    }
    editExecute(EditPostExecutor.class, k, u, m, executors);
    return result;
  }

  //@SuppressWarnings("unchecked")
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  public Boolean editAll(AuthorizationUser<?, ?, ?, ?> u, K k, M m, EditAllExecutor<K, M>... executors) throws Exception {
    boolean result = false;
    editAllExecute(u, EditAllPerExecutor.class, k, m, executors);
    int count = getDao().update(k, m, u);
    if (count > 0) {
      result = true;
    }
    editAllExecute(u, EditAllPostExecutor.class, k, m, executors);
    return result;
  }
}
