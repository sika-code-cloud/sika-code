package com.sika.code.monitor.core.threadpool.dynamictp.manager;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.sika.code.monitor.core.common.enums.ThreadPoolTypeEnum;
import com.sika.code.monitor.core.common.manager.BaseMetricsManager;
import com.sika.code.monitor.core.common.metrics.ThreadPoolMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class DynamicTpThreadPoolMetricsManager implements BaseMetricsManager {

    private final MeterRegistry meterRegistry;

    @Override
    public void registerMetrics() {
        Map<String, DtpExecutor> dtpExecutorMap = SpringUtil.getBeansOfType(DtpExecutor.class);
        if (CollUtil.isEmpty(dtpExecutorMap)) {
            return;
        }
        for (DtpExecutor threadPoolExecutor : dtpExecutorMap.values()) {
            ThreadPoolMetrics.monitor(meterRegistry, threadPoolExecutor, ThreadPoolTypeEnum.BUSINESS_DYNAMICTP,
                threadPoolExecutor.getThreadPoolName(), value -> threadPoolExecutor.getRejectedTaskCount());
        }
    }

}
