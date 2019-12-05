package com.sika.code.retryer.pojo;

import com.sika.code.basic.util.BaseUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.TimeUnit;

/**
 * @author daiqi
 * @create 2019-12-05 23:47
 */
@Data
@Accessors(chain = true)
public class StrategyParam {
    protected static final Long ZERO = 0L;
    protected static final Long ONE = 1L;
    /** 时间单位 */
    protected TimeUnit timeUnit;
    /**
     * 最大的时长
     */
    protected long maxTime;

    protected void buildCommon() {
        if (BaseUtil.isNull(this.timeUnit)) {
            this.timeUnit = TimeUnit.MILLISECONDS;
        }
        if (this.maxTime < ZERO) {
            this.maxTime = Long.MAX_VALUE;
        }
    }

}
