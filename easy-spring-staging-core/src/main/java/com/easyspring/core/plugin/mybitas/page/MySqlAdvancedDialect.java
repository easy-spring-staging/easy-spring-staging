/**
 * Copyright: Copyright(C) 2020 Easy-Java-Rest-Framework.
 */
package com.easyspring.core.plugin.mybitas.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.dialect.AbstractHelperDialect;
import com.github.pagehelper.util.MetaObjectUtil;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能简介  .
 *
 * <p>
 * 功能详细描述
 *
 * @author caobaoyu
 * @date 2020/3/23 9:58
 */
public class MySqlAdvancedDialect extends AbstractHelperDialect {
    public static final Pattern PATTERN_PAGE_SQL_RGEX = Pattern.compile("([\\s\\S]*)\\/\\*\\{\\*\\/([\\s\\S]*)\\/\\*\\}\\*/([\\s\\S]*)");

    @Override
    public Object processPageParameter(MappedStatement ms, Map<String, Object> paramMap, Page page, BoundSql boundSql, CacheKey pageKey) {
        paramMap.put("First_PageHelper", page.getStartRow());
        paramMap.put("Second_PageHelper", page.getPageSize());
        pageKey.update(page.getStartRow());
        pageKey.update(page.getPageSize());
        if (boundSql.getParameterMappings() != null) {
            List<ParameterMapping> newParameterMappings = new ArrayList(boundSql.getParameterMappings());
            if (page.getStartRow() == 0) {
                newParameterMappings.add((new ParameterMapping.Builder(ms.getConfiguration(), "Second_PageHelper", Integer.class)).build());
            } else {
                newParameterMappings.add((new ParameterMapping.Builder(ms.getConfiguration(), "First_PageHelper", Integer.class)).build());
                newParameterMappings.add((new ParameterMapping.Builder(ms.getConfiguration(), "Second_PageHelper", Integer.class)).build());
            }

            MetaObject metaObject = MetaObjectUtil.forObject(boundSql);
            metaObject.setValue("parameterMappings", newParameterMappings);
        }

        return paramMap;
    }

    @Override
    public String getPageSql(String sql, Page page, CacheKey pageKey) {
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
        String[] sqlArray = parserPageSql(sql);
        if(sqlArray==null) {
            sqlBuilder.append(sql);
            if (page.getStartRow() == 0) {
                sqlBuilder.append(" LIMIT ? ");
            } else {
                sqlBuilder.append(" LIMIT ?, ? ");
            }
        }else{
            sqlBuilder.append(sqlArray[0]);
            sqlBuilder.append(sqlArray[1]);
            if (page.getStartRow() == 0) {
                sqlBuilder.append(" LIMIT ? ");
            } else {
                sqlBuilder.append(" LIMIT ?, ? ");
            }
            sqlBuilder.append(sqlArray[2]);
        }

        return sqlBuilder.toString();
    }

    @Override
    public String getCountSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey countKey) {
        String[] sqlArray = parserPageSql(boundSql.getSql());
        if(sqlArray==null) {
            return this.countSqlParser.getSmartCountSql(boundSql.getSql());
        }else {
            return this.countSqlParser.getSmartCountSql(sqlArray[1]);
        }
    }

    private String[] parserPageSql(String sql){
        String [] sqlArray = null;
        if(sql!=null){
            Matcher matcher = PATTERN_PAGE_SQL_RGEX.matcher(sql);
            if (matcher.find()) {
                sqlArray = new String[3];
                sqlArray[0] = matcher.group(1);
                sqlArray[1] = matcher.group(2);
                sqlArray[2] = matcher.group(3);
            }
        }
        return sqlArray;
    }
}
