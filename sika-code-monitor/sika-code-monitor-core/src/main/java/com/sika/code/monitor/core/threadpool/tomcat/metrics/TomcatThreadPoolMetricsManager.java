package com.sika.code.monitor.core.threadpool.tomcat.metrics;

import com.sika.code.monitor.core.common.manager.BaseMetricsManager;
import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.threadpool.config.ThreadPoolMetricsConfig;
import com.sika.code.monitor.core.threadpool.enums.ThreadPoolTypeEnum;
import com.sika.code.monitor.core.threadpool.metrics.ThreadPoolMetrics;
import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.BaseUnits;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;

/**
 * TomcatThreadPoolMetrics
 *
 * @author : daiqi
 * @date : 2023-06-25
 */
@Slf4j
public class TomcatThreadPoolMetricsManager extends BaseMetricsManager<ThreadPoolTypeEnum> {
    private final WebServerApplicationContext webServerApplicationContext;

    public TomcatThreadPoolMetricsManager(LoadMetricsConfigManager loadMetricsConfigManager,
        MeterRegistry meterRegistry, WebServerApplicationContext webServerApplicationContext) {
        super(loadMetricsConfigManager, meterRegistry);
        this.webServerApplicationContext = webServerApplicationContext;
    }

    private void monitor(ThreadPoolExecutor executor, String threadPoolType) {
        // prometheus会将指标转为自己的命名风格：threadPoolType.thread.pool.core.size
        String threadPoolName = getThreadPoolPrefix();
        // 定义标签
        Tags tags = Tags.of("thread.pool.name", threadPoolName).and("thread.pool.type", threadPoolType);
        Gauge.builder(metricName("core.size"), executor, ThreadPoolExecutor::getCorePoolSize).description("核心线程数")
            .baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        Gauge.builder(metricName("largest.size"), executor, ThreadPoolExecutor::getLargestPoolSize)
            .description("历史最高线程数").baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        Gauge.builder(metricName("max.size"), executor, ThreadPoolExecutor::getMaximumPoolSize)
            .description("最大线程数").baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        Gauge.builder(metricName("active.size"), executor, ThreadPoolExecutor::getActiveCount).description("活跃线程数")
            .baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        Gauge.builder(metricName("thread.count"), executor, ThreadPoolExecutor::getPoolSize).description("当前线程数")
            .baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        Gauge.builder(metricName("queue.size"), executor, e -> e.getQueue().size()).description("任务队列大小")
            .baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        FunctionCounter.builder(metricName("task.count"), executor, ThreadPoolExecutor::getTaskCount)
            .description("任务总量").baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        FunctionCounter.builder(metricName("completed.task.count"), executor, ThreadPoolExecutor::getCompletedTaskCount)
            .baseUnit(BaseUnits.THREADS).description("已完成的任务量").tags(tags).register(meterRegistry);

    }

    private String metricName(String metricsName) {
        return ThreadPoolMetrics.metricName(metricsName);
    }

    private String getThreadPoolName() {
        return loadMetricsConfigManager.getMetricsConfigInstance(ThreadPoolTypeEnum.TOMCAT.getType(),
            ThreadPoolMetricsConfig.class).getMetricsName();
    }

    @Override
    public void registerMetrics() {
        String poolName = getThreadPoolName();
        log.info("线程池类型【{}】线程池名称【{}】加入监控开始", ThreadPoolTypeEnum.TOMCAT.getType(), poolName);
        //获取webServer线程池 - 待实现
        ThreadPoolExecutor executor =
            (ThreadPoolExecutor)((TomcatWebServer)webServerApplicationContext.getWebServer()).getTomcat().getConnector()
                .getProtocolHandler().getExecutor();
        monitor(executor, ThreadPoolTypeEnum.TOMCAT.getType());
        log.info("线程池类型【{}】线程池名称【{}】加入监控成功", ThreadPoolTypeEnum.TOMCAT.getType(), poolName);
    }

    @Override
    protected ThreadPoolTypeEnum getMetricsTypeEnum() {
        return ThreadPoolTypeEnum.TOMCAT;
    }
}
