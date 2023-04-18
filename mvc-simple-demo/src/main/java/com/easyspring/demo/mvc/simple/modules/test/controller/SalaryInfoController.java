package com.easyspring.demo.mvc.simple.modules.test.controller;

import com.easyspring.core.argument.SwaggerParam;
import com.easyspring.core.argument.UserParam;
import com.easyspring.core.model.Page;
import com.easyspring.core.model.QueryParameter;
import com.easyspring.core.model.ResponseModel;
import com.easyspring.core.pattern.mvc.simple.controller.BaseRest;
import com.easyspring.core.sercurity.AuthorizationUser;
import com.easyspring.demo.mvc.simple.modules.test.model.SalaryInfo;
import com.easyspring.demo.mvc.simple.modules.test.service.SalaryInfoService;
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
@Api(value = "SalaryInfoController", tags = {"薪水-接口"})
@RequestMapping("salaryInfos")
@Slf4j
public class SalaryInfoController implements BaseRest {
	
	@Resource
    private SalaryInfoService salaryInfoService;
	
	@ApiOperation(value = "查询薪水列表", notes = "查询薪水列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = Page.PAGE_NUM_PARAM_NAME, value = "页序号", paramType = "query", dataType = "Int"),
        @ApiImplicitParam(name = Page.PAGE_SIZE_PARAM_NAME, value = "页大小", paramType = "query", dataType = "Int"),
		@ApiImplicitParam(name = QueryParameter.SORT_PARAM_KEY_NAME, value = "排序表达式,格式:<+ | -><排序字段>;多个字段排序采用“,”分割", paramType = "query", dataType = "string")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Page<SalaryInfo>> queryPage(@SwaggerParam QueryParameter q, @UserParam AuthorizationUser<?, ?, ?, ?> u) throws Exception {
		q.sort(getColumnMap());
        return ResponseModel.success(salaryInfoService.queryPage(u, q));
    }
	
	@ApiOperation(value = "查询薪水详情", notes = "查询薪水详情")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "id", value = "薪水ID", paramType = "path", dataType = "String")
    })
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<SalaryInfo> queryDetails(@PathVariable(value = "id") String id, @UserParam AuthorizationUser<?, ?, ?, ?> u) throws Exception {
        return ResponseModel.success(salaryInfoService.queryDetails(u, id));
    }

    @ApiOperation(value = "添加薪水信息", notes = "添加薪水信息")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "salaryInfo", value = "薪水信息", required = true, paramType = "body", dataType = "SalaryInfo")
    })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<String> add(@RequestBody SalaryInfo salaryInfo, @UserParam AuthorizationUser<?, ?, ?, ?> u) throws Exception {
        return ResponseModel.success(salaryInfoService.add(u, salaryInfo));
    }
	
	@ApiOperation(value = "修改薪水信息-全部字段", notes = "修改薪水信息-全部字段")
    @ApiImplicitParams(value = {
		@ApiImplicitParam(name = "id", value = "薪水ID", paramType = "path", dataType = "String"),
        @ApiImplicitParam(name = "salaryInfo", value = "薪水信息", required = true, paramType = "body", dataType = "SalaryInfo")
    })
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Boolean> editAll(@PathVariable(value = "id") String id, @RequestBody SalaryInfo salaryInfo, @UserParam AuthorizationUser<?, ?, ?, ?> u) throws Exception {
        return ResponseModel.success(salaryInfoService.editAll(u, id, salaryInfo));
    }
	
	@ApiOperation(value = "修改薪水信息-有的字段", notes = "修改薪水信息-有的字段")
    @ApiImplicitParams(value = {
		@ApiImplicitParam(name = "id", value = "薪水ID", paramType = "path", dataType = "String"),
        @ApiImplicitParam(name = "salaryInfo", value = "薪水信息", required = true, paramType = "body", dataType = "SalaryInfo")
    })
    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Boolean> edit(@PathVariable(value = "id") String id, @RequestBody SalaryInfo salaryInfo, @UserParam AuthorizationUser<?, ?, ?, ?> u) throws Exception {
        return ResponseModel.success(salaryInfoService.edit(u, id, salaryInfo));
    }
	
	@ApiOperation(value = "删除薪水", notes = "删除薪水")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "id", value = "薪水ID", paramType = "path", dataType = "String")
    })
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Boolean> remove(@PathVariable(value = "id") String id, @UserParam AuthorizationUser<?, ?, ?, ?> u) throws Exception {
        return ResponseModel.success(salaryInfoService.remove(u, id));
    }
	
	@ApiOperation(value = "批量删除薪水", notes = "批量删除薪水")
    @ApiImplicitParams(value = {
         @ApiImplicitParam(name = "ids", value = "薪水ID", paramType = "query", allowMultiple = true, dataType = "String")
    })
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Integer> removeMulti(@RequestParam(value = "ids") List<String> ids, @UserParam AuthorizationUser<?, ?, ?, ?> u) throws Exception {
        return ResponseModel.success(salaryInfoService.removeMulti(u, ids));
    }

	@Override
    public Map<String, String> getColumnMap() {
        return SORT_COLUMN_MAP;
    }
	
    private static final Map<String,String> SORT_COLUMN_MAP = new HashMap<String,String>(){{
        put("id", SalaryInfoService.id);
        put("accountId", SalaryInfoService.accountId);
        put("yearNumber", SalaryInfoService.yearNumber);
        put("monthNumber", SalaryInfoService.monthNumber);
        put("salaryAmount", SalaryInfoService.salaryAmount);
    }};
}

