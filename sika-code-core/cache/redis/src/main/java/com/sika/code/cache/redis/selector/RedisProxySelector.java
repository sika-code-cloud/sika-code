package com.sika.code.cache.redis.selector;

import com.sika.code.basic.util.Assert;
import com.sika.code.cache.redis.proxy.RedisProxy;
import com.google.common.collect.Maps;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * redis代理类选择器
 *
 * @author daiqi
 * @create 2019-04-12 15:53
 */

public class RedisProxySelector {
    private static final Map<Class, RedisProxy> REDIS_PROXY_HASH_MAP = Maps.newHashMap();

    /**
     * <p>
     * 根据代理类的Class获取代理对象
     * </p>
     *
     * @param proxyClass
     * @return RedisProxy
     * @author daiqi
     * @date 2019/4/12 16:28
     */
    public static <T extends RedisProxy> RedisProxy getProxy(Class<T> proxyClass) {
        return REDIS_PROXY_HASH_MAP.get(proxyClass);
    }

    @PostConstruct
    protected final void init() {
        doInit();
    }

    protected void doInit() {
    }

    /**
     * 将代理类放入map中
     */
    protected final <T extends RedisProxy> void putProxy(T redisProxy) {
        Assert.verifyObjNull(redisProxy, "redisProxy");
        REDIS_PROXY_HASH_MAP.put(redisProxy.getClass(), redisProxy);
    }
}
