package com.easyspring.core.argument;

import org.springframework.web.context.request.NativeWebRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ParameterUtil {

    private static final String PARAM_TYPE_FORM = "form";
    private static final String PARAM_TYPE_QUERY = "query";
    private static final String PARAM_TYPE_HEADER = "header";


    private static final String PARAM_DATA_TYPE_STRING = "string";

    private static final String PARAM_DATA_TYPE_INTEGER = "int";

    private static final String PARAM_DATA_TYPE_LONG = "long";

    private static final String PARAM_DATA_TYPE_FLOAT = "float";

    private static final String PARAM_DATA_TYPE_DOUBLE = "double";


    private static final String PARAM_DATA_TYPE_DATE = "date";

    private static final String PARAM_DATA_TYPE_BOOLEAN = "boolean";

    private static final String EMPTY_DATA_VALUE = "";
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";


    public static String getParamValue(String name, String paramType, NativeWebRequest webRequest) {
        String value = null;
        if (name != null && paramType != null) {
            switch (paramType) {
                case PARAM_TYPE_QUERY:
                case PARAM_TYPE_FORM:
                    value = webRequest.getParameter(name);
                    break;
                case PARAM_TYPE_HEADER:
                    value = webRequest.getHeader(name);
                    break;
                default:
            }
        }
        return value;
    }

    public static String[] getParamValues(String name, String paramType, NativeWebRequest webRequest) {
        String[] values = null;
        if (name != null && paramType != null) {
            switch (paramType) {
                case PARAM_TYPE_QUERY:
                case PARAM_TYPE_FORM:
                    values = webRequest.getParameterValues(name);
                    break;
                case PARAM_TYPE_HEADER:
                    values = webRequest.getHeaderValues(name);
                    break;
                default:
            }
        }
        return values;
    }

    public static Object convert(String stringValue, String dataType, String format) throws Exception {
        Object value = null;
        if (stringValue != null && dataType != null) {
            String lowerDataType = dataType.toLowerCase();
            switch (lowerDataType) {
                case PARAM_DATA_TYPE_STRING:
                    value = stringValue;
                    break;
                case PARAM_DATA_TYPE_INTEGER:
                    value = Integer.valueOf(stringValue);
                    break;
                case PARAM_DATA_TYPE_LONG:
                    value = Long.valueOf(stringValue);
                    break;
                case PARAM_DATA_TYPE_FLOAT:
                    value = Float.valueOf(stringValue);
                    break;
                case PARAM_DATA_TYPE_DOUBLE:
                    value = Double.valueOf(stringValue);
                    break;
                case PARAM_DATA_TYPE_BOOLEAN:
                    value = Boolean.valueOf(stringValue);
                    break;
                case PARAM_DATA_TYPE_DATE: {
                    SimpleDateFormat sdf;
                    if (format == null || EMPTY_DATA_VALUE.equals(format)) {
                        sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
                    } else {
                        sdf = new SimpleDateFormat(format);
                    }
                    value = sdf.parse(stringValue);
                }
                break;
                default:
            }
        }
        return value;
    }

    public static List<Object> convertList(String[] stringValues, String dataType, String format) throws Exception {
        List<Object> valueList = new ArrayList<>();
        if (stringValues != null && stringValues.length > 0 && dataType != null) {
            for (String v : stringValues) {
                Object parameterObject = convert(v, dataType, format);
                if (parameterObject != null) {
                    valueList.add(parameterObject);
                }
            }
        }
        if (valueList.size() > 0) {
            return valueList;
        } else {
            return null;
        }
    }
}
