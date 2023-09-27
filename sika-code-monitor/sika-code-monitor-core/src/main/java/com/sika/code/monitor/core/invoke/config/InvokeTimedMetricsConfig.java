package com.sika.code.monitor.core.invoke.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.sika.code.monitor.core.common.config.BaseMetricsConfig;
import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import com.sika.code.monitor.core.invoke.enums.InvokeTimedTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 请求耗时指标
 *
 * @author : daiqi
 * @date : 2023-06-19
 */
@NoArgsConstructor
@Getter
@Setter
public class InvokeTimedMetricsConfig extends BaseMetricsConfig {

    protected static final double[] DEFAULT_PUBLISH_PERCENTILES = new double[]{0.50, 0.90, 0.95, 0.99, 0.999};

    protected static final InvokeAlertConfig DEFAULT_CONFIG = new InvokeAlertConfig();
    /**
     * 95线等指标
     */
    protected double[] percentiles = DEFAULT_PUBLISH_PERCENTILES;
    /**
     * 是否使用Histogram 默认false-直接客户端汇总上报
     */
    protected boolean percentilesHistogram = false;
    private List<InvokeAlertConfig> invokeAlert = Lists.newCopyOnWriteArrayList();

    public InvokeTimedMetricsConfig(String metricsName, String metricsDesc) {
        this.metricsName = metricsName;
        this.metricsDesc = metricsDesc;
    }

    @Override
    public Class<? extends BaseMetricsTypeEnum> getMetricsTypeEnumClass() {
        return InvokeTimedTypeEnum.class;
    }

    public InvokeAlertConfig match(List<String> waitMatchArrays) {
        if (ArrayUtil.isEmpty(waitMatchArrays)) {
            return null;
        }
        String waitMatchStr = CollUtil.join(waitMatchArrays, StrPool.COLON);
        if (StrUtil.isBlank(waitMatchStr)) {
            return null;
        }
        if (CollUtil.isEmpty(invokeAlert)) {
            return DEFAULT_CONFIG;
        }
        if (StrUtil.isBlank(getMetricsType())) {
            return DEFAULT_CONFIG;
        }
        logger.info("开始进入正则表达式匹配：{}", waitMatchStr);
        return invokeAlert.stream()
                .filter(config -> ReUtil.isMatch(config.getPatten(), waitMatchStr))
                .findFirst().orElse(DEFAULT_CONFIG);
    }

    @Getter
    @Setter
    public static class InvokeAlertConfig {

        /**
         * 匹配的路径表达式
         */
        private String patten = ".*";
        /**
         * 告警触发的阈值时长
         */
        private long threshold = 1000L;
        /**
         * 告警阈值的单位-毫秒|秒等等
         */
        private TimeUnit timeUnit = TimeUnit.MICROSECONDS;

    }
}
