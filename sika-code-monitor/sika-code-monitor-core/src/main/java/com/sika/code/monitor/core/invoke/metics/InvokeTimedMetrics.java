package com.sika.code.monitor.core.invoke.metics;

import com.google.common.collect.Maps;
import com.sika.code.monitor.core.alert.matcher.AlertMatcher;
import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.invoke.config.InvokeAlertRuleConfig;
import com.sika.code.monitor.core.invoke.config.InvokeTimedMetricsConfig;
import com.sika.code.monitor.core.invoke.config.InvokeTimedMetricsItemConfig;
import com.sika.code.monitor.core.invoke.enums.InvokeTimedTypeEnum;
import io.micrometer.core.instrument.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * MetricsUtil
 *
 * @author : daiqi
 * @date : 2023-08-23
 */
@Slf4j
public class InvokeTimedMetrics {
    private final LoadMetricsConfigManager loadMetricsConfigManager;
    public static Map<List<String>, InvokeTimedMetricsItemConfig> idInvokeAlertRuleConfigMap = Maps.newConcurrentMap();

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
        InvokeTimedMetricsItemConfig invokeTimeNsdConfig = getInstance(InvokeTimedTypeEnum.MQ_CONSUME);
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
        InvokeTimedMetricsItemConfig invokeTimeNsdConfig = getInstance(InvokeTimedTypeEnum.MQ_PRODUCE);
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
        InvokeTimedMetricsItemConfig invokeTimeNsdConfig = getInstance(InvokeTimedTypeEnum.DB_CLIENT);
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
        InvokeTimedMetricsItemConfig invokeTimeNsdConfig = getInstance(InvokeTimedTypeEnum.HTTP_CLIENT);
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
        InvokeTimedMetricsItemConfig invokeTimeNsdConfig = getInstance(InvokeTimedTypeEnum.DUBBO_CLIENT);
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
        Tags tags = Tags.of("serviceName", serviceClass.getName()).and("methodName", methodName)
                .and("parameterTypes", parameterTypeStr);
        InvokeTimedMetricsItemConfig invokeTimeNsdConfig = getInstance(InvokeTimedTypeEnum.DUBBO_SERVER);
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
    public void collectInvokeTimed(MeterRegistry meterRegistry, InvokeTimedMetricsItemConfig invokeTimeNsdConfig, Tags tags,
                                   Long invokeTimeNs) {
        try {

            String metricsName = invokeTimeNsdConfig.getMetricsName();
            String metricsDescription = invokeTimeNsdConfig.getMetricsDesc();
            Timer timer = Timer.builder(metricsName).tags(tags).description(metricsDescription)
                    .publishPercentileHistogram(invokeTimeNsdConfig.isPercentilesHistogram())
                    .publishPercentiles(invokeTimeNsdConfig.getPercentiles()).register(meterRegistry);
            timer.record(invokeTimeNs, TimeUnit.NANOSECONDS);
            // 注册告警指标
            registryAlert(meterRegistry, invokeTimeNsdConfig, tags);
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
    public InvokeTimedMetricsItemConfig getInstance(InvokeTimedTypeEnum invokeTimedTypeEnum) {
        assert invokeTimedTypeEnum != null;
        return (InvokeTimedMetricsItemConfig) loadMetricsConfigManager.getMetricsItemConfigInstance(invokeTimedTypeEnum.getType(),
                InvokeTimedMetricsConfig.class);
    }

    private void registryAlert(MeterRegistry meterRegistry, InvokeTimedMetricsItemConfig invokeTimeNsdConfig, Tags tags, String... tasArrs) {
        if (tags == null) {
            return;
        }
        List<String> tagValues = tags.stream().map(Tag::getValue).collect(Collectors.toList());
        InvokeAlertRuleConfig alertConfig = AlertMatcher.matchInvokedTime(invokeTimeNsdConfig, tagValues);
        if (alertConfig == null) {
            return;
        }
        tags.and("metricsType", invokeTimeNsdConfig.getMetricsType());
        System.out.println(alertConfig);
        String metricsName = invokeTimeNsdConfig.getMetricsName() + ".alert";
        Meter.Id id = new Meter.Id(metricsName, tags, null, null, Meter.Type.GAUGE);
        InvokeTimedMetricsItemConfig config = idInvokeAlertRuleConfigMap.putIfAbsent(tagValues, invokeTimeNsdConfig);
        log.info("newConfig:{}, cacheConfig:{}", invokeTimeNsdConfig, config);
        Gauge.builder(invokeTimeNsdConfig.getMetricsName() + ".alert", this, value -> millis(alertConfig))
                .tags(tags)
                .register(meterRegistry);
    }

    private Long millis(InvokeAlertRuleConfig alertConfig) {
        return alertConfig.toMillis();
    }

}
