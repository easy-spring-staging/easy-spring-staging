package com.easyspring.demo.mvc.simple.modules.test.controller;

import com.easyspring.core.argument.SwaggerParam;
import com.easyspring.core.argument.UserParam;
import com.easyspring.core.model.Page;
import com.easyspring.core.model.QueryParameter;
import com.easyspring.core.model.ResponseModel;
import com.easyspring.core.pattern.mvc.simple.controller.BaseRest;
import com.easyspring.core.sercurity.AuthorizationUser;
import com.easyspring.demo.mvc.simple.modules.test.model.EmployeeInfo;
import com.easyspring.demo.mvc.simple.modules.test.service.EmployeeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "EmployeeInfoController", tags = {"员工-接口"})
@RequestMapping("employeeInfos")
@Slf4j
public class EmployeeInfoController implements BaseRest {
	
	@Resource
    private EmployeeInfoService employeeInfoService;
	
	@ApiOperation(value = "查询员工列表", notes = "查询员工列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = Page.PAGE_NUM_PARAM_NAME, value = "页序号", paramType = "query", dataType = "Int"),
        @ApiImplicitParam(name = Page.PAGE_SIZE_PARAM_NAME, value = "页大小", paramType = "query", dataType = "Int"),
		@ApiImplicitParam(name = QueryParameter.SORT_PARAM_KEY_NAME, value = "排序表达式,格式:<+ | -><排序字段>;多个字段排序采用“,”分割", paramType = "query", dataType = "string")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Page<EmployeeInfo>> queryPage(@SwaggerParam QueryParameter q, @UserParam AuthorizationUser u) throws Exception {
		q.sort(getColumnMap());
        return ResponseModel.success(employeeInfoService.queryPage(q, u));
    }
	
	@ApiOperation(value = "查询员工详情", notes = "查询员工详情")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "id", value = "员工ID", paramType = "path", dataType = "String")
    })
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<EmployeeInfo> queryDetails(@PathVariable(value = "id") String id, @UserParam AuthorizationUser u) throws Exception {
        return ResponseModel.success(employeeInfoService.queryDetails(id, u));
    }

    @ApiOperation(value = "添加员工信息", notes = "添加员工信息")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "employeeInfo", value = "员工信息", required = true, paramType = "body", dataType = "EmployeeInfo")
    })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<String> add(@RequestBody EmployeeInfo employeeInfo, @UserParam AuthorizationUser u) throws Exception {
        return ResponseModel.success(employeeInfoService.add(employeeInfo, u));
    }
	
	@ApiOperation(value = "修改员工信息-全部字段", notes = "修改员工信息-全部字段")
    @ApiImplicitParams(value = {
		@ApiImplicitParam(name = "id", value = "员工ID", paramType = "path", dataType = "String"),
        @ApiImplicitParam(name = "employeeInfo", value = "员工信息", required = true, paramType = "body", dataType = "EmployeeInfo")
    })
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Boolean> editAll(@PathVariable(value = "id") String id, @RequestBody EmployeeInfo employeeInfo, @UserParam AuthorizationUser u) throws Exception {
        return ResponseModel.success(employeeInfoService.editAll(id, employeeInfo, u));
    }
	
	@ApiOperation(value = "修改员工信息-有的字段", notes = "修改员工信息-有的字段")
    @ApiImplicitParams(value = {
		@ApiImplicitParam(name = "id", value = "员工ID", paramType = "path", dataType = "String"),
        @ApiImplicitParam(name = "employeeInfo", value = "员工信息", required = true, paramType = "body", dataType = "EmployeeInfo")
    })
    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Boolean> edit(@PathVariable(value = "id") String id, @RequestBody EmployeeInfo employeeInfo, @UserParam AuthorizationUser u) throws Exception {
        return ResponseModel.success(employeeInfoService.edit(id, employeeInfo, u));
    }
	
	@ApiOperation(value = "删除员工", notes = "删除员工")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "id", value = "员工ID", paramType = "path", dataType = "String")
    })
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Boolean> remove(@PathVariable(value = "id") String id, @UserParam AuthorizationUser u) throws Exception {
        return ResponseModel.success(employeeInfoService.remove(id, u));
    }
	
	@ApiOperation(value = "批量删除员工", notes = "批量删除员工")
    @ApiImplicitParams(value = {
         @ApiImplicitParam(name = "ids", value = "员工ID", paramType = "query", allowMultiple = true, dataType = "String")
    })
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Integer> removeMulti(@RequestParam(value = "ids") List<String> ids, @UserParam AuthorizationUser u) throws Exception {
        return ResponseModel.success(employeeInfoService.removeMulti(ids, u));
    }

	@Override
    public Map<String, String> getColumnMap() {
        return SORT_COLUMN_MAP;
    }
	
    private static final Map<String,String> SORT_COLUMN_MAP = new HashMap(){{
        put("id", EmployeeInfoService.id);
        put("accountId", EmployeeInfoService.accountId);
        put("employeeName", EmployeeInfoService.employeeName);
        put("entryTime", EmployeeInfoService.entryTime);
    }};
}

