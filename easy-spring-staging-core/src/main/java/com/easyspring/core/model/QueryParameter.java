/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;

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
     * 分页信息
     */
    private Page pageModel;

    public Page getPageModel() {
        return pageModel;
    }

    public void setPageModel(Page pageModel) {
        this.pageModel = pageModel;
    }

    @JsonIgnore
    public void initPage(){
        Object pageNumObject = this.get(Page.PAGE_NUM_PARAM_NAME);
        Object pageSizeObject = this.get(Page.PAGE_SIZE_PARAM_NAME);
        Integer pageNum = 1;
        Integer pageSize = 0;
        if(pageNumObject!=null){
            pageNum = (Integer) pageNumObject;
        }
        if(pageSizeObject!=null){
            pageSize = (Integer) pageSizeObject;
        }
        pageModel = new Page(pageNum,pageSize);

    }
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer("RequestModel(");
        boolean first = true;
        for(String key : this.keySet()){
            if(!first){
                sb.append(",");
            }else {
                first = false;
            }
            sb.append("\"").append(key).append("\"").append("= ").append(this.get(key).toString());
        }
        if(pageModel!=null){
            sb.append("\"").append(pageModel).append("\"").append("= ").append(pageModel.toString()).append(",");
        }
        sb.append(")");
        return sb.toString();
    }

    public Boolean isPage(){
        Boolean pageFlag = Boolean.FALSE;
        if(this.containsKey(Page.PAGE_SIZE_PARAM_NAME)){
            Object pageSizeObj = this.get(Page.PAGE_SIZE_PARAM_NAME);
            if(pageSizeObj!=null && pageSizeObj instanceof Integer){
                pageFlag = Boolean.TRUE;
            }
        }
        return pageFlag;
    }
}
