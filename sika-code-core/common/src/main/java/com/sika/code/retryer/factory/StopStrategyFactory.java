package com.sika.code.retryer.factory;

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
     * <p>
     * 创建默认的StopStrategy实例
     * </p>
     *
     * @return com.github.rholder.retry.StopStrategy
     * @author sikadai
     * @date 2019/12/6 0:01
     */
    public static StopStrategy newInstance() {
        return newInstance(null);
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
