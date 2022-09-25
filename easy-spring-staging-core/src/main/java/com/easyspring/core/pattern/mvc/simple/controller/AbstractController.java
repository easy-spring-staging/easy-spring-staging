/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.pattern.mvc.simple.controller;

import com.easyspring.core.model.QueryParameter;
import com.easyspring.core.exception.EasyRestException;
import com.easyspring.core.exception.ExceptionEnum;
import com.easyspring.core.sercurity.AuthorizationUser;
import com.easyspring.core.utils.SpringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller抽象类  .
 *
 * <p>
 * Controller抽象类，实现部分抽象功能供实现类调用
 *
 * @author caobaoyu
 * @date 2020/5/16 14:31
 */
public abstract class AbstractController {
    protected static final String DEFAULT_QUERY_PAGE_METHOD_NAME = "queryPage";
    protected static final String DEFAULT_QUERY_COUNT_METHOD_NAME = "queryCount";
    private static final String PARAM_TYPE_FORM = "form";
    private static final String PARAM_TYPE_QUERY = "query";
    private static final String PARAM_TYPE_HEADER = "header";

    private static final String PARAM_DATA_TYPE_UPPER_STRING = "String";
    private static final String PARAM_DATA_TYPE_LOWER_STRING = "string";

    private static final String PARAM_DATA_TYPE_UPPER_INTEGER = "Int";
    private static final String PARAM_DATA_TYPE_LOWER_INTEGER = "int";

    private static final String PARAM_DATA_TYPE_UPPER_LONG = "Long";
    private static final String PARAM_DATA_TYPE_LOWER_LONG = "long";

    private static final String PARAM_DATA_TYPE_UPPER_FLOAT = "Float";
    private static final String PARAM_DATA_TYPE_LOWER_FLOAT = "float";

    private static final String PARAM_DATA_TYPE_UPPER_DOUBLE = "Double";
    private static final String PARAM_DATA_TYPE_LOWER_DOUBLE = "double";


    private static final String PARAM_DATA_TYPE_UPPER_DATE = "Date";
    private static final String PARAM_DATA_TYPE_LOWER_DATE = "date";

    private static final String PARAM_DATA_TYPE_UPPER_BOOLEAN = "Boolean";
    private static final String PARAM_DATA_TYPE_LOWER_BOOLEAN = "boolean";

    private static final String EMPTY_DATA_VALUE = "";
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Controller方法Swagger参数说明数组Map，以方法名称为key存储Controller方法的@ApiImplicitParam声明信息.
     */
    private Map<String, ApiImplicitParam[]> apiImplicitParamMap;

    @PostConstruct
    private void loadParamMetaData() {
        init();
        Class<?> controllerClass = loader();
        loadApiImplicitParams(controllerClass);
    }

    /**
     * 实现类需要调用该方法进行业务上的类参数检查的加载.
     *
     * <p>
     * 实现类需要调用该方法进行业务上的类参数检查的加载.
     *
     * @param
     * @return java.lang.Class<?>
     * @author caobaoyu
     * @date 2020/5/20 8:58
     */
    public abstract Class<?> loader();

    protected AuthorizationUser getUser() {
        AuthorizationUser user = null;
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        Object userObj = request.getAttribute(AuthorizationUser.DEFAULT_USER_KEY_NAME);
        if (userObj != null && userObj instanceof AuthorizationUser) {
            user = (AuthorizationUser) userObj;
        }
        return user;
    }

    public abstract Map<String, String> getColumnMap();

    protected void builderSort(QueryParameter q) {
        String sortExpression = null;
        Object sortParam = q.get(QueryParameter.SORT_PARAM_KEY_NAME);
        if (sortParam != null) {
            sortExpression = (String) sortParam;
        }
        StringBuffer sortBuffer = new StringBuffer();
        if (sortExpression != null && getColumnMap() != null && getColumnMap().size() > 0) {
            String[] sortArray = sortExpression.split(QueryParameter.SORT_PARAM_VALUE_DELIMITER);
            boolean first = true;
            for (String s : sortArray) {
                if (s != null && !"".equals(s) && s.length() > 1) {
                    String sortTypeStr = s.substring(0, 1);
                    String sortType = null;
                    String columnName = getColumnMap().get(s.substring(1, s.length()));
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
            q.put(QueryParameter.SORT_PARAM_KEY_NAME, "");
        } else {

            q.put(QueryParameter.SORT_PARAM_KEY_NAME, String.format(" ORDER BY %s", sort));
        }
    }


    /**
     * 初始化存参数检查数据存储信息 .
     *
     * <p>
     * 初始化存参数检查数据存储信息
     *
     * @author caobaoyu
     * @date 2018/8/23 18:30
     */
    private void init() {
        apiImplicitParamMap = new HashMap(5);
    }

    /**
     * 加载Controller类方法swagger声明的参数，用@ApiImplicitParam声明的信息 .
     *
     * @param controllerClass Controller类
     * @author caobaoyu
     * @date 2019/2/26 9:33
     */
    private void loadApiImplicitParams(Class<?> controllerClass) {
        if (controllerClass != null) {
            Method[] methods = controllerClass.getMethods();
            if (methods != null) {
                for (Method method : methods) {
                    String methodName = method.getName();
                    ApiImplicitParams apiImplicitParams = method.getDeclaredAnnotation(ApiImplicitParams.class);
                    if (apiImplicitParams != null) {
                        apiImplicitParamMap.put(methodName, apiImplicitParams.value());
                    }
                }
            }
        }
    }

    /**
     * 构建RequestModel
     *
     * <p>
     * 构建RequestModel,用于form、query、header方式提交的参数构建 RequestModel
     *
     * @param methodName 方法名称
     * @return RequestModel 请求模型
     * @author caobaoyu
     * @date 2020/5/18 9:40
     */
    public QueryParameter builderRequestModel(String methodName) throws Exception {
        HttpServletRequest request = SpringUtil.getServletRequest();
        QueryParameter rm = new QueryParameter();
        if (request != null && methodName != null && apiImplicitParamMap != null) {
            ApiImplicitParam[] apiImplicitParamArray = apiImplicitParamMap.get(methodName);
            if (apiImplicitParamArray != null) {
                for (ApiImplicitParam apiImplicitParam : apiImplicitParamArray) {
                    String paramType = apiImplicitParam.paramType();
                    boolean required = apiImplicitParam.required();
                    String name = apiImplicitParam.name();
                    String dataType = apiImplicitParam.dataType();
                    String defaultValue = apiImplicitParam.defaultValue();
                    String format = apiImplicitParam.format();
                    String paramValue = null;
                    if (PARAM_TYPE_FORM.equals(paramType) || PARAM_TYPE_QUERY.equals(paramType)) {
                        paramValue = request.getParameter(name);
                    } else if (PARAM_TYPE_HEADER.equals(paramType)) {
                        paramValue = request.getHeader(name);
                    }
                    if (PARAM_TYPE_FORM.equals(paramType) || PARAM_TYPE_QUERY.equals(paramType) || PARAM_TYPE_HEADER.equals(paramType)) {
                        Object paramObj = convert(name, dataType, paramValue, required, defaultValue, format);
                        if (paramObj != null) {
                            rm.put(name, paramObj);
                        }
                    }
                }
            }
        }
        return rm;
    }

    /**
     * 字符串转换对象 .
     *
     * <p>
     * 字符串转换对象
     *
     * @param name         参数名称
     * @param dataType     参数数据类型
     * @param paramValue   参数值
     * @param required     是否必须
     * @param defaultValue 缺省值
     * @param format       格式
     * @return java.lang.Object
     * @author caobaoyu
     * @date 2020/5/18 9:43
     */
    private Object convert(String name, String dataType, String paramValue, boolean required, String defaultValue, String format) throws Exception {
        Object value = null;
        if (required) {
            if (paramValue == null || EMPTY_DATA_VALUE.equals(paramValue)) {
                new EasyRestException(ExceptionEnum.ERROR_BAD_REQUEST.getCode(), String.format("参数：“%s”不能为空!", name));
            } else {
                value = parse(name, dataType, paramValue, format);
            }
        } else {
            if (paramValue == null || EMPTY_DATA_VALUE.equals(paramValue)) {
                if (defaultValue == null || EMPTY_DATA_VALUE.equals(defaultValue)) {
                    value = null;
                } else {
                    value = parse(name, dataType, paramValue, format);
                }
            } else {
                value = parse(name, dataType, paramValue, format);
            }
        }
        return value;
    }

    /**
     * 解析参数 .
     *
     * <p>
     * 解析参数
     *
     * @param name       参数名称
     * @param dataType   参数数据类型
     * @param paramValue 参数值
     * @param format     参数格式
     * @return java.lang.Object
     * @author caobaoyu
     * @date 2020/5/18 9:45
     */
    private Object parse(String name, String dataType, String paramValue, String format) throws Exception {
        Object value = null;
        if (dataType != null && !EMPTY_DATA_VALUE.equals(dataType)) {
            try {
                if (PARAM_DATA_TYPE_UPPER_STRING.equals(dataType) || PARAM_DATA_TYPE_LOWER_STRING.equals(dataType)) {
                    value = paramValue;
                }
                if (PARAM_DATA_TYPE_UPPER_INTEGER.equals(dataType) || PARAM_DATA_TYPE_LOWER_INTEGER.equals(dataType)) {
                    value = new Integer(paramValue);
                }
                if (PARAM_DATA_TYPE_UPPER_LONG.equals(dataType) || PARAM_DATA_TYPE_LOWER_LONG.equals(dataType)) {
                    value = new Long(paramValue);
                }
                if (PARAM_DATA_TYPE_UPPER_FLOAT.equals(dataType) || PARAM_DATA_TYPE_LOWER_FLOAT.equals(dataType)) {
                    value = new Float(paramValue);
                }
                if (PARAM_DATA_TYPE_UPPER_DOUBLE.equals(dataType) || PARAM_DATA_TYPE_LOWER_DOUBLE.equals(dataType)) {
                    value = new Double(paramValue);
                }
                if (PARAM_DATA_TYPE_UPPER_DATE.equals(dataType) || PARAM_DATA_TYPE_LOWER_DATE.equals(dataType)) {
                    SimpleDateFormat sdf;
                    if (format != null && !EMPTY_DATA_VALUE.equals(format)) {
                        sdf = new SimpleDateFormat(format);
                    } else {
                        sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
                    }
                    value = sdf.parse(paramValue);
                }
                if (PARAM_DATA_TYPE_UPPER_BOOLEAN.equals(dataType) || PARAM_DATA_TYPE_LOWER_BOOLEAN.equals(dataType)) {
                    value = new Boolean(paramValue);
                }
            } catch (Exception e) {
                throw new EasyRestException(ExceptionEnum.ERROR_BAD_REQUEST.getCode(), String.format("参数：“%s = %s”格式不正确", name, paramValue));
            }
        }
        return value;
    }
}
