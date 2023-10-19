package com.sika.code.monitor.core.invoke.config;

import com.sika.code.monitor.core.alert.config.BaseAlertRuleConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class InvokeAlertRuleConfig extends BaseAlertRuleConfig {
    /**
     * 告警触发的阈值时长
     */
    private long threshold = 1000L;
    /**
     * 告警阈值的单位-毫秒|秒等等
     */
    private TimeUnit timeUnit = TimeUnit.MICROSECONDS;

    public long toMillis() {
        System.out.println(this + "--------" + threshold);
        return getTimeUnit().toMillis(getThreshold());
    }

}