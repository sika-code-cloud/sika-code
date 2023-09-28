package com.sika.code.monitor.core.common.config;

import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import com.sika.code.monitor.core.invoke.config.InvokeTimedMetricsItemConfig;
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
@Setter
@Getter
public abstract class BaseMetricsConfig<T extends BaseMetricsItemConfig> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected final Map<String, T> item = new LinkedHashMap<>();

    public Logger getLogger() {
        return logger;
    }

    /**
     * 获取指标类型枚举的class
     * @return
     */
    public abstract Class<? extends BaseMetricsTypeEnum> getMetricsTypeEnumClass();

    public abstract Class<T> getMetricsItemConfigClass();
}
