package com.sika.code.monitor.core.invoke.metics;

import com.google.common.collect.Maps;
import com.sika.code.monitor.core.alert.matcher.AlertMatcher;
import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.common.properties.MetricsProperties;
import com.sika.code.monitor.core.invoke.config.InvokeAlertRuleConfig;
import com.sika.code.monitor.core.invoke.config.InvokeTimedMetricsConfig;
import com.sika.code.monitor.core.invoke.config.InvokeTimedMetricsItemConfig;
import com.sika.code.monitor.core.invoke.enums.InvokeTimedTypeEnum;
import io.micrometer.core.instrument.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    private final static Map<ID, InvokeTimedMetricsItemConfig> ID_INVOKE_ALERT_RULE_CONFIG_MAP = Maps.newConcurrentMap();
    private final Logger logger = LoggerFactory.getLogger(getClass());

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
            tags.and("metricsType", invokeTimeNsdConfig.getMetricsType());
            registryAlert(meterRegistry, invokeTimeNsdConfig, tags, false);
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

    public void refreshRegistryAlert(MetricsProperties propertiesYetUpdate, MeterRegistry meterRegistry) {
        logger.info("刷新监控告警信息-开始");
        for (Map.Entry<InvokeTimedMetrics.ID, InvokeTimedMetricsItemConfig> entry : getIdInvokeAlertRuleConfigMap().entrySet()) {
            InvokeTimedMetrics.ID id = entry.getKey();
            InvokeTimedMetricsItemConfig configOld = entry.getValue();
            InvokeTimedMetricsItemConfig configNew = (InvokeTimedMetricsItemConfig) loadMetricsConfigManager
                    .getMetricsItemConfigInstance(propertiesYetUpdate, configOld.getMetricsType(), InvokeTimedMetricsConfig.class);
            // 根据缓存的配置构建MeterId
            Meter.Id oldMeterId = new Meter.Id(id.getName(), id.getTags(), null, null, Meter.Type.GAUGE);
            // 移除原来监控的值
            meterRegistry.remove(oldMeterId);
            // 构建新的告警指标
            registryAlert(meterRegistry, configNew, id.getTags(), true);
        }
        logger.info("刷新监控告警信息-完成");
    }

    private void registryAlert(MeterRegistry meterRegistry, InvokeTimedMetricsItemConfig invokeTimeNsdConfig, Tags tags, boolean write) {
        if (tags == null) {
            return;
        }
        List<String> tagValues = tags.stream().map(Tag::getValue).collect(Collectors.toList());
        InvokeAlertRuleConfig alertConfig = AlertMatcher.matchInvokedTime(invokeTimeNsdConfig, tagValues);
        if (alertConfig == null) {
            return;
        }
        String metricsName = invokeTimeNsdConfig.getMetricsName() + ".alert";
        ID idCache = new ID(metricsName, tagValues, tags);

        if (!write && ID_INVOKE_ALERT_RULE_CONFIG_MAP.containsKey(idCache)) {
            return;
        }
        ID_INVOKE_ALERT_RULE_CONFIG_MAP.put(idCache, invokeTimeNsdConfig);
        Gauge.builder(metricsName, alertConfig, InvokeAlertRuleConfig::toMillis)
                .tags(tags)
                .register(meterRegistry);
    }

    public static Map<ID, InvokeTimedMetricsItemConfig> getIdInvokeAlertRuleConfigMap() {
        return ID_INVOKE_ALERT_RULE_CONFIG_MAP;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ID {
        private String name;
        private List<String> tasValues;
        private Tags tags;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ID id = (ID) o;
            return Objects.equals(name, id.name) && Objects.equals(tasValues, id.tasValues);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, tasValues);
        }
    }


}
