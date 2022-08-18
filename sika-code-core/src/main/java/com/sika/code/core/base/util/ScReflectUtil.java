package com.sika.code.core.base.util;

import cn.hutool.core.util.ReflectUtil;
import org.springframework.core.GenericTypeResolver;
import org.springframework.util.ClassUtils;

/**
 * <pre>
 *  ak的反射工具类
 * </pre>
 *
 * @author daiqi
 * @version 1.0
 * @since 2022/5/31 11:27
 */
public class ScReflectUtil extends ReflectUtil {

    /**
     * <p>
     * 反射对象获取泛型
     * </p>
     *
     * @param clazz      对象
     * @param genericIfc 所属泛型父类
     * @param index      泛型所在位置
     * @return Class
     */
    public static Class<?> getSuperClassGenericType(final Class<?> clazz, final Class<?> genericIfc, final int index) {
        //update by noear @2021-09-03
        Class<?>[] typeArguments = resolveTypeArguments(ClassUtils.getUserClass(clazz), genericIfc);
        return null == typeArguments ? null : typeArguments[index];
    }

    /**
     * 获取泛型工具助手
     */
    public static Class<?>[] resolveTypeArguments(final Class<?> clazz, final Class<?> genericIfc) {
            // 直接使用 spring 静态方法，减少对象创建
        return GenericTypeResolver.resolveTypeArguments(clazz, genericIfc);
    }


}
