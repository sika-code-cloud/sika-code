package com.sika.code.monitor.core.common.manager;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.sika.code.core.base.constant.BaseTypeEnum;
import com.sika.code.monitor.core.common.config.BaseMetricsConfig;
import com.sika.code.monitor.core.common.config.BaseMetricsItemConfig;
import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import com.sika.code.monitor.core.common.properties.MetricsProperties;
import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * LoadMetricsConfigManager
 *
 * @author : daiqi
 * @date : 2023-09-21
 */
@AllArgsConstructor
public class LoadMetricsConfigManager {
    private MetricsProperties metricsProperties;
    private final Map<String, BaseMetricsItemConfig<BaseMetricsConfig<?>>> METRICS_CONFIG_MAP = Maps.newConcurrentMap();

    public BaseMetricsItemConfig<?> getMetricsItemConfigInstance(String metricsType, Class<? extends BaseMetricsConfig<?>> metricsConfigClass) {
        return getMetricsItemConfigInstance(this.metricsProperties, metricsType, metricsConfigClass);
    }

    public BaseMetricsItemConfig<?> getMetricsItemConfigInstance(MetricsProperties metricsProperties, String metricsType, Class<? extends BaseMetricsConfig<?>> metricsConfigClass) {
        BaseMetricsConfig<?> metricsConfig = getMetricConfigInstance(metricsProperties, metricsConfigClass);
        BaseMetricsItemConfig<BaseMetricsConfig<?>> metricsCache = metricsConfig.getItem().get(metricsType);
        // 从配置中获取 - 配置优先
        if (metricsCache != null) {
            metricsCache.buildMetricsType(metricsType);
            metricsCache.buildMetricsConfig(metricsConfig);
            metricsCache.buildMetricsNameAndDesc();
            return metricsCache;
        }

        // 从缓存中获取数据
        String key = buildKey(metricsType, metricsConfigClass);
        metricsCache = METRICS_CONFIG_MAP.get(key);
        if (metricsCache != null) {
            return metricsCache;
        }

        // 从自定义中获取
        metricsCache = getItemConfigInstanceDefault(metricsType, metricsConfig);
        METRICS_CONFIG_MAP.putIfAbsent(key, metricsCache);
        return metricsCache;
    }

    protected BaseMetricsItemConfig<BaseMetricsConfig<?>> getItemConfigInstanceDefault(String metricsTypeValue, BaseMetricsConfig<?> metricsItemConfig) {
        BaseMetricsTypeEnum metricsConfigTypeEnum = BaseTypeEnum.find(metricsTypeValue, metricsItemConfig.getMetricsTypeEnumClass());
        Assert.notNull(metricsTypeValue, "invokeTimedType[{}]对应的枚举不存在，请核实配置", metricsTypeValue);

        Class<? extends BaseMetricsItemConfig> metricsItemConfigClass = metricsItemConfig.getMetricsItemConfigClass();
        assert metricsItemConfigClass != null;
        BaseMetricsItemConfig<BaseMetricsConfig<?>> itemConfig = ReflectUtil.newInstance(metricsItemConfigClass);
        itemConfig.setMetricsDesc(metricsConfigTypeEnum.getDesc());
        itemConfig.setMetricsName(metricsConfigTypeEnum.getName());
        itemConfig.buildMetricsType(metricsTypeValue);
        itemConfig.buildMetricsConfig(metricsItemConfig);
        return itemConfig;
    }

    public BaseMetricsConfig<?> getMetricConfigInstance(MetricsProperties metricsProperties, Class<? extends BaseMetricsConfig<?>> metricsConfigClass) {
        // 从配置中获取 - 配置优先
        if (metricsProperties != null) {
            return metricsProperties.getConfigByType(metricsConfigClass);
        } else {
            return ReflectUtil.newInstance(metricsConfigClass);
        }
    }

    protected String buildKey(String metricsType, Class<?> metricsConfigClass) {
        return StrUtil.join(StrPool.COLON, metricsConfigClass.getName(), metricsType);
    }

    public Map<String, BaseMetricsItemConfig<BaseMetricsConfig<?>>> getConfigMap() {
        return METRICS_CONFIG_MAP;
    }
}
