/**
 * Copyright: Copyright(C) 2020 Easy-Java-Rest-Framework.
 */

package com.easyspring.core.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页模型类  .
 *
 * <p>
 * 分页模型类
 *
 * @author caobaoyu
 * @date 2020/3/20 15:14
 */
@ApiModel(value = "PageModel", description = "分页")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Page<M> {

    // 每页大小参数名称
    public static final String PAGE_SIZE_PARAM_NAME = "pageSize";

    // 页序号参数名称
    public static final String PAGE_NUM_PARAM_NAME = "pageNum";

    /**
     * 通过pagehelper插件的page模型构建Page
     *
     * @param p pagehelper插件的page模型
     * @author caobaoyu
     * @date 2020/3/20 15:20
     */
    public Page(com.github.pagehelper.Page<M> p) {
        this.pageNum = p.getPageNum();
        this.pageSize = p.getPageSize();
        this.startRow = p.getStartRow();
        this.endRow = p.getEndRow();
        this.total = p.getTotal();
        this.pages = p.getPages();
        this.list = p.getResult();
    }

    /**
     * 通过page和list构建Page
     *
     * @param p         分析模型
     * @param modelList 数据模型List
     * @author caobaoyu
     * @date 2020/3/20 15:22
     */

    public Page(Page p, List<M> modelList) {
        this.pageNum = p.getPageNum();
        this.pageSize = p.getPageSize();
        this.startRow = p.getStartRow();
        this.endRow = p.getEndRow();
        this.total = p.getTotal();
        this.pages = p.getPages();
        this.list = modelList;

    }

    public Page(List<M> modelList) {
        if (modelList != null) {
            int modelListSizeInt = modelList.size();
            Long modelListSizeLong = Long.getLong(Integer.toString(modelListSizeInt));
            this.pageNum = 1;
            this.pageSize = modelListSizeInt;
            this.startRow = 1L;
            this.endRow = modelListSizeLong;
            this.total = modelListSizeLong;
            this.pages = 1;
            this.list = modelList;
        }
    }

    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /**
     * 页码，从1开始
     */
    @JsonProperty(value = PAGE_NUM_PARAM_NAME)
    @ApiModelProperty(value = "页码")
    private Integer pageNum;
    /**
     * 页面大小
     */
    @JsonProperty(value = PAGE_SIZE_PARAM_NAME)
    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;
    /**
     * 起始行
     */
    @ApiModelProperty(value = "起始行")
    private Long startRow;
    /**
     * 末行
     */
    @ApiModelProperty(value = "末行")
    private Long endRow;
    /**
     * 总数
     */
    @ApiModelProperty(value = "总数")
    private Long total;
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Integer pages;
    /**
     * 数据List
     */
    private List<M> list;
    /**
     * 附加信息，一般用于分页数据同时需要附加一下全局信息
     */
    private Map<String, Object> attachedInfo;

    /**
     * 添加附加信息 .
     *
     * <p>
     * 添加附加信息
     *
     * @param keyName key名称
     * @param value   值
     * @author caobaoyu
     * @date 2020/3/20 15:36
     */
    @JsonIgnore
    public void addAttachedInfo(String keyName, Object value) {
        if (keyName != null && value != null) {
            if (attachedInfo == null) {
                attachedInfo = new HashMap<>();
            }
            attachedInfo.put(keyName, value);
        }
    }

    @JsonAnyGetter
    public Map<String, Object> getAttachedInfo() {
        return attachedInfo;
    }


    public Integer getPageNum() {
        if (pageNum == null) {
            return 1;
        } else {
            return pageNum;
        }
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum < 1) {
            this.pageNum = 1;
        } else {
            this.pageNum = pageNum;
        }
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return 0;
        } else {
            return pageSize;
        }
    }

}
