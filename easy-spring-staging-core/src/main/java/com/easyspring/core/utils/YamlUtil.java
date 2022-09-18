/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * Yaml文件解析  .
 *
 * <p>
 * Yaml文件解析
 *
 * @author caobaoyu
 * @date 2020/12/2 13:20
 */
public class YamlUtil {
    public static Object loadObject(InputStream inputStream){
        Object resultObject = null;
        Yaml yaml = new Yaml();
        resultObject = yaml.load(inputStream);
        return resultObject;
    }

    public static Map<String, Object> loadMap(InputStream inputStream){
        Map<String, Object> resultMap = null;
        Yaml yaml = new Yaml();
        resultMap = yaml.load(inputStream);
        return resultMap;
    }

    public static String queryMapValue(Map<String, Object> map, String paths,String keyName){
        String value = null;
        if(map!=null && paths!=null){
            String[] pathArray =  paths.split(":");
            if(pathArray!=null && pathArray.length>0){
                Map<String, Object> tempMap = map;
                for (String key:pathArray){
                    Map<String, Object> tp = recursionMap(tempMap,key);
                    if(tp!=null){
                        tempMap = tp;
                    }else {
                        tempMap = null;
                        break;
                    }
                }
                if(tempMap!=null){
                    Object objValue = tempMap.get(keyName);
                    if(objValue instanceof String){
                        value = (String)objValue;
                    }
                }
            }
        }
        return value;
    }

    public static Map<String, Object> recursionMap(Map<String, Object> map, String key){
        Map<String, Object> result = null;
        if(map!=null && key!=null){
            Object objValue = map.get(key);
            if (objValue instanceof Map){
                result =  (Map<String, Object>)objValue;
            }
        }
        return result;
    }
}
