package com.sika.code.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.sika.code.core.base.pojo.domain.factory.MetaSpringUtil;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;
import java.util.Map;

/**
 * 对象转化者工具类
 */
public class BeanUtil extends cn.hutool.core.bean.BeanUtil {
    private static final MapperFacade mapperFacade;
    private static final MapperFactory mapperFactory;

    static {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public static <INSTANCE> INSTANCE newInstance(String className) {
        Assert.notNull(className, "className不能为空");
        return ReflectUtil.newInstance(className);
    }

    public static <INSTANCE> INSTANCE newInstance(Class<INSTANCE> instanceClass, Object... params) {
        Assert.notNull(instanceClass, "instanceClass不能为空");
        return ReflectUtil.newInstance(instanceClass, params);
    }

    public static <INSTANCE> INSTANCE newInstance(Class<INSTANCE> instanceClass) {
        Assert.notNull(instanceClass, "instanceClass不能为空");
        return ReflectUtil.newInstance(instanceClass);
    }

    public static <TARGET> TARGET toBean(Object source, Class<TARGET> targetClass) {
        if (source == null) {
            return null;
        }
        if (source instanceof Map) {
            return cn.hutool.core.bean.BeanUtil.toBean(source, targetClass);
        }
        if (Map.class.isAssignableFrom(targetClass)) {
            return (TARGET) cn.hutool.core.bean.BeanUtil.beanToMap(source);
        }
        return mapperFacade.map(source, targetClass);
    }

    public static <TARGET> List<TARGET> toBeans(List<?> sources, Class<TARGET> targetClass) {
        Assert.notNull(targetClass, "targetClass不能为空");
        if (CollUtil.isEmpty(sources)) {
            return Lists.newArrayList();
        }
        List<TARGET> targets = Lists.newArrayList();
        for (Object source : sources) {
            targets.add(toBean(source, targetClass));
        }
        return targets;
    }

    public static <T> T getBean(Class<T> tClass) {
        return MetaSpringUtil.getBean(tClass);
    }


    public static <T> T getBean(String beanName) {
        T instance;
        try {
            Class<T> instanceClass = (Class<T>) Class.forName(beanName);
            instance = getBean(instanceClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("从容器中获取执行器对象失败", e);
        }
        return instance;
    }

    /**
     * 校验节点类是否存在 - 此处若在多系统维度下需要调用目标机器的判定方法
     *
     * @param clazz : 全量限定Class名称
     */
    public static void verifyClassExist(String clazz) {
        // 校验节点类是否存在
        try {
            Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(StrUtil.format("类名【{}】对应的类不存在，请立即核实", clazz));
        }
    }

    public static <T> T getTClass(String className) {
        try {
            return (T) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(StrUtil.format("类名【{}】对应的类不存在，请立即核实", className));
        }
    }
}
