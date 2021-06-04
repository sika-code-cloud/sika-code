package com.sika.code.retryer.factory;

import cn.hutool.core.lang.SimpleCache;
import com.github.rholder.retry.WaitStrategies;
import com.github.rholder.retry.WaitStrategy;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.retryer.constant.WaitStrategyEnum;
import com.sika.code.retryer.pojo.WaitStrategyParam;

import java.util.concurrent.TimeUnit;

/**
 * 等待策略枚举
 *
 * @author daiqi
 * @create 2019-12-05 23:07
 */
public class WaitStrategyFactory {
    /**
     * 使用简单的缓存，自动维护缓存机制
     */
    private static SimpleCache<String, WaitStrategy> WAIT_STRATEGY_CACHE = new SimpleCache<>();

    /**
     * <p>
     * 获取默认等待策略，使用了缓存机制
     * </p>
     *
     * @param
     * @return com.github.rholder.retry.WaitStrategy
     * @author daiqi
     * @date 2019/12/19 18:09
     */
    public static WaitStrategy getWaitStrategy() {
        return getWaitStrategy(new WaitStrategyParam().build());
    }

    /**
     * <p>
     * 获取等待策略，使用了缓存机制
     * </p>
     *
     * @param waitStrategyParam : 等待策略参数
     * @return com.github.rholder.retry.WaitStrategy
     * @author daiqi
     * @date 2019/12/19 18:00
     */
    public static WaitStrategy getWaitStrategy(WaitStrategyParam waitStrategyParam) {
        String waitStrategyParamStr = waitStrategyParam.toString();
        WaitStrategy waitStrategyFromCache = get(waitStrategyParamStr);
        if (waitStrategyFromCache == null) {
            waitStrategyFromCache = newInstance(waitStrategyParam);
        }
        return put(waitStrategyParamStr, waitStrategyFromCache);
    }

    public static WaitStrategy get(String waitStrategyParamStr) {
        return WAIT_STRATEGY_CACHE.get(waitStrategyParamStr);
    }

    public static WaitStrategy put(String waitStrategyParamStr, WaitStrategy waitStrategy) {
        return WAIT_STRATEGY_CACHE.put(waitStrategyParamStr, waitStrategy);
    }


    /**
     * <p>
     * 创建默认的WaitStrategy实例
     * </p>
     *
     * @return com.github.rholder.retry.WaitStrategy
     * @author sikadai
     * @date 2019/12/6 0:02
     */
    public static WaitStrategy newInstance() {
        return newInstance(new WaitStrategyParam().build());
    }

    /**
     * <p>
     * 根据参数创建WaitStrategy实例
     * </p>
     *
     * @param waitStrategyParam
     * @return com.github.rholder.retry.WaitStrategy
     * @author sikadai
     * @date 2019/12/6 0:02
     */
    public static WaitStrategy newInstance(WaitStrategyParam waitStrategyParam) {
        if (BaseUtil.isNull(waitStrategyParam)) {
            waitStrategyParam = new WaitStrategyParam().build();
        }

        WaitStrategyEnum waitStrategyEnum = waitStrategyParam.getWaitStrategyEnum();
        TimeUnit timeUnit = waitStrategyParam.getTimeUnit();
        Long initTime = waitStrategyParam.getInitTime();
        Long increment = waitStrategyParam.getIncrement();
        Long maxTime = waitStrategyParam.getMaxTime();

        if (WaitStrategyEnum.FIXED.equals(waitStrategyEnum)) {
            return WaitStrategies.fixedWait(initTime, timeUnit);
        }
        if (WaitStrategyEnum.RANDOM.equals(waitStrategyEnum)) {
            return WaitStrategies.randomWait(initTime, timeUnit, maxTime, timeUnit);
        }
        if (WaitStrategyEnum.INCREMENTING.equals(waitStrategyEnum)) {
            return WaitStrategies.incrementingWait(initTime, timeUnit, increment, timeUnit);
        }
        if (WaitStrategyEnum.EXPONENTIAL.equals(waitStrategyEnum)) {
            return WaitStrategies.exponentialWait(initTime, maxTime, timeUnit);
        }
        if (WaitStrategyEnum.FIBONACCI.equals(waitStrategyEnum)) {
            return WaitStrategies.fibonacciWait(initTime, maxTime, timeUnit);
        }
        throw new UnsupportedOperationException("不支持的等待策略");
    }
}
