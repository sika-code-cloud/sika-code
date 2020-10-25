package com.sika.code.retryer.factory;

import cn.hutool.core.lang.SimpleCache;
import com.github.rholder.retry.RetryListener;
import com.sika.code.common.spring.SpringUtil;
import com.sika.code.common.util.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * 重试监听器工厂类
 *
 * @author daiqi
 * @create 2019-12-20 2:36
 */
@Slf4j
public class RetryListenerFactory {
    /**
     * 使用简单的缓存，自动维护缓存机制
     */
    private static SimpleCache<Class<? extends RetryListener>, RetryListener> RETRY_LISTENER_CACHE = new SimpleCache<>();

    /**
     * <p>
     * 根据监听器的class获取监听器对象
     * </p>
     *
     * @param tClass
     * @return T
     * @author daiqi
     * @date 2019/12/20 2:46
     */
    public static RetryListener getRetryListener(Class<? extends RetryListener> tClass) {
        if (tClass == null) {
            return null;
        }
        // 从缓存取
        RetryListener retryListener = get(tClass);
        if (retryListener != null) {
            return retryListener;
        }
        // 从spring容器取
        try {
            retryListener = SpringUtil.getBean(tClass);
        } catch (NoSuchBeanDefinitionException e) {
            // 不做处理
        }
        if (retryListener != null) {
            return put(tClass, retryListener);
        }
        // 反射创建对象
        return put(tClass, ReflectionUtil.newInstanceIfPossible(tClass));
    }

    public static RetryListener get(Class<? extends RetryListener> tClass) {
        return RETRY_LISTENER_CACHE.get(tClass);
    }

    public static RetryListener put(Class<? extends RetryListener> tClass, RetryListener retryListener) {
        return RETRY_LISTENER_CACHE.put(tClass, retryListener);
    }
}
