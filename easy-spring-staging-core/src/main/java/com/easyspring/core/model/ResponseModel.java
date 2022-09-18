/**
 * Copyright: Copyright(C) 2020 Easy-Java-Rest-Framework.
 */
package com.easyspring.core.model;

import com.easyspring.core.exception.ExceptionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * WEB 响应模型类  .
 *
 * <p>
 * WEB 响应模型类
 *
 * @author caobaoyu
 * @date 2020/3/20 16:30
 */
@ApiModel(value = "ResponseModel", description = "响应信息")
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseModel<D> implements Serializable {


    public static<D> ResponseModel<D> success(D d){
        return new ResponseModel(ExceptionEnum.OK.getCode(),ExceptionEnum.OK.getMessage(),d);
    }

    public static<D> ResponseModel<D> fail(ExceptionEnum exceptionEnum, D d){
        return new ResponseModel(exceptionEnum.getCode(), exceptionEnum.getMessage() ,d);
    }


    public ResponseModel(Integer code, String message, D data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     *  返回代码
     */
    @ApiModelProperty(value = "状态码", required = true)
    private Integer code;

    @ApiModelProperty(value = "描述信息", required = true)
    private String message;

    /**
     *  数据
     */
    @ApiModelProperty(value = "响应数据")
    private D data;


}
