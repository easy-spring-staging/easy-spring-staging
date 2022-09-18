/**
 * Copyright: Copyright(C) 2020 Easy-Java-Rest-Framework.
 */
package com.easyspring.core.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 基础Map模型类  .
 *
 * <p>
 * 基础Map模型类
 *
 * @author caobaoyu
 * @date 2020/3/20 15:03
 */
public class BaseMap extends HashMap<String, Object> implements Serializable {

    public Integer getInteger(String key){
        Integer value = null;
        if(key!=null){
            Object obj = super.get(key);
            if(obj!=null){
                try{
                    value = (Integer) obj;
                }catch (Exception e){

                }
            }
        }
        return value;
    }

}
