package com.sika.code.monitor.core.threadpool.common.config;

import com.sika.code.monitor.core.common.config.BaseMetricsConfig;
import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import com.sika.code.monitor.core.threadpool.common.enums.ThreadPoolTypeEnum;

/**
 * <p>
 * 线程池配置类
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/9/28 8:29
 */
public class ThreadPoolMetricsConfig extends BaseMetricsConfig<ThreadPoolMetricsItemItemConfig> {
    @Override
    public Class<? extends BaseMetricsTypeEnum> getMetricsTypeEnumClass() {
        return ThreadPoolTypeEnum.class;
    }

    @Override
    public Class<ThreadPoolMetricsItemItemConfig> getMetricsItemConfigClass() {
        return ThreadPoolMetricsItemItemConfig.class;
    }
}
