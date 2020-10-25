package com.sika.code.common.spring;

import com.sika.code.basic.constant.BeanMappingEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 获取springbean的工具类
 * </p>
 *
 * @author daiqi
 * @date 2018/7/28 9:00
 * @return
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * <p>
     * 通过name获取 Bean.
     * </p>
     *
     * @param name : bean的名称
     * @return java.lang.Object
     * @author daiqi
     * @date 2018/7/28 9:01
     */
    public static Object getBean(String name) {
        if (getApplicationContext() == null) {
            return null;
        }
        return getApplicationContext().getBean(name);
    }


    /**
     * <p>
     * 通过class获取Bean.
     * </p>
     *
     * @param clazz : bean的class
     * @return T
     * @author daiqi
     * @date 2018/7/28 9:02
     */
    public static <T> T getBean(Class<T> clazz) {
        if (getApplicationContext() == null) {
            return null;
        }
        return getApplicationContext().getBean(clazz);
    }

    /**
     * <p>
     * 通过name,以及Clazz返回指定的Bean
     * </p>
     *
     * @param name ： bean的名称
     * @param clazz : bean的class
     * @return T
     * @author daiqi
     * @date 2018/7/28 9:02
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        if (getApplicationContext() == null) {
            return null;
        }
        return getApplicationContext().getBean(name, clazz);
    }

    public static <T> T getBean(BeanMappingEnum<T> beanMappingEnum) {
        return getBean(beanMappingEnum.getBeanName(), beanMappingEnum.getBeanClass());
    }

}