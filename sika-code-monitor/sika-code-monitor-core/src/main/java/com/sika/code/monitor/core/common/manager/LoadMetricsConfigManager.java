package com.sika.code.monitor.core.common.manager;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.sika.code.core.base.constant.BaseTypeEnum;
import com.sika.code.monitor.core.common.config.BaseMetricsConfig;
import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import com.sika.code.monitor.core.common.properties.MetricsProperties;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.function.Function;

/**
 * LoadMetricsConfigManager
 *
 * @author : daiqi
 * @date : 2023-09-21
 */
@AllArgsConstructor
public class LoadMetricsConfigManager {
    private MetricsProperties metricsProperties;
    private final Map<String, BaseMetricsConfig> METRICS_CONFIG_MAP = Maps.newConcurrentMap();

    public <T extends BaseMetricsConfig> T getMetricsConfigInstance(String metricsType, Class<T> metricsConfigClass) {
        T metricsCache = null;
        // 从配置中获取 - 配置优先
        if (metricsProperties != null) {
            metricsCache = (T)metricsProperties.getConfigByType(metricsType, metricsConfigClass);
            if (metricsCache != null) {
                metricsCache.setMetricsType(metricsType);
                return metricsCache;
            }
        }

        // 从缓存中获取数据
        String key = buildKey(metricsType, metricsConfigClass);
        metricsCache = (T)METRICS_CONFIG_MAP.get(key);
        if (metricsCache != null) {
            return metricsCache;
        }

        // 从自定义中获取
        metricsCache = getConfigInstanceDefault(metricsType, metricsConfigClass);
        METRICS_CONFIG_MAP.putIfAbsent(key, metricsCache);
        return metricsCache;
    }

    protected <T extends BaseMetricsConfig> T getConfigInstanceDefault(String metricsTypeValue, Class<T> metricsConfigClass) {
        T metricsConfig = ReflectUtil.newInstance(metricsConfigClass);
        BaseMetricsTypeEnum metricsConfigTypeEnum = BaseTypeEnum.find(metricsTypeValue, metricsConfig.getMetricsTypeEnumClass());
        Assert.notNull(metricsTypeValue, "invokeTimedType[{}]对应的枚举不存在，请核实配置", metricsTypeValue);

        metricsConfig.setMetricsDesc(metricsConfigTypeEnum.getDesc());
        metricsConfig.setMetricsName(metricsConfigTypeEnum.getName());
        metricsConfig.setMetricsType(metricsTypeValue);
        return metricsConfig;
    }
    protected String buildKey(String metricsType, Class<?> metricsConfigClass) {
        return StrUtil.join(StrPool.COLON, metricsConfigClass.getName(), metricsType);
    }
}
