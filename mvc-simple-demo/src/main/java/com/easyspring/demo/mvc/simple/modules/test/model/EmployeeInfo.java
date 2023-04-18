package com.easyspring.demo.mvc.simple.modules.test.model;

import com.easyspring.core.model.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "EmployeeInfo", description = "员工-模型类")
public class EmployeeInfo implements Model<String> {

  // 员工ID
  @ApiModelProperty(value = "员工ID")
  private String id;

  // 账号ID
  @ApiModelProperty(value = "账号ID")
  private String accountId;

  // 员工姓名
  @ApiModelProperty(value = "员工姓名")
  private String employeeName;

  // 入职日期
  @ApiModelProperty(value = "入职日期, 格式:yyyy-MM-dd")
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
  private Date entryTime;

  @ApiModelProperty(value = "账号信息")
  private AccountInfo accountInfo;

  @ApiModelProperty(value = "薪水列表")
  private List<SalaryInfo> salaryInfos;

  @Override
  public String getKey() {
    return this.id;
  }
}

