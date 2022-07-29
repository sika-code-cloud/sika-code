package com.sika.code.core.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 风控枚举工具类
 *
 * @author daiqi
 * @create 2019-06-06 12:01
 */
@Slf4j
public class EnumUtil extends cn.hutool.core.util.EnumUtil {

    /**
     * 缓存枚举方法对应的枚举Class+方法+值和合枚举之间的关系
     * key：【ClassName + methodName + value】
     * value : enumObj
     */
//    private static final SimpleCache<String, Object> ENUM_VALUE_CACHE = new SimpleCache<>();
    private static final Cache<String, Object> ENUM_VALUE_CACHE = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.DAYS)
            .initialCapacity(100)
            .maximumSize(500)
            .build();

    /**
     * <p>
     * 根据code判断对应的Class的枚举是否不存在
     * </p>
     *
     * @param enumClass  : 枚举类的class
     * @param methodName : 枚举中属性对应的方法名称
     * @param targetData : 需要查询的数据
     * @return boolean 不存在返回true 否则返回false
     * @author daiqi
     * @date 2020/3/4 2:18
     */
    public static <T> boolean unExist(Class<T> enumClass, String methodName, Object targetData) {
        return !exist(enumClass, methodName, targetData);
    }

    /**
     * <p>
     * 判断传入的data是否存在指定的枚举类中 存在返回true
     * </p>
     *
     * @param enumClass  : 枚举类的class
     * @param methodName : 枚举中属性对应的方法名称
     * @param targetData : 需要查询的数据
     * @return boolean
     * @author daiqi
     * @date 2019/6/30 9:43
     */
    public static <T> boolean exist(Class<T> enumClass, String methodName, Object targetData) {
        Object object = find(enumClass, methodName, targetData);
        if (object != null) {
            return true;
        }
        return false;
    }

    /**
     * <p>
     * 从指定枚举类class中查找与data相匹配的枚举
     * </p>
     *
     * @param enumClass  : 枚举类的class
     * @param methodName : 枚举中属性对应的方法名称
     * @param targetData : 需要查询的数据
     * @return T
     * @author daiqi
     * @date 2019/6/30 9:33
     */
    public static <T> T find(Class<T> enumClass, String methodName, Object targetData) {
        if (targetData == null || methodName == null || enumClass == null) {
            return null;
        }
        if (!isEnum(enumClass)) {
            log.error("非枚举类型{}", enumClass);
        }
        T[] enumConstants = enumClass.getEnumConstants();
        if (ArrayUtil.isEmpty(enumConstants)) {
            return null;
        }
        // 优先从缓存中获取枚举对象
        T enumObj = getEnumObjFromCache(enumClass, methodName, targetData);
        if (enumObj != null) {
            return enumObj;
        }
        return getEnumObj(enumClass, methodName, targetData, enumConstants);
    }

    public static <T> T findByName(Class<T> enumClass, String typeName) {
        T[] enumConstants = enumClass.getEnumConstants();
        for (T enumObj : enumConstants) {
            if (enumObj.toString().equalsIgnoreCase(typeName)) {
                return enumObj;
            }
        }
        return null;
    }

    /**
     * 根据目标枚举参数获取枚举列表
     *
     * @param enumClass
     * @param methodName
     * @param targetData
     * @param <T>
     * @return
     */
    public static <T> List<T> findEnums(Class<T> enumClass, String methodName, Object targetData) {
        List<T> enums = new ArrayList<>();
        T[] enumConstants = enumClass.getEnumConstants();
        try {
            Method enumMethod = ReflectUtil.getMethod(enumClass, methodName);
            for (T enumObj : enumConstants) {
                if (Objects.equals(targetData, enumObj)) {
                    enums.add(enumObj);
                    continue;
                }
                Object enumMethodRetValue = ReflectUtil.invoke(enumObj, enumMethod);
                if (Objects.equals(enumMethodRetValue, targetData)) {
                    enums.add(enumObj);
                    continue;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return enums;
    }

    /**
     * 码值是否包含在该枚举中
     *
     * @param enumClass  枚举
     * @param methodName 获取的方法
     * @param target     比较的参数
     * @param inEnums    枚举值
     * @param <T>        枚举类
     * @return 包含=true
     */
    public static <T> boolean in(Class<T> enumClass, String methodName, Object target, T... inEnums) {
        if (inEnums == null || inEnums.length == 0 || target == null) {
            return false;
        }
        Method enumMethod = ReflectUtil.getMethod(enumClass, methodName);
        for (T inEnum : inEnums) {
            Object enumMethodRetValue = ReflectUtil.invoke(inEnum, enumMethod);
            if (Objects.equals(enumMethodRetValue, target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 码值是否不包含在该枚举中
     *
     * @param enumClass  枚举
     * @param methodName 获取的方法
     * @param target     比较的参数
     * @param notInEnums 枚举值
     * @param <T>        枚举类
     * @return 不包含=true
     */
    public static <T> boolean notIn(Class<T> enumClass, String methodName, Object target, T... notInEnums) {
        if (notInEnums == null || notInEnums.length == 0 || target == null) {
            return true;
        }
        Method enumMethod = ReflectUtil.getMethod(enumClass, methodName);
        for (T notInEnum : notInEnums) {
            Object enumMethodRetValue = ReflectUtil.invoke(notInEnum, enumMethod);
            if (Objects.equals(enumMethodRetValue, target)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * 获取枚举对象
     * </p>
     *
     * @param enumClass
     * @param methodName
     * @param targetData
     * @param enumConstants
     * @return T
     * @author daiqi
     * @date 2020/3/4 10:23
     */
    private static <T> T getEnumObj(Class<T> enumClass, String methodName, Object targetData, T[] enumConstants) {
        T enumObjForRet = null;
        try {
            Method enumMethod = ReflectUtil.getMethod(enumClass, methodName);
            for (T enumObj : enumConstants) {
                // 若data为当前枚举类中的枚举对象
                if (Objects.equals(targetData, enumObj)) {
                    enumObjForRet = enumObj;
                    break;
                }
                // 执行方法获取结果
                Object enumMethodRetValue = ReflectUtil.invoke(enumObj, enumMethod);
                if (Objects.equals(enumMethodRetValue, targetData)) {
                    enumObjForRet = enumObj;
                    break;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
        // 非空 -- 缓存
        if (enumObjForRet != null) {
            return putEnumObjForCache(enumClass, methodName, targetData, enumObjForRet);
        }
        return enumObjForRet;
    }

    /**
     * 缓存枚举对象
     */
    private static <T> T putEnumObjForCache(Class<T> enumClass, String methodName, Object targetData, T enumObjForRet) {
        ENUM_VALUE_CACHE.put(buildEnumCacheKey(enumClass, methodName, targetData), enumObjForRet);
        return enumObjForRet;
    }

    /**
     * 从缓存中获取枚举对象
     */
    private static <T> T getEnumObjFromCache(Class<T> enumClass, String methodName, Object targetData) {
        return (T) ENUM_VALUE_CACHE.asMap().get(buildEnumCacheKey(enumClass, methodName, targetData));
    }

    /**
     * 构建枚举缓存Key
     */
    private static <T> String buildEnumCacheKey(Class<T> enumClass, String methodName, Object targetData) {
        String dashed = StrUtil.DASHED;
        return enumClass.getName().concat(dashed).concat(methodName).concat(dashed).concat(targetData.toString());
    }
}
