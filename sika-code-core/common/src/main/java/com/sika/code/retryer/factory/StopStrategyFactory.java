package com.sika.code.retryer.factory;

import cn.hutool.core.lang.SimpleCache;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.StopStrategy;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.retryer.constant.StopStrategyEnum;
import com.sika.code.retryer.pojo.StopStrategyParam;

import java.util.concurrent.TimeUnit;

/**
 * 停止策略工厂类
 *
 * @author daiqi
 * @create 2019-12-05 22:51
 */
public class StopStrategyFactory {
    /**
     * 使用简单的缓存，自动维护缓存机制
     */
    private static SimpleCache<String, StopStrategy> STOP_STRATEGY_CACHE = new SimpleCache<>();

    /**
     * <p>
     * 获取默认停止策略，使用了缓存机制
     * </p>
     *
     * @param
     * @return com.github.rholder.retry.StopStrategy
     * @author daiqi
     * @date 2019/12/19 18:08
     */
    public static StopStrategy getStopStrategy() {
        return getStopStrategy(new StopStrategyParam().build());
    }

    public static StopStrategy get(String stopStrategyParamStr) {
        return STOP_STRATEGY_CACHE.get(stopStrategyParamStr);
    }

    public static StopStrategy put(String stopStrategyParamStr, StopStrategy stopStrategy) {
       return  STOP_STRATEGY_CACHE.put(stopStrategyParamStr, stopStrategy);
    }

    /**
     * <p>
     * 获取停止策略，使用了缓存机制
     * </p>
     *
     * @param stopStrategyParam : 停止策略的参数
     * @return com.github.rholder.retry.StopStrategy
     * @author daiqi
     * @date 2019/12/19 18:03
     */
    public static StopStrategy getStopStrategy(StopStrategyParam stopStrategyParam) {
        String stopStrategyParamStr = stopStrategyParam.toString();
        StopStrategy stopStrategyFromCache = get(stopStrategyParamStr);
        if (stopStrategyFromCache == null) {
            stopStrategyFromCache = newInstance(stopStrategyParam);
        }
        return put(stopStrategyParamStr, stopStrategyFromCache);
    }

    /**
     * <p>
     * 创建默认的StopStrategy实例
     * </p>
     *
     * @return com.github.rholder.retry.StopStrategy
     * @author sikadai
     * @date 2019/12/6 0:01
     */
    public static StopStrategy newInstance() {
        return newInstance(new StopStrategyParam().build());
    }

    /**
     * <p>
     * 根据参数构建StopStrategy实例
     * </p>
     *
     * @param stopStrategyParam
     * @return com.github.rholder.retry.StopStrategy
     * @author sikadai
     * @date 2019/12/6 0:01
     */
    public static StopStrategy newInstance(StopStrategyParam stopStrategyParam) {
        if (BaseUtil.isNull(stopStrategyParam)) {
            stopStrategyParam = new StopStrategyParam().build();
        }

        StopStrategyEnum stopStrategyEnum = stopStrategyParam.getStopStrategyEnum();
        TimeUnit timeUnit = stopStrategyParam.getTimeUnit();
        int attemptNumber = stopStrategyParam.getAttemptNumber();
        long maxTime = stopStrategyParam.getMaxTime();

        if (StopStrategyEnum.NEVER_STOP.equals(stopStrategyEnum)) {
            return StopStrategies.neverStop();
        }
        if (StopStrategyEnum.STOP_AFTER_ATTEMPT.equals(stopStrategyEnum)) {
            return StopStrategies.stopAfterAttempt(attemptNumber);
        }
        if (StopStrategyEnum.STOP_AFTER_DELAY.equals(stopStrategyEnum)) {
            return StopStrategies.stopAfterDelay(maxTime, timeUnit);
        }
        throw new UnsupportedOperationException("不支持的停止策略");
    }
}
