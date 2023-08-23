package com.sika.code.monitor.core.common.metrics;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

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
public class InvokeTimedMetrics {

    protected static double[] DEFAULT_PUBLISH_PERCENTILES = new double[]{0.50, 0.90, 0.95, 0.99};
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected double[] publishPercentiles = DEFAULT_PUBLISH_PERCENTILES;
    protected boolean publishPercentileHistogram = false;
    protected String metricsName;
    protected String metricsDescription;
    protected MeterRegistry meterRegistry;
    private static final Map<String, InvokeTimedMetrics> INVOKE_TIMED_METRICS_MAP = Maps.newConcurrentMap();

    /**
     * 获取监控实例
     *
     * @param meterRegistry      - 指标注册器
     * @param metricsName        - 指标名称
     * @param metricsDescription - 指标描述
     * @return 执行实现指标
     */
    public static InvokeTimedMetrics getInstance(MeterRegistry meterRegistry, String metricsName, String metricsDescription) {
        assert meterRegistry != null;
        assert StrUtil.isNotBlank(metricsName);
        assert StrUtil.isNotBlank(metricsDescription);
        String key = buildKey(meterRegistry, metricsName, metricsDescription);
        InvokeTimedMetrics metricsCache = INVOKE_TIMED_METRICS_MAP.get(key);
        if (metricsCache != null) {
            return metricsCache;
        }
        InvokeTimedMetrics metricsCacheForNew = new InvokeTimedMetrics(meterRegistry, metricsName, metricsDescription);
        metricsCache = INVOKE_TIMED_METRICS_MAP.putIfAbsent(key, metricsCacheForNew);
        if (metricsCache != null) {
            return metricsCache;
        }
        return metricsCacheForNew;
    }

    public static String buildKey(MeterRegistry meterRegistry, String metricsName, String metricsDescription) {
        return StrUtil.join(StrPool.COLON, meterRegistry.getClass().getName(), metricsName, metricsDescription);
    }

    public InvokeTimedMetrics(MeterRegistry meterRegistry, String metricsName, String metricsDescription) {
        this.meterRegistry = meterRegistry;
        this.metricsName = metricsName;
        this.metricsDescription = metricsDescription;
    }

    public Logger getLogger() {
        return logger;
    }

    public InvokeTimedMetrics buildLogger(Logger logger) {
        this.logger = logger;
        return this;
    }

    public double[] getPublishPercentiles() {
        return publishPercentiles;
    }

    public InvokeTimedMetrics buildPublishPercentiles(double[] publishPercentiles) {
        this.publishPercentiles = publishPercentiles;
        return this;
    }

    public boolean isPublishPercentileHistogram() {
        return publishPercentileHistogram;
    }

    public InvokeTimedMetrics buildPublishPercentileHistogram(boolean publishPercentileHistogram) {
        this.publishPercentileHistogram = publishPercentileHistogram;
        return this;
    }

    public String getMetricsName() {
        return metricsName;
    }

    public InvokeTimedMetrics buildMetricsName(String metricsName) {
        this.metricsName = metricsName;
        return this;
    }

    public String getMetricsDescription() {
        return metricsDescription;
    }

    public InvokeTimedMetrics buildMetricsDescription(String metricsDescription) {
        this.metricsDescription = metricsDescription;
        return this;
    }

    public MeterRegistry getMeterRegistry() {
        return meterRegistry;
    }

    public InvokeTimedMetrics buildMeterRegistry(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        return this;
    }

}
