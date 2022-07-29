package com.sika.code.core.base.pojo.domain.factory;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 基础领域接工厂接口
 *
 * @author daiqi
 * @create 2021-10-21 0:41
 */
public interface BaseFactory {

    /**
     * <p>
     * 通过class获取Bean.
     * </p>
     *
     * @param tClass : bean的class
     * @return T
     * @author daiqi
     * @date 2018/7/28 9:02
     */
    @JsonIgnore
    @JSONField(deserialize = false, serialize = false)
    default <T> T getBean(Class<T> tClass) {
        return getBeanObj(tClass);
    }

    /**
     * <p>
     * 通过class获取Bean.
     * </p>
     *
     * @param name : bean的名称
     * @return T
     * @author daiqi
     * @date 2018/7/28 9:02
     */
    @JsonIgnore
    @JSONField(deserialize = false, serialize = false)
    default <T> T getBean(String name) {
        return getBeanObj(name);
    }

    /**
     * <p>
     * 通过name,以及Clazz返回指定的Bean
     * </p>
     *
     * @param name   ： bean的名称
     * @param tClass : bean的class
     * @return T
     * @author daiqi
     * @date 2018/7/28 9:02
     */
    @JsonIgnore
    @JSONField(deserialize = false, serialize = false)
    default <T> T getBean(String name, Class<T> tClass) {
        return getBeanObj(name, tClass);
    }

    /**
     * <p>
     * 通过class获取Bean.
     * </p>
     *
     * @param tClass : bean的class
     * @return T
     * @author daiqi
     * @date 2018/7/28 9:02
     */
    static <T> T getBeanObj(Class<T> tClass) {
        return MetaSpringUtil.getBean(tClass);
    }

    /**
     * <p>
     * 通过class获取Bean.
     * </p>
     *
     * @param name : bean的名称
     * @return T
     * @author daiqi
     * @date 2018/7/28 9:02
     */
    static <T> T getBeanObj(String name) {
        return MetaSpringUtil.getBean(name);
    }

    /**
     * <p>
     * 通过name,以及Clazz返回指定的Bean
     * </p>
     *
     * @param name   ： bean的名称
     * @param tClass : bean的class
     * @return T
     * @author daiqi
     * @date 2018/7/28 9:02
     */
    static <T> T getBeanObj(String name, Class<T> tClass) {
        return MetaSpringUtil.getBean(name, tClass);
    }

    static <T> T newInstance(Class<T> tClass, Object... params) {
        return ReflectUtil.newInstance(tClass, params);
    }

    static <T> T newInstance(String className) {
        return ReflectUtil.newInstance(className);
    }
}
