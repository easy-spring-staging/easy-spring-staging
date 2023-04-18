/**
 * Copyright(C) 2023 Company:深瞳大观科技有限公司
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
 * <p>分页模型</p> .
 *
 * @author caobaoyu
 * @date 2023/4/18 15:33
 */
@ApiModel(value = "Page", description = "分页")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Page<M extends Model<?>> {
    // 每页大小参数名称
    public static final String PAGE_SIZE_PARAM_NAME = "pageSize";

    // 页码参数名称
    public static final String PAGE_NUM_PARAM_NAME = "pageNo";

    // 是否还有前一页参数名称
    public static final String PAGE_HAS_PREV_PARAM_NAME = "hasPrev";

    // 是否还有前一页参数名称
    public static final String PAGE_HAS_NEXT_PARAM_NAME = "hasNext";

    // 总的页数参数名称
    public static final String PAGE_PAGE_COUNT_PARAM_NAME = "pageCount";

    // 总的数据条数参数名称
    public static final String PAGE_PAGE_TOTAL_PARAM_NAME = "total";

    // 数据项集合参数名称
    public static final String PAGE_PAGE_ITEMS_PARAM_NAME = "items";
    /**
     * 页码，从1开始
     */
    @JsonProperty(value = PAGE_NUM_PARAM_NAME)
    @ApiModelProperty(value = "页码")
    private Integer pageNo;

    /**
     * 页面大小
     */
    @JsonProperty(value = PAGE_SIZE_PARAM_NAME)
    @ApiModelProperty(value = "每页面大小")
    private Integer pageSize;

    /**
     * 是否还有前一页
     */
    @JsonProperty(value = PAGE_HAS_PREV_PARAM_NAME)
    @ApiModelProperty(value = "是否还有前一页")
    private Boolean hasPrev;

    /**
     * 是否还有后一页
     */
    @JsonProperty(value = PAGE_HAS_NEXT_PARAM_NAME)
    @ApiModelProperty(value = "是否还有后一页")
    private Boolean hasNext;

    /**
     * 总的页数
     */
    @JsonProperty(value = PAGE_PAGE_COUNT_PARAM_NAME)
    @ApiModelProperty(value = "总的页数")
    private Integer pageCount;

    /**
     * 总的数据条数
     */
    @JsonProperty(value = PAGE_PAGE_TOTAL_PARAM_NAME)
    @ApiModelProperty(value = "总的数据条数")
    private Long total;


    @JsonProperty(value = PAGE_PAGE_ITEMS_PARAM_NAME)
    @ApiModelProperty(value = "数据项集合")
    private List<M> items;


    /**
     * 附加信息，一般用于分页数据同时需要附加一下全局信息
     */
    private Map<String, Object> attachedInfo;

    public Page(com.github.pagehelper.Page<M> p) {
        this.pageNo = p.getPageNum();
        this.pageSize = p.getPageSize();
        this.total = p.getTotal();
        this.pageCount = p.getPages();
        this.items = p.getResult();
        this.hasNext = this.pageNo < this.pageCount;
        this.hasPrev = this.pageNo > 1;
    }

    public Page(Page<?> p, List<M> items) {
        this.pageNo = p.getPageNo();
        this.pageSize = p.getPageSize();
        this.total = p.getTotal();
        this.pageCount = p.getPageCount();
        this.items = items;
        this.hasNext = this.pageNo < this.pageCount;
        this.hasPrev = this.pageNo > 1;
    }

    public Page(List<M> items) {
        if (items != null) {
            int itemsTotalInteger = items.size();
            Long itemsTotalLong = Long.getLong(Integer.toString(itemsTotalInteger));
            this.pageNo = 1;
            this.pageSize = itemsTotalInteger;
            this.total = itemsTotalLong;
            this.pageCount = 1;
            this.items = items;
            this.hasNext = false;
            this.hasPrev = false;
        }
    }

    public Page(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    /**
     * 添加附加信息
     *
     * @param keyName 键名称
     * @param value   值
     * @author caobaoyu
     * @date 2023/4/18 15:44
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

    public Integer getPageNo() {
        if (pageNo == null) {
            return 1;
        } else {
            return pageNo;
        }
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo < 1) {
            this.pageNo = 1;
        } else {
            this.pageNo = pageNo;
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
