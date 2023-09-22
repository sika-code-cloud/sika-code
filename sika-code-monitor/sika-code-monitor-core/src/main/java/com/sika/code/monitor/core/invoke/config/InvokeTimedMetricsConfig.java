package com.sika.code.monitor.core.invoke.config;

import com.sika.code.monitor.core.common.config.BaseMetricsConfig;
import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import com.sika.code.monitor.core.invoke.enums.InvokeTimedTypeEnum;
import lombok.NoArgsConstructor;

/**
 * 请求耗时指标
 * <pre>
 *     1. InvokeTimedMetrics invokeTimedMetrics = new InvokeTimedMetrics();
 *     2. httpTimedMetrics.record(register, "")
 * </pre>
 *
 * @author : daiqi
 * @date : 2023-06-19
 */
@NoArgsConstructor
public class InvokeTimedMetricsConfig extends BaseMetricsConfig {

    protected static double[] DEFAULT_PUBLISH_PERCENTILES = new double[] {0.50, 0.90, 0.95, 0.99, 0.999};

    protected double[] percentiles = DEFAULT_PUBLISH_PERCENTILES;
    protected boolean percentilesHistogram = false;

    public InvokeTimedMetricsConfig(String metricsName, String metricsDesc) {
        this.metricsName = metricsName;
        this.metricsDesc = metricsDesc;
    }

    public double[] getPercentiles() {
        return percentiles;
    }

    public InvokeTimedMetricsConfig buildPublishPercentiles(double[] publishPercentiles) {
        this.percentiles = publishPercentiles;
        return this;
    }

    public boolean isPercentilesHistogram() {
        return percentilesHistogram;
    }

    public InvokeTimedMetricsConfig buildPublishPercentileHistogram(boolean publishPercentileHistogram) {
        this.percentilesHistogram = publishPercentileHistogram;
        return this;
    }

    public void setPercentiles(double[] percentiles) {
        this.percentiles = percentiles;
    }

    public void setPercentilesHistogram(boolean percentilesHistogram) {
        this.percentilesHistogram = percentilesHistogram;
    }

    @Override
    public Class<? extends BaseMetricsTypeEnum> getMetricsTypeEnumClass() {
        return InvokeTimedTypeEnum.class;
    }
}
