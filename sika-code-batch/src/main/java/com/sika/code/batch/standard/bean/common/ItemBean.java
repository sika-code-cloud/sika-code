package com.sika.code.batch.standard.bean.common;

import cn.hutool.core.bean.BeanUtil;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/12 11:55
 */
@Data
public class ItemBean<T> {
    private Class<T> beanClass;
    private Map<String, Object> bean;
    @Setter(value = AccessLevel.NONE)
    private T beanObj;

    public T buildBeanObj() {
        if (beanObj == null) {
            this.beanObj = BeanUtil.toBean(bean, beanClass);
        }
        return this.beanObj;
    }
}
