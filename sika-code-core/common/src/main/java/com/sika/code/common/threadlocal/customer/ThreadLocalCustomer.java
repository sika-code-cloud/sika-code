package com.sika.code.common.threadlocal.customer;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>
 * 自定义的ThreadLocal
 * 通过在threadLocal中存储map对象来支持使用一个ThreadLocal对象支持多个值的存储
 * </p>
 *
 * @author daiqi
 * @date 2019/6/23 8:58
 */
public class ThreadLocalCustomer extends ThreadLocal<Map<String, Object>> {
    @Override
    protected Map<String, Object> initialValue() {
        return Maps.newHashMap();
    }
}
