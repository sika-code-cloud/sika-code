package com.sika.code.common.http.util;


import com.sika.code.basic.constant.BaseConstant.Charset;
import com.sika.code.common.http.constant.HttpErrorCodeEnum;
import com.sika.code.exception.BusinessException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * URL表达式处理器
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 上午11:26:37
 */
public class UriVariables {

    /**
     * 依次匹配
     *
     * @param uri          匹配的uri，带代表式
     * @param uriVariables 匹配表达式的值
     * @return 匹配完的url <code>
     * System.out.println(getUri(&quot;http://egan.in/{a}/ba/{a1}?{bb}={a1}&quot;, &quot;no1&quot;, &quot;no2&quot;, &quot;no3&quot;, &quot;no4&quot;));
     * 结果 http://egan.in/no1/ba/no2?no3=no4
     * </code>
     */
    public static String getUri(String uri, Object... uriVariables) {

        if (null == uriVariables) {
            return uri;
        }
        for (Object variable : uriVariables) {
            if (null == variable) {
                continue;
            }
            uri = uri.replaceFirst("\\{\\w+\\}", variable.toString());
        }
        return uri;
    }

    /**
     * 匹配Map.key
     *
     * @param uri          匹配的uri，带代表式
     * @param uriVariables 匹配表达式的值
     * @return 匹配完的url <code>
     * Map&lt;String, Object&gt;  uriVariable = new HashMap&lt;String, Object&gt;();
     * uriVariable.set(&quot;a&quot;, &quot;no1&quot;);
     * uriVariable.set(&quot;a1&quot;, &quot;no2&quot;);
     * uriVariable.set(&quot;bb&quot;, &quot;no3&quot;);
     * System.out.println(getUri(&quot;http://egan.in/{a}/ba/{a1}?{bb}={a1}&quot;, uriVariable));
     * 结果 http://egan.in/no1/ba/no2?no3=no2
     * </code>
     */
    public static String getUri(String uri, Map<String, Object> uriVariables) {

        if (null == uriVariables) {
            return uri;
        }
        for (String key : uriVariables.keySet()) {
            Object uriVariable = uriVariables.get(key);
            if (null == uriVariable) {
                continue;
            }

            uri = uri.replace("{" + key + "}", uriVariable.toString());
        }
        return uri;
    }

    /**
     * Map转化为对应得参数字符串
     *
     * @param pe 参数
     * @return 参数字符串
     */
    public static String getParameters(Map<String, Object> pe) {
        StringBuilder builder = new StringBuilder();
        for (Object key : pe.keySet()) {
            Object o = pe.get(key);

            if (null == o) {
                continue;
            }

            if (o instanceof List) {
                o = ((List<?>) o).toArray();
            }
            try {
                if (o instanceof Object[]) {
                    Object[] os = (Object[]) o;
                    String valueStr = "";
                    for (int i = 0, len = os.length; i < len; i++) {
                        if (null == os[i]) {
                            continue;
                        }
                        String value = os[i].toString().trim();
                        valueStr += (i == len - 1) ? value : value + ",";
                    }
                    builder.append(key).append("=").append(URLEncoder.encode(valueStr, Charset.UTF_8)).append("&");

                    continue;
                }
                builder.append(key).append("=").append(URLEncoder.encode(pe.get(key).toString(), Charset.UTF_8)).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    /**
     * 解析应答字符串，生成应答要素
     *
     * @param str 需要解析的字符串
     * @return 解析的结果map
     */
    public static Map<String, Object> getMapFromParameterStr(String str) {

        Map<String, Object> map = new HashMap<>();
        int len = str.length();
        StringBuilder temp = new StringBuilder();
        char curChar;
        String key = null;
        boolean isKey = true;
        boolean isOpen = false;// 值里有嵌套
        char openName = 0;
        if (len > 0) {
            for (int i = 0; i < len; i++) {// 遍历整个带解析的字符串
                curChar = str.charAt(i);// 取当前字符
                if (isKey) {// 如果当前生成的是key

                    if (curChar == '=') {// 如果读取到=分隔符
                        key = temp.toString();
                        temp.setLength(0);
                        isKey = false;
                    } else {
                        temp.append(curChar);
                    }
                } else {// 如果当前生成的是value
                    if (isOpen) {
                        if (curChar == openName) {
                            isOpen = false;
                        }

                    } else {// 如果没开启嵌套
                        if (curChar == '{') {// 如果碰到，就开启嵌套
                            isOpen = true;
                            openName = '}';
                        }
                        if (curChar == '[') {
                            isOpen = true;
                            openName = ']';
                        }
                    }
                    if (curChar == '&' && !isOpen) {// 如果读取到&分割符,同时这个分割符不是值域，这时将map里添加
                        putKeyValueToMap(temp, isKey, key, map);
                        temp.setLength(0);
                        isKey = true;
                    } else {
                        temp.append(curChar);
                    }
                }

            }
            putKeyValueToMap(temp, isKey, key, map);
        }
        return map;
    }

    private static void putKeyValueToMap(StringBuilder temp, boolean isKey, String key, Map<String, Object> map) {
        if (isKey) {
            key = temp.toString();
            if (key.length() == 0) {
                throw new BusinessException(HttpErrorCodeEnum.HTTP_CONTENT_FORMAT_WRONG);
            }
            map.put(key, "");
        } else {
            if (key.length() == 0) {
                throw new BusinessException(HttpErrorCodeEnum.HTTP_CONTENT_FORMAT_WRONG);
            }
            map.put(key, temp.toString());
        }
    }

}
