package com.sika.code.monitor.core.invoke.config;

import com.google.common.collect.Lists;
import com.sika.code.monitor.core.common.config.BaseMetricsItemConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 请求耗时指标
 *
 * @author : daiqi
 * @date : 2023-06-19
 */
@NoArgsConstructor
@Getter
@Setter
public class InvokeTimedMetricsItemConfig extends BaseMetricsItemConfig<InvokeTimedMetricsConfig> {

    protected static final double[] DEFAULT_PUBLISH_PERCENTILES = new double[]{0.50, 0.90, 0.95, 0.99, 0.999};

    protected static final InvokeAlertRuleConfig DEFAULT_CONFIG = new InvokeAlertRuleConfig();
    /**
     * 95线等指标
     */
    protected double[] percentiles = DEFAULT_PUBLISH_PERCENTILES;
    /**
     * 是否使用Histogram 默认false-直接客户端汇总上报
     */
    protected boolean percentilesHistogram = false;

    private List<InvokeAlertRuleConfig> alertRules = Lists.newCopyOnWriteArrayList();

    public InvokeTimedMetricsItemConfig(String metricsName, String metricsDesc) {
        this.metricsName = metricsName;
        this.metricsDesc = metricsDesc;
    }
}
