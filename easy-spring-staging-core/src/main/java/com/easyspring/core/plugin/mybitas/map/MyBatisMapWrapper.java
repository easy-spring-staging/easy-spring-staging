/**
 * ProjectName:    Easy-Java-Rest-Framework
 * PackageName:    com.ejwf.core.plugin.mybitas.map
 * FileName：      MyBatisMapWrapper.java
 * Copyright:      Copyright(C) 2020
 * Company:        easy-spring-staging Co.
 * Author:         caobaoyu
 * CreateDate:     2020/3/20 22:33
 */

package com.easyspring.core.plugin.mybitas.map;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * Mybitas Map 包裹类  .
 *
 * <p>
 * Mybitas Map 包裹类
 *
 * @author caobaoyu
 * @date 2020/3/20 22:33
 */
public class MyBatisMapWrapper extends MapWrapper {
    public MyBatisMapWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if (useCamelCaseMapping) {
            String tempName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tempName);
        }
        return name;
    }
}
