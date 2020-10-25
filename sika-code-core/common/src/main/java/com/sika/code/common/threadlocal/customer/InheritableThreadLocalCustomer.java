package com.sika.code.common.threadlocal.customer;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>
 * 自定义InheritableThreadLocal类
 * 通过在InheritableThreadLocal中存储map对象来支持使用一个InheritableThreadLocal对象支持多个值的存储
 * </p>
 *
 * @author daiqi
 * @date 2019/6/23 8:58
 */
public class InheritableThreadLocalCustomer extends InheritableThreadLocal<Map<String, Object>> {
    @Override
    protected Map<String, Object> initialValue() {
        return Maps.newHashMap();
    }
}
