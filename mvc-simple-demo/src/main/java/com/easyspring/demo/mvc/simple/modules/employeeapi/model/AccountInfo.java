package com.easyspring.demo.mvc.simple.modules.employeeapi.model;

import com.easyspring.core.model.AbstractModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountInfo", description = "账号-模型类")
public class AccountInfo extends AbstractModel<String> {

    // 用户ID
    @ApiModelProperty(value = "用户ID")
    private String id;

    // 账号名称
    @ApiModelProperty(value = "账号名称")
    private String accountName;

    // 性别(1:男;0:女)
    @ApiModelProperty(value = "性别(1:男;0:女)")
    private String gender;

    // 创建信息
    @ApiModelProperty(value = "创建信息, 格式:yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @Override
    public String getKey() {
        return this.id;
    }
}

