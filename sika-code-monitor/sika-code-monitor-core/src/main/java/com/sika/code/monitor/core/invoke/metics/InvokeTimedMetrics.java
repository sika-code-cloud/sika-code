package com.sika.code.monitor.core.invoke.metics;

import com.google.common.collect.Maps;
import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.invoke.config.InvokeTimedConfig;
import com.sika.code.monitor.core.invoke.enums.InvokeTimedTypeEnum;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * MetricsUtil
 *
 * @author : daiqi
 * @date : 2023-08-23
 */
public class InvokeTimedMetrics {
    private final LoadMetricsConfigManager loadMetricsConfigManager;

    public InvokeTimedMetrics(LoadMetricsConfigManager loadMetricsConfigManager) {
        this.loadMetricsConfigManager = loadMetricsConfigManager;
    }

    /**
     * 收集接口指标 - MQ消费耗时
     *
     * @param meterRegistry : 指标注册器
     * @param mqType        : 目标类名
     * @param group         : 目标方法名称
     * @param topic         : 目标方法名称
     * @param invokeTimeNs  : 执行的耗时时长-单位ns
     */
    public void collectMqConsumeInvokeTimed(MeterRegistry meterRegistry, String mqType, String topic, String group,
        Long invokeTimeNs) {
        Tags tags = Tags.of("mqType", mqType).and("group", group).and("topic", topic);
        InvokeTimedConfig invokeTimeNsdConfig = getInstance(InvokeTimedTypeEnum.MQ_CONSUME);
        collectInvokeTimed(meterRegistry, invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    /**
     * 收集接口指标 - MQ生产耗时
     *
     * @param meterRegistry : 指标注册器
     * @param mqType        : 目标类名
     * @param topic         : 目标方法名称
     * @param invokeTimeNs  : 执行的耗时时长-单位ns
     */
    public void collectMqProduceInvokeTimed(MeterRegistry meterRegistry, String mqType, String topic,
        Long invokeTimeNs) {
        Tags tags = Tags.of("mqType", mqType).and("topic", topic);
        InvokeTimedConfig invokeTimeNsdConfig = getInstance(InvokeTimedTypeEnum.MQ_PRODUCE);
        collectInvokeTimed(meterRegistry, invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    /**
     * 收集接口指标 - HTTP客户端
     *
     * @param meterRegistry  : 指标注册器
     * @param sqlCommandType : 目标类名
     * @param methodName     : 目标方法名称
     * @param invokeTimeNs   : 执行的耗时时长-单位ns
     */
    public void collectDBClientInvokeTimed(MeterRegistry meterRegistry, String sqlCommandType, String methodName,
        Long invokeTimeNs) {
        Tags tags = Tags.of("sqlCommandType", sqlCommandType).and("methodName", methodName);
        InvokeTimedConfig invokeTimeNsdConfig = getInstance(InvokeTimedTypeEnum.DB_CLIENT);
        collectInvokeTimed(meterRegistry, invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    /**
     * 收集接口指标 - HTTP客户端
     *
     * @param meterRegistry : 指标注册器
     * @param domain        : 目标类名
     * @param uri           : 目标方法名称
     * @param method        : 目标方法参数类型列表
     * @param invokeTimeNs  : 执行的耗时时长-单位ns
     */
    public void collectHttpClientInvokeTimed(MeterRegistry meterRegistry, String domain, String uri, String method,
        Long invokeTimeNs) {
        Tags tags = Tags.of("domain", domain).and("uri", uri).and("method", method.toUpperCase());
        InvokeTimedConfig invokeTimeNsdConfig = getInstance(InvokeTimedTypeEnum.HTTP_CLIENT);
        collectInvokeTimed(meterRegistry, invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    /**
     * 收集接口指标 - DUBBO客户端
     *
     * @param meterRegistry  : 指标注册器
     * @param className      : 目标类名
     * @param methodName     : 目标方法名称
     * @param parameterTypes : 目标方法参数类型列表
     * @param invokeTimeNs   : 执行的耗时时长-单位ns
     */
    public void collectDubboClientInvokeTimed(MeterRegistry meterRegistry, Class<?> className, String methodName,
        Class<?>[] parameterTypes, Long invokeTimeNs) {
        String parameterTypeStr = Arrays.toString(parameterTypes);
        Tags tags = Tags.of("serviceName", className.getSimpleName()).and("methodName", methodName)
            .and("parameterTypes", parameterTypeStr);
        InvokeTimedConfig invokeTimeNsdConfig = getInstance(InvokeTimedTypeEnum.DUBBO_CLIENT);
        collectInvokeTimed(meterRegistry, invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    /**
     * 收集接口指标 - DUBBO服务端
     *
     * @param meterRegistry  : 指标注册器
     * @param serviceClass   : 目标类名
     * @param methodName     : 目标方法名称
     * @param parameterTypes : 目标方法参数类型列表
     * @param invokeTimeNs   : 执行的耗时时长-单位ns
     */
    public void collectDubboServerInvokeTimed(MeterRegistry meterRegistry, Class<?> serviceClass, String methodName,
        Class<?>[] parameterTypes, Long invokeTimeNs) {
        String parameterTypeStr = Arrays.toString(parameterTypes);
        Tags tags = Tags.of("serviceName", serviceClass.getSimpleName()).and("methodName", methodName)
            .and("parameterTypes", parameterTypeStr);
        InvokeTimedConfig invokeTimeNsdConfig = getInstance(InvokeTimedTypeEnum.DUBBO_SERVER);
        collectInvokeTimed(meterRegistry, invokeTimeNsdConfig, tags, invokeTimeNs);
    }

    /**
     * 收集执行时间
     *
     * @param meterRegistry       - 注册
     * @param invokeTimeNsdConfig - 执行的配置
     * @param tags                - tag
     * @param invokeTimeNs        - 纳秒值
     */
    public void collectInvokeTimed(MeterRegistry meterRegistry, InvokeTimedConfig invokeTimeNsdConfig, Tags tags,
        Long invokeTimeNs) {
        try {
            String metricsName = invokeTimeNsdConfig.getMetricsName();
            String metricsDescription = invokeTimeNsdConfig.getMetricsDesc();
            Timer timer = Timer.builder(metricsName).tags(tags).description(metricsDescription)
                .publishPercentileHistogram(invokeTimeNsdConfig.isPercentilesHistogram())
                .publishPercentiles(invokeTimeNsdConfig.getPercentiles()).register(meterRegistry);
            timer.record(invokeTimeNs, TimeUnit.NANOSECONDS);
        } catch (Exception e) {
            invokeTimeNsdConfig.getLogger().error(e.getMessage(), e);
        }
    }

    /**
     * 获取监控实例
     *
     * @param invokeTimedTypeEnum - 执行枚举
     * @return 执行实现指标
     */
    public InvokeTimedConfig getInstance(InvokeTimedTypeEnum invokeTimedTypeEnum) {
        assert invokeTimedTypeEnum != null;
        return loadMetricsConfigManager.getMetricsConfigInstance(invokeTimedTypeEnum.getType(),
            InvokeTimedConfig.class);
    }
}
