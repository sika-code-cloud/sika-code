package com.sika.code.common.http.util;

import cn.hutool.http.HttpUtil;
import com.sika.code.basic.util.Assert;
import com.sika.code.common.json.util.JSONUtil;
import com.sika.code.common.string.util.StringUtil;
import com.sika.code.common.util.MapUtil;
import com.google.common.collect.Maps;
import com.sika.code.common.string.constant.StringConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author daiqi
 * @ClassName : RequestUtil
 * @Description : http工具类
 * @date 2017年12月21日 上午11:49:37
 */
public class RequestUtil extends HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(RequestUtil.class);

    /**
     * <p>
     * 将requestParameter转化为泛型class对应的对象
     * </p>
     *
     * @param request : HttpServletRequest
     * @param tClass  : Class<T> : 泛型class
     * @return T
     * @author daiqi
     * @date 2018/6/27 16:51
     */
    public static <T> T changeRequestParameterToTObj(ServletRequest request, Class<T> tClass) {
        Map<String, String> paramters = changeRequestParameterToMap(request);
        return JSONUtil.parseObject(paramters, tClass);
    }

    /**
     * <p>
     * 将requestParameter转化为map对象
     * </p>
     *
     * @param request : HttpServletRequest
     * @return java.manager.Map<java.lang.String,java.lang.String>
     * @author daiqi
     * @date 2018/6/27 16:46
     */
    public static Map<String, String> changeRequestParameterToMap(ServletRequest request) {
        Map<String, String> parameters = MapUtil.newHashMap();
        Enumeration em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String value = request.getParameter(name);
            parameters.put(name, value);
        }
        return parameters;
    }

    /**
     * <p>
     * 从request的Attribute域中获取泛型class对应的对象
     * </p>
     *
     * @param request
     * @param name
     * @param tClass
     * @return T
     * @author daiqi
     * @date 2018/6/27 19:48
     */
    public static <T> T getTObjFromAttribute(ServletRequest request, String name, Class<T> tClass) {
        Object attributeValue = request.getAttribute(name);
        return JSONUtil.parseObject(attributeValue, tClass);
    }

    /**
     * <p>
     * 使用json格式输出
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     * </pre>
     *
     * @param request
     * @param response
     * @param obj
     * @return void
     * @author daiqi
     * @date 2018/7/28 9:46
     */
    public static void printForJson(ServletRequest request, ServletResponse response, Object obj) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter wirte = null;
        String jsonStr = JSONUtil.toJSONString(obj);
        try {
            wirte = response.getWriter();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            wirte.print(jsonStr);
            wirte.flush();
            wirte.close();
        }

    }

    /**
     * <p>
     * 构建请求url
     * </p>
     *
     * @param requestUrl    : String : 请求的原始url
     * @param requestParams : Map<String, Object> : 请求参数map
     * @return java.lang.String : 拼接好的请求url
     * @author daiqi
     * @date 2018/7/31 14:42
     */
    public static String buildRequestUrl(final String requestUrl, final Map<String, Object> requestParams) {
        if (StringUtil.isEmpty(requestUrl) || MapUtil.isEmpty(requestParams)) {
            return requestUrl;
        }
        StringBuilder urlBuilder = StringUtil.newStringBuilder();
        urlBuilder.append(requestUrl);
        if (!StringUtil.containsAny(requestUrl, StringConstant.Symbol.QUESTION_MARK)) {
            urlBuilder.append(StringConstant.Symbol.QUESTION_MARK);
        }
        boolean needSingleAnd = false;
        for (String name : requestParams.keySet()) {
            Object value = requestParams.get(name);
            if (needSingleAnd) {
                urlBuilder.append(StringConstant.Symbol.SINGLE_AND);
            }
            urlBuilder.append(name)
                    .append(StringConstant.Symbol.EQUAL)
                    .append(value);
            if (!needSingleAnd) {
                needSingleAnd = true;
            }

        }
        return urlBuilder.toString();
    }

    /**
     * <p>
     * 从request中获取请求参数信息
     * </p>
     *
     * @param request
     * @return java.manager.Map<java.lang.String,java.lang.Object>
     * @author daiqi
     * @date 2018/8/1 15:57
     */
    public static Map<String, Object> getRequestParams(ServletRequest request) {
        Map<String, Object> paramsMap = Maps.newLinkedHashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    paramsMap.put(paramName, paramValue);
                }
            }
        }
        return paramsMap;
    }

    /**
     * <p>
     * 根据名称获取请求头的数据
     * </p>
     *
     * @param request
     * @param name
     * @return java.lang.String
     * @author daiqi
     * @date 2018/8/9 10:04
     */
    public static String getHeader(HttpServletRequest request, String name) {
        Assert.verifyStrEmpty(name, "name");
        return request.getHeader(name);
    }

    /**
     * 获取Ip地址
     * @param request
     * @return
     */
    public static String getIpAdrress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtil.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtil.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtil.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtil.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtil.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }
}
