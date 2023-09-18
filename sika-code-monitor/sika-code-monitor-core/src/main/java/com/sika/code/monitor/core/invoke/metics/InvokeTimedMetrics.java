package com.sika.code.monitor.core.invoke.metics;

import cn.hutool.core.lang.Assert;
import com.google.common.collect.Maps;
import com.sika.code.core.base.constant.BaseTypeEnum;
import com.sika.code.monitor.core.invoke.config.InvokeTimedConfig;
import com.sika.code.monitor.core.invoke.enums.InvokeTimedTypeEnums;
import com.sika.code.monitor.core.invoke.properties.InvokeTimedProperties;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * MetricsUtil
 *
 * @author : daiqi
 * @date : 2023-08-23
 */
public class InvokeTimedMetrics {
    private final Map<String, InvokeTimedConfig> INVOKE_TIMED_METRICS_MAP = Maps.newConcurrentMap();
    private final InvokeTimedProperties invokeTimedProperties;

    public InvokeTimedMetrics(InvokeTimedProperties invokeTimedProperties) {
        this.invokeTimedProperties = invokeTimedProperties;
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
        InvokeTimedConfig invokeTimeNsdConfig = getInstance(meterRegistry, InvokeTimedTypeEnums.MQ_CONSUME);
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
        InvokeTimedConfig invokeTimeNsdConfig = getInstance(meterRegistry, InvokeTimedTypeEnums.MQ_PRODUCE);
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
        InvokeTimedConfig invokeTimeNsdConfig = getInstance(meterRegistry, InvokeTimedTypeEnums.DB_CLIENT);
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
        InvokeTimedConfig invokeTimeNsdConfig = getInstance(meterRegistry, InvokeTimedTypeEnums.HTTP_CLIENT);
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
        InvokeTimedConfig invokeTimeNsdConfig = getInstance(meterRegistry, InvokeTimedTypeEnums.DUBBO_CLIENT);
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
        InvokeTimedConfig invokeTimeNsdConfig = getInstance(meterRegistry, InvokeTimedTypeEnums.DUBBO_SERVER);
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
     * @param meterRegistry - 指标注册器
     * @return 执行实现指标
     */
    public InvokeTimedConfig getInstance(MeterRegistry meterRegistry, InvokeTimedTypeEnums invokeTimedTypeEnums) {
        assert invokeTimedTypeEnums != null;
        return getInstance(meterRegistry, invokeTimedTypeEnums.getType());
    }

    /**
     * 获取监控实例
     *
     * @param meterRegistry - 指标注册器
     * @return 执行实现指标
     */
    public InvokeTimedConfig getInstance(MeterRegistry meterRegistry, String invokeTimeType) {
        return getInstance(meterRegistry, invokeTimeType, o -> {
            InvokeTimedTypeEnums invokeTimedTypeEnums = BaseTypeEnum.find(invokeTimeType, InvokeTimedTypeEnums.class);
            Assert.notNull(invokeTimeType, "invokeTimedType[{}]对应的枚举不存在，请核实配置", invokeTimeType);
            return new InvokeTimedConfig(meterRegistry, invokeTimedTypeEnums.getName(), invokeTimedTypeEnums.getDesc());
        });
    }

    private InvokeTimedConfig getInstance(MeterRegistry meterRegistry, String invokeTimeType,
        Function<Object, InvokeTimedConfig> function) {
        assert meterRegistry != null;
        // 从缓存中获取数据
        InvokeTimedConfig metricsCache = INVOKE_TIMED_METRICS_MAP.get(invokeTimeType);
        if (metricsCache != null) {
            return metricsCache;
        }
        // 从配置中获取
        if (invokeTimedProperties != null) {
            metricsCache = invokeTimedProperties.getInvoke().get(invokeTimeType);
            if (metricsCache != null) {
                INVOKE_TIMED_METRICS_MAP.putIfAbsent(invokeTimeType, metricsCache);
                return metricsCache;
            }
        }
        // 从自定义中获取
        metricsCache = function.apply(invokeTimeType);
        INVOKE_TIMED_METRICS_MAP.putIfAbsent(invokeTimeType, metricsCache);
        return metricsCache;
    }
}
