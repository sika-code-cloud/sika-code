package com.easy.cloud.core.common.http.utils;

import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;

import javax.servlet.ServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author daiqi
 * @ClassName : EcRequestUtils
 * @Description : http工具类
 * @date 2017年12月21日 上午11:49:37
 */
public class EcRequestUtils {
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
        return EcJSONUtils.parseObject(paramters, tClass);
    }

    /**
     * <p>
     * 将requestParameter转化为map对象
     * </p>
     *
     * @param request : HttpServletRequest
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @author daiqi
     * @date 2018/6/27 16:46
     */
    public static Map<String, String> changeRequestParameterToMap(ServletRequest request) {
        Map<String, String> parameters = EcMapUtils.newHashMap();
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
        return EcJSONUtils.parseObject(attributeValue, tClass);
    }
}
