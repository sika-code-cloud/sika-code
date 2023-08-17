package com.sika.code.core.base.util;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author daiqi
 * @ClassName : DqJSONUtil
 * @Description : Json工具类--封装了OBJECT_MAPPER进行转换
 * @date 2017年12月5日 下午2:05:04
 */
public class JSONUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) // 忽略反序列化对象不存在的属性
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    ; // 属性为空不序列化

    /**
     * 将Object对象转换为json字符串，若不能转换将String.valueOf(obj)
     */
    public static String toJSONString(Object obj) {
        if (ObjectUtil.isNull(obj)) {
            return null;
        }
        if (obj instanceof String) {
            return obj.toString();
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return String.valueOf(obj);
        }
    }

    /**
     * 将obj转化为class对应的泛型对象
     *
     * @param obj
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T parseObject(Object obj, Class<T> clazz) {
        if (obj == null) {
            return null;
        }
        try {
            String jsonStr;
            if (obj instanceof String) {
                jsonStr = obj.toString();
            } else {
                jsonStr = toJSONString(obj);
            }
            return OBJECT_MAPPER.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            return (T) obj;
        }
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    private static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 将json数格式的字符串或者list转换为Class对应泛型对象的集合
     *
     * @param listOrJsonStr
     * @param clazz
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> parseArray(Object listOrJsonStr, Class<T> clazz) {
        if (listOrJsonStr == null || clazz == null) {
            return null;
        }
        String arrStr = listOrJsonStr.toString();
        if (StrUtil.isEmpty(arrStr)) {
            return null;
        }
        try {
            if (listOrJsonStr instanceof List || listOrJsonStr instanceof Set) {
                arrStr = toJSONString(listOrJsonStr);
            }
            return OBJECT_MAPPER.readValue(arrStr, getCollectionType(OBJECT_MAPPER, List.class, clazz));
        } catch (Exception e) {
            e.printStackTrace();
            List<T> retList = new ArrayList<>();
            retList.add((T) arrStr);
            return retList;
        }
    }

    /**
     * <p>
     * 将fromList中的数据转换为泛型list数据
     * </p>
     * <p>
     * <pre>
     *
     * </pre>
     *
     * @param fromList : List : 任意泛型Collection
     * @param clazz    : 泛型class
     * @return 泛型list
     * @author daiqi
     * @date 2017年12月11日 下午4:46:07
     */
    public static <T> List<T> fromListToTList(Collection<?> fromList, Class<T> clazz) {
        List<T> retList = new ArrayList<>();
        if (CollectionUtil.isEmpty(fromList)) {
            return retList;
        }
        for (Object obj : fromList) {
            retList.add(parseObject(obj, clazz));
        }
        return retList;
    }
}
