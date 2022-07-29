package com.sika.code.core.base.util;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author daiqi
 * @ClassName : DqJSONUtil
 * @Description : Json工具类--封装了fastjson进行转换
 * @date 2017年12月5日 下午2:05:04
 */
public class JSONUtil {

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
            return JSONObject.toJSONString(obj);
        } catch (JSONException | UnsupportedOperationException | IllegalStateException e) {
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
            if (obj instanceof String) {
                obj = JSONObject.parse(obj.toString());
            }
            String jsonStr = toJSONString(obj);
            return JSONObject.parseObject(jsonStr, clazz);
        } catch (JSONException | UnsupportedOperationException | IllegalStateException e) {
            return (T) obj;
        }
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
            if (listOrJsonStr instanceof List) {
                arrStr = JSONArray.toJSONString(listOrJsonStr);
            }
            return JSONArray.parseArray(arrStr, clazz);
        } catch (Exception e) {
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
     * @param clazz      : 泛型class
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
            retList.add(JSONUtil.parseObject(obj, clazz));
        }
        return retList;
    }
}
