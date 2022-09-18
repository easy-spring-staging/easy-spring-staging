/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.typehandler;


import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * json类型抽象TypeHandler  .
 *
 * <p>
 * json类型抽象TypeHandler
 *
 * @author caobaoyu
 * @date 2020/10/28 10:36
 */
public abstract class AbstractTypeHandler<T extends Object> extends BaseTypeHandler<T> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType) throws SQLException {
        if(t==null){
            preparedStatement.setString(i, null);
        }else {
            try{
                String jsonStr = JSON.toJSONString(t);
                preparedStatement.setString(i, jsonStr);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        T t = null;
        String tsStr = resultSet.getString(s);
        t = createTypeObject(tsStr);
        return t;
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        T  t = null;
        String tsStr = resultSet.getString(i);
        t = createTypeObject(tsStr);
        return t;
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        T  t = null;
        String tsStr = callableStatement.getString(i);
        t = createTypeObject(tsStr);
        return t;
    }

    private T createTypeObject(String tsStr) throws SQLException {
        T t = null;
        if(tsStr!=null){
            try{
                t = createObject(tsStr);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return t;
    }

    /**
     *  把json字符串转换成对象数组
     *
     *  <p>
     * 把json字符串转换成对象数组
     *
     * @param tsStr json字符串
     *
     * @return java.util.List<T> 对象List
     *
     * @author caobaoyu
     * @date 2020/10/28 10:36
     */
    public abstract T createObject(String tsStr) throws Exception;
}
