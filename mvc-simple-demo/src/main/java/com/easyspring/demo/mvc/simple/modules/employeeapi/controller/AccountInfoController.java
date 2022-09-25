package com.easyspring.demo.mvc.simple.modules.employeeapi.controller;

import com.easyspring.core.model.Page;
import com.easyspring.core.model.QueryParameter;
import com.easyspring.core.model.ResponseModel;
import com.easyspring.core.pattern.mvc.simple.controller.AbstractController;
import com.easyspring.demo.mvc.simple.modules.employeeapi.model.AccountInfo;
import com.easyspring.demo.mvc.simple.modules.employeeapi.service.AccountInfoService;
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
@Api(value = "AccountInfoController", tags = {"账号-接口"})
@RequestMapping("accountInfos")
@Slf4j
public class AccountInfoController extends AbstractController {

    @Resource
    private AccountInfoService accountInfoService;

    @ApiOperation(value = "查询账号列表", notes = "查询账号列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = Page.PAGE_NUM_PARAM_NAME, value = "页序号", paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = Page.PAGE_SIZE_PARAM_NAME, value = "页大小", paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = "sort", value = "排序表达式,格式:<+ | -><排序字段>;多个字段排序采用“,”分割", paramType = "query", dataType = "string")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Page<AccountInfo>> queryPage() throws Exception {
        QueryParameter q = builderRequestModel(DEFAULT_QUERY_PAGE_METHOD_NAME);
        builderSort(q);
        return ResponseModel.success(accountInfoService.queryPage(q, getUser()));
    }

    @ApiOperation(value = "查询账号详情", notes = "查询账号详情")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "用户ID", paramType = "path", dataType = "String")
    })
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<AccountInfo> queryDetails(@PathVariable(value = "id") String id) throws Exception {
        return ResponseModel.success(accountInfoService.queryDetails(id, getUser()));
    }

    @ApiOperation(value = "添加账号信息", notes = "添加账号信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "accountInfo", value = "账号信息", required = true, paramType = "body", dataType = "AccountInfo")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<String> add(@RequestBody AccountInfo accountInfo) throws Exception {
        return ResponseModel.success(accountInfoService.add(accountInfo, getUser()));
    }

    @ApiOperation(value = "修改账号信息-全部字段", notes = "修改账号信息-全部字段")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "用户ID", paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "accountInfo", value = "账号信息", required = true, paramType = "body", dataType = "AccountInfo")
    })
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Boolean> editAll(@PathVariable(value = "id") String id, @RequestBody AccountInfo accountInfo) throws Exception {
        return ResponseModel.success(accountInfoService.editAll(id, accountInfo, getUser()));
    }

    @ApiOperation(value = "修改账号信息-有的字段", notes = "修改账号信息-有的字段")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "用户ID", paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "accountInfo", value = "账号信息", required = true, paramType = "body", dataType = "AccountInfo")
    })
    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Boolean> edit(@PathVariable(value = "id") String id, @RequestBody AccountInfo accountInfo) throws Exception {
        return ResponseModel.success(accountInfoService.edit(id, accountInfo, getUser()));
    }

    @ApiOperation(value = "删除账号", notes = "删除账号")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "用户ID", paramType = "path", dataType = "String")
    })
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Boolean> remove(@PathVariable(value = "id") String id) throws Exception {
        return ResponseModel.success(accountInfoService.remove(id, getUser()));
    }

    @ApiOperation(value = "批量删除账号", notes = "批量删除账号")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ids", value = "用户ID", paramType = "query", allowMultiple = true, dataType = "String")
    })
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Integer> removeMulti(@RequestParam(value = "ids") List<String> ids) throws Exception {
        return ResponseModel.success(accountInfoService.removeMulti(ids, getUser()));
    }

    @Override
    public Class<?> loader() {
        return this.getClass();
    }

    @Override
    public Map<String, String> getColumnMap() {
        return SORT_COLUMN_MAP;
    }


    private static final Map<String, String> SORT_COLUMN_MAP = new HashMap() {{
        put("id", AccountInfoService.id);
        put("accountName", AccountInfoService.accountName);
        put("gender", AccountInfoService.gender);
        put("createTime", AccountInfoService.createTime);
    }};
}

