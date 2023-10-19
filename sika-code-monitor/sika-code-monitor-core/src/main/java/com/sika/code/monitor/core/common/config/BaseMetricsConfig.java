package com.sika.code.monitor.core.common.config;

import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * MetricsCommonConfig
 *
 * @author : daiqi
 * @date : 2023-09-21
 */
public abstract class BaseMetricsConfig<T extends BaseMetricsItemConfig> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected final Map<String, T> item = new LinkedHashMap<>();

    public Logger logger() {
        return logger;
    }

    /**
     * 获取指标类型枚举的class
     * @return
     */
    public abstract Class<? extends BaseMetricsTypeEnum> getMetricsTypeEnumClass();

    public abstract Class<T> getMetricsItemConfigClass();

    public Map<String, T> getItem() {
        return item;
    }
}
