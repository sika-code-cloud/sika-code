package com.sika.code.retryer.pojo;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.retryer.constant.WaitStrategyEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import static com.sika.code.retryer.constant.WaitStrategyEnum.FIXED;

/**
 * 等待策略的入参
 */
@Data
@Accessors(chain = true)
public class WaitStrategyParam extends StrategyParam{

    private WaitStrategyEnum waitStrategyEnum;
    /** 初始化的时长 */
    private long initTime;
    /** 每次递增的时长 --- 对递增枚举类型有效 */
    private long increment;

    @Override
    public WaitStrategyParam build() {
        super.buildCommon();

        if (BaseUtil.isNull(this.waitStrategyEnum)) {
            this.waitStrategyEnum = FIXED;
        }
        if (this.initTime < ONE) {
            this.initTime = INIT_TIME;
        }
        if (this.increment < ZERO) {
            this.increment = ONE;
        }
        return this;
    }

    @Override
    public String toString() {
        return "WaitStrategyParam{" +
                "waitStrategyEnum=" + waitStrategyEnum +
                ", initTime=" + initTime +
                ", increment=" + increment +
                ", timeUnit=" + timeUnit +
                ", maxTime=" + maxTime +
                '}';
    }
}