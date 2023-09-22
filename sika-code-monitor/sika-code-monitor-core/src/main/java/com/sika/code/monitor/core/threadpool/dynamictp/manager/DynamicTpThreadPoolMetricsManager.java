package com.sika.code.monitor.core.threadpool.dynamictp.manager;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.sika.code.monitor.core.common.manager.BaseMetricsManager;
import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.threadpool.enums.ThreadPoolTypeEnum;
import com.sika.code.monitor.core.threadpool.metrics.ThreadPoolMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.dromara.dynamictp.core.thread.DtpExecutor;

import java.util.Map;

/**
 * DynamicTpThreadPoolMetricsManager
 *
 * @author : daiqi
 * @date : 2023-09-04
 */
@Slf4j
public class DynamicTpThreadPoolMetricsManager extends BaseMetricsManager<ThreadPoolTypeEnum> {

    public DynamicTpThreadPoolMetricsManager(LoadMetricsConfigManager loadMetricsConfigManager,
        MeterRegistry meterRegistry) {
        super(loadMetricsConfigManager, meterRegistry);
    }

    @Override
    public void registerMetrics() {
        Map<String, DtpExecutor> dtpExecutorMap = SpringUtil.getBeansOfType(DtpExecutor.class);
        if (CollUtil.isEmpty(dtpExecutorMap)) {
            return;
        }
        for (DtpExecutor threadPoolExecutor : dtpExecutorMap.values()) {
            String poolName = StrUtil.join(StrPool.DOT, getThreadPoolPrefix(), threadPoolExecutor.getThreadPoolName());
            ThreadPoolMetrics.monitor(meterRegistry, threadPoolExecutor, getMetricsTypeEnum(), poolName,
                value -> threadPoolExecutor.getRejectedTaskCount());
        }
    }

    @Override
    protected ThreadPoolTypeEnum getMetricsTypeEnum() {
        return ThreadPoolTypeEnum.BUSINESS_DYNAMICTP;
    }

}
