package com.sika.code.monitor.core.common.util;

import com.sika.code.monitor.core.common.metrics.InvokeTimedMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.MockClock;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * MetricsUtil
 *
 * @author : daiqi
 * @date : 2023-08-23
 */
public class MetricsUtil {

    /**
     * 收集接口指标 - MQ消费耗时
     *
     * @param meterRegistry : 指标注册器
     * @param mqType        : 目标类名
     * @param group         : 目标方法名称
     * @param topic         : 目标方法名称
     * @param invokeTime    : 执行的耗时时长-单位ms
     */
    public static void collectMqConsumeInvokeTimed(MeterRegistry meterRegistry, String mqType, String topic,
        String group, Long invokeTime) {
        Tags tags = Tags.of("mqType", mqType).and("group", group).and("topic", topic);
        String metricsName = "mq.consume.invoke.timed";
        String metricsDesc = "MQ消费处理耗时";
        InvokeTimedMetrics invokeTimedMetrics = InvokeTimedMetrics.getInstance(meterRegistry, metricsName, metricsDesc);
        collectInvokeTimed(invokeTimedMetrics, tags, invokeTime);
    }

    /**
     * 收集接口指标 - MQ生产耗时
     *
     * @param meterRegistry : 指标注册器
     * @param mqType        : 目标类名
     * @param topic         : 目标方法名称
     * @param invokeTime    : 执行的耗时时长-单位ms
     */
    public static void collectMqProduceInvokeTimed(MeterRegistry meterRegistry, String mqType, String topic,
        Long invokeTime) {
        Tags tags = Tags.of("mqType", mqType).and("topic", topic);
        String metricsName = "mq.produce.invoke.timed";
        String metricsDesc = "MQ生产发送耗时";
        InvokeTimedMetrics invokeTimedMetrics = InvokeTimedMetrics.getInstance(meterRegistry, metricsName, metricsDesc);
        collectInvokeTimed(invokeTimedMetrics, tags, invokeTime);
    }

    /**
     * 收集接口指标 - HTTP客户端
     *
     * @param meterRegistry  : 指标注册器
     * @param sqlCommandType : 目标类名
     * @param methodName     : 目标方法名称
     * @param invokeTime     : 执行的耗时时长-单位ms
     */
    public static void collectDBClientInvokeTimed(MeterRegistry meterRegistry, String sqlCommandType, String methodName,
        Long invokeTime) {
        Tags tags = Tags.of("sqlCommandType", sqlCommandType).and("methodName", methodName);
        String metricsName = "db.client.invoke.timed";
        String metricsDesc = "DB客户端方法执行耗时";
        InvokeTimedMetrics invokeTimedMetrics = InvokeTimedMetrics.getInstance(meterRegistry, metricsName, metricsDesc);
        collectInvokeTimed(invokeTimedMetrics, tags, invokeTime);
    }

    /**
     * 收集接口指标 - HTTP客户端
     *
     * @param meterRegistry : 指标注册器
     * @param domain        : 目标类名
     * @param uri           : 目标方法名称
     * @param method        : 目标方法参数类型列表
     * @param invokeTime    : 执行的耗时时长-单位ms
     */
    public static void collectHttpClientInvokeTimed(MeterRegistry meterRegistry, String domain, String uri,
        String method, Long invokeTime) {
        Tags tags = Tags.of("domain", domain).and("uri", uri).and("method", method.toUpperCase());
        String metricsName = "http.client.invoke.timed";
        String metricsDesc = "HTTP客户端方法执行耗时";
        InvokeTimedMetrics invokeTimedMetrics = InvokeTimedMetrics.getInstance(meterRegistry, metricsName, metricsDesc);
        collectInvokeTimed(invokeTimedMetrics, tags, invokeTime);
    }

    /**
     * 收集接口指标 - HTTP服务端
     *
     * @param meterRegistry : 指标注册器
     * @param uri           : 目标方法名称
     * @param method        : 目标方法参数类型列表
     * @param invokeTime    : 执行的耗时时长-单位ms
     */
    public static void collectHttpServerInvokeTimed(MeterRegistry meterRegistry, String uri, String method,
        Long invokeTime) {
        Tags tags = Tags.of("uri", uri).and("method", method.toUpperCase());
        String metricsName = "http.server.invoke.timed";
        String metricsDesc = "HTTP服务端方法执行耗时";
        InvokeTimedMetrics invokeTimedMetrics = InvokeTimedMetrics.getInstance(meterRegistry, metricsName, metricsDesc);
        collectInvokeTimed(invokeTimedMetrics, tags, invokeTime);
    }

    /**
     * 收集接口指标 - DUBBO客户端
     *
     * @param meterRegistry  : 指标注册器
     * @param className      : 目标类名
     * @param methodName     : 目标方法名称
     * @param parameterTypes : 目标方法参数类型列表
     * @param invokeTime     : 执行的耗时时长-单位ms
     */
    public static void collectDubboClientInvokeTimed(MeterRegistry meterRegistry, Class<?> className, String methodName,
        Class<?>[] parameterTypes, Long invokeTime) {
        String parameterTypeStr = Arrays.toString(parameterTypes);
        Tags tags = Tags.of("serviceName", className.getSimpleName()).and("methodName", methodName)
            .and("parameterTypes", parameterTypeStr);
        String metricsName = "dubbo.client.invoke.timed";
        String metricsDesc = "DUBBO客户端方法执行耗时";
        InvokeTimedMetrics invokeTimedMetrics = InvokeTimedMetrics.getInstance(meterRegistry, metricsName, metricsDesc);
        collectInvokeTimed(invokeTimedMetrics, tags, invokeTime);
    }

    /**
     * 收集接口指标 - DUBBO服务端
     *
     * @param meterRegistry  : 指标注册器
     * @param className      : 目标类名
     * @param methodName     : 目标方法名称
     * @param parameterTypes : 目标方法参数类型列表
     * @param invokeTime     : 执行的耗时时长-单位ms
     */
    public static void collectDubboServerInvokeTimed(MeterRegistry meterRegistry, Class<?> className, String methodName,
        Class<?>[] parameterTypes, Long invokeTime) {
        String parameterTypeStr = Arrays.toString(parameterTypes);
        Tags tags = Tags.of("serviceName", className.getSimpleName()).and("methodName", methodName)
            .and("parameterTypes", parameterTypeStr);
        String metricsName = "dubbo.server.invoke.timed";
        String metricsDesc = "DUBBO服务端方法执行耗时";
        InvokeTimedMetrics invokeTimedMetrics = InvokeTimedMetrics.getInstance(meterRegistry, metricsName, metricsDesc);
        MetricsUtil.collectInvokeTimed(invokeTimedMetrics, tags, invokeTime);
    }

    /**
     * 核心收集执行耗时方法
     *
     * @param invokeTimedMetrics : 执行指标
     * @param tags               : 标签
     * @param invokeTime         : 执行耗时
     */
    public static void collectInvokeTimed(InvokeTimedMetrics invokeTimedMetrics, Tags tags, Long invokeTime) {
        MockClock clock = new MockClock();
        Timer.Sample sample = Timer.start(clock);
        clock.add(invokeTime, TimeUnit.MILLISECONDS);
        stop(sample, invokeTimedMetrics, tags);
    }

    /**
     * 停止
     */
    private static void stop(Timer.Sample sample, InvokeTimedMetrics invokeTimedMetrics, Tags tags) {
        try {
            String metricsName = invokeTimedMetrics.getMetricsName();
            String metricsDescription = invokeTimedMetrics.getMetricsDescription();
            sample.stop(Timer.builder(metricsName).tags(tags).description(metricsDescription)
                .publishPercentileHistogram(invokeTimedMetrics.isPublishPercentileHistogram())
                .publishPercentiles(invokeTimedMetrics.getPublishPercentiles())
                .register(invokeTimedMetrics.getMeterRegistry()));
        } catch (Exception e) {
            invokeTimedMetrics.getLogger().error(e.getMessage(), e);
        }
    }

}
