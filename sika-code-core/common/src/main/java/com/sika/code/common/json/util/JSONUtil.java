package com.sika.code.common.json.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.json.config.JsonConfig;
import com.sika.code.common.json.filter.JsonPropertyFilter;
import com.sika.code.common.string.util.StringUtil;
import com.sika.code.common.util.CollectionUtil;

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

    static {
        new JsonPropertyFilter(JsonConfig.getJsonFilterDTOMap());
    }

    /**
     * 将Object对象转换为json字符串，若不能转换将String.valueOf(obj)
     */
    public static String toJSONString(Object obj) {
        if (BaseUtil.isNull(obj)) {
            return null;
        }
        if (obj instanceof String) {
            return obj.toString();
        }

        try {
            return JSONObject.toJSONString(obj);
        } catch (JSONException e) {
            return String.valueOf(obj);
        } catch (UnsupportedOperationException e) {
            return String.valueOf(obj);
        } catch (IllegalStateException e) {
            return String.valueOf(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
            return com.alibaba.fastjson.JSONObject.parseObject(jsonStr, clazz);
        } catch (JSONException e) {
            return (T) obj;
        } catch (UnsupportedOperationException e) {
            return (T) obj;
        } catch (IllegalStateException e) {
            return (T) obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        if (StringUtil.isEmpty(arrStr)) {
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
