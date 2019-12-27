package com.sika.code.retryer.pojo;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.retryer.constant.StopStrategyEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 停止策略的入参
 *
 * @author daiqi
 * @create 2019-12-05 23:44
 */
@Data
@Accessors(chain = true)
public class StopStrategyParam extends StrategyParam {
    private static final int DEFAULT_ATTEMPT_NUMBER = 3;

    private StopStrategyEnum stopStrategyEnum;
    /**
     * 停止重试的次数
     */
    private int attemptNumber;

    @Override
    public StopStrategyParam build() {
        super.buildCommon();

        if (BaseUtil.isNull(this.stopStrategyEnum)) {
            this.stopStrategyEnum = StopStrategyEnum.STOP_AFTER_ATTEMPT;
        }
        if (this.attemptNumber <= 0) {
            this.attemptNumber = DEFAULT_ATTEMPT_NUMBER;
        }
        return this;
    }

    @Override
    public String toString() {
        return "StopStrategyParam{" +
                "stopStrategyEnum=" + stopStrategyEnum +
                ", attemptNumber=" + attemptNumber +
                ", timeUnit=" + timeUnit +
                ", maxTime=" + maxTime +
                '}';
    }
}
