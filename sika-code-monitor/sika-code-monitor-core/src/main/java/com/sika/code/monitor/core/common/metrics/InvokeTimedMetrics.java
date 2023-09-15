package com.sika.code.monitor.core.common.metrics;

import com.sika.code.monitor.core.common.config.InvokeTimedConfig;
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
public class InvokeTimedMetrics {

    /**
     * 收集接口指标 - MQ消费耗时
     *
     * @param meterRegistry : 指标注册器
     * @param mqType        : 目标类名
     * @param group         : 目标方法名称
     * @param topic         : 目标方法名称
     * @param invokeTimeNs    : 执行的耗时时长-单位ns
     */
    public static void collectMqConsumeInvokeTimed(MeterRegistry meterRegistry, String mqType, String topic,
        String group, Long invokeTimeNs) {
        Tags tags = Tags.of("mqType", mqType).and("group", group).and("topic", topic);
        String metricsName = "mq.consume.invoke.timed";
        String metricsDesc = "MQ消费处理耗时";
        InvokeTimedConfig invokeTimeNsdConfig = InvokeTimedConfig.getInstance(meterRegistry, metricsName, metricsDesc);
        collectInvokeTimed(invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    /**
     * 收集接口指标 - MQ生产耗时
     *
     * @param meterRegistry : 指标注册器
     * @param mqType        : 目标类名
     * @param topic         : 目标方法名称
     * @param invokeTimeNs    : 执行的耗时时长-单位ns
     */
    public static void collectMqProduceInvokeTimed(MeterRegistry meterRegistry, String mqType, String topic,
        Long invokeTimeNs) {
        Tags tags = Tags.of("mqType", mqType).and("topic", topic);
        String metricsName = "mq.produce.invoke.timed";
        String metricsDesc = "MQ生产发送耗时";
        InvokeTimedConfig invokeTimeNsdConfig = InvokeTimedConfig.getInstance(meterRegistry, metricsName, metricsDesc);
        collectInvokeTimed(invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    /**
     * 收集接口指标 - HTTP客户端
     *
     * @param meterRegistry  : 指标注册器
     * @param sqlCommandType : 目标类名
     * @param methodName     : 目标方法名称
     * @param invokeTimeNs     : 执行的耗时时长-单位ns
     */
    public static void collectDBClientInvokeTimed(MeterRegistry meterRegistry, String sqlCommandType, String methodName,
        Long invokeTimeNs) {
        Tags tags = Tags.of("sqlCommandType", sqlCommandType).and("methodName", methodName);
        String metricsName = "db.client.invoke.timed";
        String metricsDesc = "DB客户端方法执行耗时";
        InvokeTimedConfig invokeTimeNsdConfig = InvokeTimedConfig.getInstance(meterRegistry, metricsName, metricsDesc);
        collectInvokeTimed(invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    /**
     * 收集接口指标 - HTTP客户端
     *
     * @param meterRegistry : 指标注册器
     * @param domain        : 目标类名
     * @param uri           : 目标方法名称
     * @param method        : 目标方法参数类型列表
     * @param invokeTimeNs    : 执行的耗时时长-单位ns
     */
    public static void collectHttpClientInvokeTimed(MeterRegistry meterRegistry, String domain, String uri,
        String method, Long invokeTimeNs) {
        Tags tags = Tags.of("domain", domain).and("uri", uri).and("method", method.toUpperCase());
        String metricsName = "http.client.invoke.timed";
        String metricsDesc = "HTTP客户端方法执行耗时";
        InvokeTimedConfig invokeTimeNsdConfig = InvokeTimedConfig.getInstance(meterRegistry, metricsName, metricsDesc);
        collectInvokeTimed(invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    /**
     * 收集接口指标 - HTTP服务端
     *
     * @param meterRegistry : 指标注册器
     * @param uri           : 目标方法名称
     * @param method        : 目标方法参数类型列表
     * @param invokeTimeNs    : 执行的耗时时长-单位ns
     */
    public static void collectHttpServerInvokeTimed(MeterRegistry meterRegistry, String uri, String method,
        Long invokeTimeNs) {
        Tags tags = Tags.of("uri", uri).and("method", method.toUpperCase());
        String metricsName = "http.server.invoke.timed";
        String metricsDesc = "HTTP服务端方法执行耗时";
        InvokeTimedConfig invokeTimeNsdConfig = InvokeTimedConfig.getInstance(meterRegistry, metricsName, metricsDesc);
        collectInvokeTimed(invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    /**
     * 收集接口指标 - DUBBO客户端
     *
     * @param meterRegistry  : 指标注册器
     * @param className      : 目标类名
     * @param methodName     : 目标方法名称
     * @param parameterTypes : 目标方法参数类型列表
     * @param invokeTimeNs     : 执行的耗时时长-单位ns
     */
    public static void collectDubboClientInvokeTimed(MeterRegistry meterRegistry, Class<?> className, String methodName,
        Class<?>[] parameterTypes, Long invokeTimeNs) {
        String parameterTypeStr = Arrays.toString(parameterTypes);
        Tags tags = Tags.of("serviceName", className.getSimpleName()).and("methodName", methodName)
            .and("parameterTypes", parameterTypeStr);
        String metricsName = "dubbo.client.invoke.timed";
        String metricsDesc = "DUBBO客户端方法执行耗时";
        InvokeTimedConfig invokeTimeNsdConfig = InvokeTimedConfig.getInstance(meterRegistry, metricsName, metricsDesc);
        collectInvokeTimed(invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    /**
     * 收集接口指标 - DUBBO服务端
     *
     * @param meterRegistry  : 指标注册器
     * @param serviceClass      : 目标类名
     * @param methodName     : 目标方法名称
     * @param parameterTypes : 目标方法参数类型列表
     * @param invokeTimeNs     : 执行的耗时时长-单位ns
     */
    public static void collectDubboServerInvokeTimed(MeterRegistry meterRegistry, Class<?> serviceClass, String methodName,
        Class<?>[] parameterTypes, Long invokeTimeNs) {
        String parameterTypeStr = Arrays.toString(parameterTypes);
        Tags tags = Tags.of("serviceName", serviceClass.getSimpleName()).and("methodName", methodName)
            .and("parameterTypes", parameterTypeStr);
        String metricsName = "dubbo.server.invoke.timed";
        String metricsDesc = "DUBBO服务端方法执行耗时";
        InvokeTimedConfig invokeTimeNsdConfig = InvokeTimedConfig.getInstance(meterRegistry, metricsName, metricsDesc);
        InvokeTimedMetrics.collectInvokeTimed(invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    /**
     * 收集执行时间
     *
     * @param invokeTimeNsdConfig - 执行的配置
     * @param tags              - tag
     * @param invokeTimeNs      - 纳秒值
     */
    public static void collectInvokeTimed(InvokeTimedConfig invokeTimeNsdConfig, Tags tags, Long invokeTimeNs) {
        try {
            String metricsName = invokeTimeNsdConfig.getMetricsName();
            String metricsDescription = invokeTimeNsdConfig.getMetricsDescription();
            Timer timer = Timer.builder(metricsName).tags(tags).description(metricsDescription)
                .publishPercentileHistogram(invokeTimeNsdConfig.isPublishPercentileHistogram())
                .publishPercentiles(invokeTimeNsdConfig.getPublishPercentiles())
                .register(invokeTimeNsdConfig.getMeterRegistry());
            timer.record(invokeTimeNs, TimeUnit.NANOSECONDS);
        } catch (Exception e) {
            invokeTimeNsdConfig.getLogger().error(e.getMessage(), e);
        }
    }

}
