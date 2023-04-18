/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数模型  .  .
 *
 * <p>
 * 请求参数模型  .
 *
 * @author caobaoyu
 * @date 2020/5/15 14:51
 */
public class QueryParameter extends HashMap<String, Object> {

    /**
     * 请求参数构建器
     */
    public static class QueryParameterBuilder{
        private HashMap<String, Object> data;
        public QueryParameterBuilder(){
            data = new HashMap<>();
        }
        public QueryParameterBuilder add(String key, Object value){
            this.data.put(key, value);
            return this;
        }

        public QueryParameterBuilder setPageNum(Integer pageNum){
            this.data.put(Page.PAGE_NUM_PARAM_NAME, pageNum);
            return this;
        }

        public QueryParameterBuilder setPageSize(Integer pageSize){
            this.data.put(Page.PAGE_SIZE_PARAM_NAME, pageSize);
            return this;
        }
        public QueryParameter build(){
            QueryParameter queryParameter = new QueryParameter(data);
            queryParameter.initPage();
            return queryParameter;
        }
    }

    // 排序参数名称
    public static final String SORT_PARAM_KEY_NAME = "sort";
    // 排序参数分割符号
    public static final String SORT_PARAM_VALUE_DELIMITER = ",";
    // 升序符号
    public static final String SORT_TYPE_ASC = "ASC";
    // 将序符号
    public static final String SORT_TYPE_DESC = "DESC";
    public static QueryParameterBuilder builder() {
        return new QueryParameterBuilder();
    }
    // 分页模型
    private Page<?> pageModel;

    public QueryParameter(HashMap<String, Object> data){
        super(data);
    }
    public QueryParameter(){
        super();
    }

    // 获取分页模型
    public Page<?> getPageModel() {
        return pageModel;
    }

    // 设置分页模型
    public void setPageModel(Page<?> pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 分页信息
     *
     * @author caobaoyu
     * @date 2020/5/15 15:01
     */
    @JsonIgnore
    public void initPage() {
        Object pageNumObject = this.get(Page.PAGE_NUM_PARAM_NAME);
        Object pageSizeObject = this.get(Page.PAGE_SIZE_PARAM_NAME);
        Integer pageNum = 1;
        Integer pageSize = 0;
        if (pageNumObject != null) {
            pageNum = (Integer) pageNumObject;
        }
        if (pageSizeObject != null) {
            pageSize = (Integer) pageSizeObject;
        }
        pageModel = new Page<>(pageNum, pageSize);

    }

    @JsonIgnore
    public QueryParameter add(String key,Object value){
        if (key != null) {
            put(key, value);
        }
        return this;
    }

    /**
     * 构建排序字符串
     *
     * @param columnMap 排序字段映射Map
     * @author caobaoyu
     * @date 2020/5/15 15:10
     */
    @JsonIgnore
    public void sort(Map<String, String> columnMap) {
        StringBuilder sortBuffer = new StringBuilder();
        String sortExpression;
        Object sortParam = this.get(QueryParameter.SORT_PARAM_KEY_NAME);
        if (sortParam != null && columnMap != null && columnMap.size() > 0) {
            sortExpression = (String) sortParam;
            String[] sortArray = sortExpression.split(QueryParameter.SORT_PARAM_VALUE_DELIMITER);
            boolean first = true;
            for (String s : sortArray) {
                if (s != null && !"".equals(s) && s.length() > 1) {
                    String sortTypeStr = s.substring(0, 1);
                    String sortType = null;
                    String columnName = columnMap.get(s.substring(1));
                    switch (sortTypeStr) {
                        case "+":
                            sortType = QueryParameter.SORT_TYPE_ASC;
                            break;
                        case "-":
                            sortType = QueryParameter.SORT_TYPE_DESC;
                            break;
                        default:
                            break;
                    }
                    if (sortType != null && columnName != null) {
                        if (!first) {
                            sortBuffer.append(",");
                        }
                        sortBuffer.append(columnName).append(" ").append(sortType);
                        first = false;
                    }

                }
            }
        }
        String sort = sortBuffer.toString();
        if ("".equals(sort)) {
            this.put(QueryParameter.SORT_PARAM_KEY_NAME, "");
        } else {

            this.put(QueryParameter.SORT_PARAM_KEY_NAME, String.format(" ORDER BY %s", sort));
        }
    }

    /**
     * 判断是否需要分页
     *
     * @return ture:需要分页;false:不需要分页
     * @author caobaoyu
     * @date 2020/5/15 15:10
     */
    @JsonIgnore
    public Boolean isPage() {
        Boolean pageFlag = Boolean.FALSE;
        if (this.containsKey(Page.PAGE_SIZE_PARAM_NAME)) {
            Object pageSizeObj = this.get(Page.PAGE_SIZE_PARAM_NAME);
            if (pageSizeObj instanceof Integer) {
                pageFlag = Boolean.TRUE;
            }
        }
        return pageFlag;
    }
}
