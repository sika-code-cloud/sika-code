package com.sika.code.monitor.core.invoke.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class InvokeTimedConfig {

    protected static double[] DEFAULT_PUBLISH_PERCENTILES = new double[] {0.50, 0.90, 0.95, 0.99, 0.999};
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected double[] percentiles = DEFAULT_PUBLISH_PERCENTILES;
    protected boolean percentilesHistogram = false;
    protected String metricsName;
    protected String metricsDesc;

    protected MeterRegistry meterRegistry;

    public InvokeTimedConfig(MeterRegistry meterRegistry, String metricsName, String metricsDesc) {
        this.meterRegistry = meterRegistry;
        this.metricsName = metricsName;
        this.metricsDesc = metricsDesc;
    }

    public Logger getLogger() {
        return logger;
    }

    public InvokeTimedConfig buildLogger(Logger logger) {
        this.logger = logger;
        return this;
    }

    public double[] getPercentiles() {
        return percentiles;
    }

    public InvokeTimedConfig buildPublishPercentiles(double[] publishPercentiles) {
        this.percentiles = publishPercentiles;
        return this;
    }

    public boolean isPercentilesHistogram() {
        return percentilesHistogram;
    }

    public InvokeTimedConfig buildPublishPercentileHistogram(boolean publishPercentileHistogram) {
        this.percentilesHistogram = publishPercentileHistogram;
        return this;
    }

    public String getMetricsName() {
        return metricsName;
    }

    public InvokeTimedConfig buildMetricsName(String metricsName) {
        this.metricsName = metricsName;
        return this;
    }

    public String getMetricsDesc() {
        return metricsDesc;
    }

    public InvokeTimedConfig buildMetricsDescription(String metricsDescription) {
        this.metricsDesc = metricsDescription;
        return this;
    }

    public MeterRegistry getMeterRegistry() {
        return meterRegistry;
    }

    public InvokeTimedConfig buildMeterRegistry(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        return this;
    }

    public void setPercentiles(double[] percentiles) {
        this.percentiles = percentiles;
    }

    public void setPercentilesHistogram(boolean percentilesHistogram) {
        this.percentilesHistogram = percentilesHistogram;
    }

    public void setMetricsName(String metricsName) {
        this.metricsName = metricsName;
    }

    public void setMetricsDesc(String metricsDesc) {
        this.metricsDesc = metricsDesc;
    }

}
