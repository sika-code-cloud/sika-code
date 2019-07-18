package com.sika.code.basic.pojo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.sika.code.common.spring.SpringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 基础领域类
 *
 * @author daiqi
 * @create 2019-06-24 22:06
 */
public interface BaseDomain {

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
    default Object getBean(String name) {
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
        return SpringUtil.getBean(tClass);
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
    static Object getBeanObj(String name) {
        return SpringUtil.getBean(name);
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
        return SpringUtil.getBean(name, tClass);
    }
}
