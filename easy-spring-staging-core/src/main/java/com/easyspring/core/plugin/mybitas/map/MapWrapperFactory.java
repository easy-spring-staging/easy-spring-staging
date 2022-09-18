/**
 * Copyright: Copyright(C) 2020 Easy-Java-Rest-Framework.
 */
package com.easyspring.core.plugin.mybitas.map;

import com.easyspring.core.model.BaseMap;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.Map;

/**
 * Mybitas Map包装类工厂方法  .
 *
 * <p>
 * Mybitas Map包装类工厂方法
 *
 * @author caobaoyu
 * @date 2020/3/20 22:27
 */
public class MapWrapperFactory implements ObjectWrapperFactory {
    @Override
    public boolean hasWrapperFor(Object object) {
        return object != null && object instanceof BaseMap;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        return new MyBatisMapWrapper(metaObject, (Map) object);
    }
}
