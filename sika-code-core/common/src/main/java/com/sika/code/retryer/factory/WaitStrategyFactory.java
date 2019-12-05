package com.sika.code.retryer.factory;

import com.github.rholder.retry.WaitStrategies;
import com.github.rholder.retry.WaitStrategy;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.retryer.anotation.RetryerAnnotation;
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
     * <p>
     * 创建默认的WaitStrategy实例
     * </p>
     *
     * @return com.github.rholder.retry.WaitStrategy
     * @author sikadai
     * @date 2019/12/6 0:02
     */
    public static WaitStrategy newInstance() {
        return newInstance(null);
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
