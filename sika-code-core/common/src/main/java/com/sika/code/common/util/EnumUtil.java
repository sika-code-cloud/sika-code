package com.sika.code.common.util;

import cn.hutool.core.util.ArrayUtil;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.exception.BusinessException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 风控枚举工具类
 *
 * @author daiqi
 * @create 2019-06-06 12:01
 */
@Slf4j
public class EnumUtil extends cn.hutool.core.util.EnumUtil {

    /**
     * <p>
     * 判断传入的data是否存在指定的枚举类中 存在返回true
     * </p>
     *
     * @param data       : 需要查询的数据
     * @param methodName : 枚举属性对应的方法名称
     * @param enumClass  : 枚举类的class
     * @return boolean
     * @author daiqi
     * @date 2019/6/30 9:43
     */
    public static <T> boolean exist(Object data, String methodName, Class<T> enumClass) {
        Object object = find(data, methodName, enumClass);
        if (BaseUtil.isAnyNull(object)) {
            return true;
        }
        return false;
    }

    /**
     * <p>
     * 从指定枚举类class中查找与data相匹配的枚举
     * </p>
     *
     * @param data       : 需要查询的数据
     * @param methodName : 枚举中属性对应的方法名称
     * @param enumClass  : 枚举类的class
     * @return T
     * @author daiqi
     * @date 2019/6/30 9:33
     */
    public static <T> T find(Object data, String methodName, Class<T> enumClass) {
        if (BaseUtil.isAllNotNull(data, methodName, enumClass)) {
            return null;
        }
        if (!enumClass.isEnum()) {
            throw new BusinessException(BaseErrorCodeEnum.DATA_ERROR, enumClass.getName() + "不是枚举类");
        }
        T[] objects = enumClass.getEnumConstants();
        if (ArrayUtil.isEmpty(objects)) {
            return null;
        }
        try {
            Method getType = enumClass.getMethod(methodName);
            for (T enumObj : objects) {
                // 若data为当前枚举类中的枚举对象直接返回
                if (BaseUtil.equals(data, enumObj)) {
                    return enumObj;
                } else if (BaseUtil.equals(getType.invoke(enumObj), data)) {
                    return enumObj;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return null;
    }

    /**
     * <p>
     * 根据枚举类型的class转化为列表
     * </p>
     *
     * @param typeEnumClass
     * @return List<Map>
     * @author daiqi
     * @date 2019/5/23 17:26
     */
    public static <T extends Enum> List<Map<String, Object>> enumToList(Class<T> typeEnumClass) {
        List<Map<String, Object>> retList = Lists.newArrayList();
        T[] objects = typeEnumClass.getEnumConstants();
        if (ArrayUtil.isEmpty(objects)) {
            return retList;
        }
        try {
            Method[] methods = typeEnumClass.getDeclaredMethods();
            // 过滤静态方法和非get方法
            String methodPrefixGet = "get";
            List<Method> methodsForFilter = Arrays.stream(methods)
                    .filter(method -> !Modifier.isStatic(method.getModifiers()))
                    .filter(method -> method.getName().startsWith(methodPrefixGet))
                    .collect(Collectors.toList());

            for (T enumObj : objects) {
                Map<String, Object> paramsMap = Maps.newLinkedHashMap();
                for (Method method : methodsForFilter) {
                    // 移除前缀并且将首字母小写
                    String key = StringUtils.lowerCase(StringUtils.substring(method.getName(), methodPrefixGet.length()));
                    paramsMap.put(key, method.invoke(enumObj));
                }
                retList.add(paramsMap);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return retList;
    }
}
