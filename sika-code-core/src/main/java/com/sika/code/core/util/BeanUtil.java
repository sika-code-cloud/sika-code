package com.sika.code.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.sika.code.core.base.pojo.domain.factory.MetaSpringUtil;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * 对象转化者工具类
 */
public class BeanUtil extends cn.hutool.core.bean.BeanUtil {

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
        return CglibUtil.copy(source, targetClass);
    }

    public static <T> T toBean(Map source, Class<T> beanClass) {
        if (source == null) {
            return null;
        }
        return CglibUtil.toBean(source, beanClass);
    }

    public static Map toMap(Object source) {
        if (source == null) {
            return null;
        }
        return CglibUtil.toMap(source);
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


    public static <T> T getBean(String tClassName) {
        return MetaSpringUtil.getBean(tClassName);
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
}
