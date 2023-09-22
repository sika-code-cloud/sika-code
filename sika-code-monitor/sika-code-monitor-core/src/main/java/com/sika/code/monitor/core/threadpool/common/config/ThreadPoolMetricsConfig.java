package com.sika.code.monitor.core.threadpool.common.config;

import com.sika.code.monitor.core.common.config.BaseMetricsConfig;
import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import com.sika.code.monitor.core.threadpool.common.enums.ThreadPoolTypeEnum;

/**
 * ThreadPoolMetricsConfig
 *
 * @author : daiqi
 * @date : 2023-09-21
 */
public class ThreadPoolMetricsConfig extends BaseMetricsConfig {
    @Override
    public Class<? extends BaseMetricsTypeEnum> getMetricsTypeEnumClass() {
        return ThreadPoolTypeEnum.class;
    }
}
