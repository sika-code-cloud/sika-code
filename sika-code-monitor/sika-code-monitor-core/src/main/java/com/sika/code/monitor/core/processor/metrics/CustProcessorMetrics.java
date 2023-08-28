package com.sika.code.monitor.core.processor.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * CustProcessorMetrics -
 * 该指标不监控cpu的使用率指标-用来替换基础的进程指标【CPU使用率指标不准确，并且会极大的增加请求耗时】
 *
 * @author : daiqi
 * @date : 2023-07-27
 */
public class CustProcessorMetrics extends ProcessorMetrics {

    @Override
    public void bindTo(MeterRegistry registry) {
        OperatingSystemMXBean operatingSystemBean = ManagementFactory.getOperatingSystemMXBean();
        Runtime runtime = Runtime.getRuntime();
        Gauge.builder("system.cpu.count", runtime, Runtime::availableProcessors)
                .description("The number of processors available to the Java virtual machine").register(registry);

        if (operatingSystemBean.getSystemLoadAverage() >= 0) {
            Gauge.builder("system.load.average.1m", operatingSystemBean, OperatingSystemMXBean::getSystemLoadAverage)
                    .description(
                            "The sum of the number of runnable entities queued to available processors and the number "
                                    + "of runnable entities running on the available processors averaged over a period of time")
                    .register(registry);
        }
    }
}
