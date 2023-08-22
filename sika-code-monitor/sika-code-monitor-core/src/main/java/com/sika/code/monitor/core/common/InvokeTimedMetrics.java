package com.sika.code.monitor.core.common;

import io.micrometer.core.instrument.*;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * 请求耗时指标
 * <pre>
 *     1. HttpTimedMetrics httpTimedMetrics = HttpTimedMetrics.start(register, ...)
 *     2. httpTimedMetrics.stop(register)
 * </pre>
 *
 * @author : daiqi
 * @date : 2023-06-19
 */
public abstract class InvokeTimedMetrics {

    private Timer.Sample sample;
    protected static double[] DEFAULT_PUBLISH_PERCENTILES = new double[]{0.50, 0.90, 0.95, 0.99};
    protected double[] publishPercentiles = DEFAULT_PUBLISH_PERCENTILES;
    protected boolean publishPercentileHistogram = false;

    public void record(MeterRegistry meterRegistry, Long invokeTime) {
        MockClock clock = start();
        clock.add(invokeTime, TimeUnit.MILLISECONDS);
        stop(meterRegistry);
    }

    public MockClock start() {
        MockClock clock = new MockClock();
        this.sample = Timer.start(clock);
        return clock;
    }

    /**
     * Timed计时开始 - 此处返回初始化好的RT指标对象
     *
     * @param meterRegistry : 指标注册器
     */
    public void start(MeterRegistry meterRegistry) {
        this.sample = Timer.start(meterRegistry);
    }

    /**
     * 停止
     *
     * @param meterRegistry : 指标注册器
     */
    public void stop(MeterRegistry meterRegistry) {
        try {
            Tags tags = getMetricsTags();
            String metricsName = getMetricsName();
            String metricsDescription = getMetricsDescription();
            getSample().stop(
                    Timer.builder(metricsName).tags(tags).description(metricsDescription).publishPercentileHistogram(getPublishPercentileHistogram())
                            .publishPercentiles(getPublishPercentiles()).register(meterRegistry));
        } catch (Exception e) {
            getLogger().error(e.getMessage(), e);
        }
    }

    public abstract Tags getMetricsTags();

    public abstract String getMetricsDescription();

    public abstract String getMetricsName();

    public Timer.Sample getSample() {
        return sample;
    }

    public abstract Logger getLogger();

    public double[] getPublishPercentiles() {
        return publishPercentiles;
    }

    public boolean getPublishPercentileHistogram() {
        return publishPercentileHistogram;
    }

    public void setPublishPercentiles(double[] publishPercentiles) {
        this.publishPercentiles = publishPercentiles;
    }

    public void setPublishPercentileHistogram(boolean publishPercentileHistogram) {
        this.publishPercentileHistogram = publishPercentileHistogram;
    }
}
