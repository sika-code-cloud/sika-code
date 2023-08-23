package com.sika.code.monitor.core.common.metrics;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.BaseUnits;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.micrometer.core.lang.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程监控工具类
 *
 * @author : daiqi
 * @date : 2023-06-15
 */
public class ThreadPoolMetrics implements MeterBinder {
    private static final String DTP_METRIC_NAME_PREFIX = "thread.pool";
    /** 线程池实例 */
    private final ThreadPoolExecutor executor;
    /** 线程池类型 */
    private final String threadPoolType;
    /** 线程池名称 */
    private final String threadPoolName;
    private static final Map<ThreadPoolExecutor, AtomicLong> EXECUTOR_TASK_REJECT_COUNT = new ConcurrentHashMap<>();

    public ThreadPoolMetrics(ThreadPoolExecutor executor, String threadPoolType, String threadPoolName) {
        this.executor = executor;
        this.threadPoolType = threadPoolType;
        this.threadPoolName = threadPoolName;
    }

    public static void monitor(@NonNull MeterRegistry registry, @NonNull ThreadPoolExecutor executor,
        @NonNull ThreadPoolTypeEnum threadPoolTypeEnum, @NonNull String threadPoolName) {
        new ThreadPoolMetrics(executor, threadPoolTypeEnum.getName(), threadPoolName).bindTo(registry);
    }

    public static void monitor(@NonNull MeterRegistry registry, @NonNull ThreadPoolExecutor executor,
        @NonNull String threadPoolType, @NonNull String threadPoolName) {
        new ThreadPoolMetrics(executor, threadPoolType, threadPoolName).bindTo(registry);
    }

    @Override
    public void bindTo(@NonNull MeterRegistry registry) {
        registerThreadPoolMetrics(registry);
    }

    private void registerThreadPoolMetrics(MeterRegistry meterRegistry) {
        // prometheus会将指标转为自己的命名风格：threadPoolType.thread.pool.core.size
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

        AtomicLong rejectTaskCount = bindAndRetRejectTaskCount(executor);
        FunctionCounter.builder(metricName("reject.task.count"), rejectTaskCount, AtomicLong::get)
            .baseUnit(BaseUnits.THREADS).description("已拒绝的任务量").tags(tags).register(meterRegistry);
    }

    private static AtomicLong bindAndRetRejectTaskCount(ThreadPoolExecutor executor) {
        AtomicLong rejectTaskCount = EXECUTOR_TASK_REJECT_COUNT.get(executor);
        if (rejectTaskCount == null) {
            rejectTaskCount = new AtomicLong(0);
            EXECUTOR_TASK_REJECT_COUNT.put(executor, rejectTaskCount);
        }
        return rejectTaskCount;
    }

    /**
     * 累加当前线程池拒绝的任务数量
     *
     * @param executor - 线程池对象
     */
    public static void incrementRejectTaskCount(ThreadPoolExecutor executor) {
        AtomicLong atomicLong = bindAndRetRejectTaskCount(executor);
        atomicLong.incrementAndGet();
    }

    private static String metricName(String name) {
        return String.join(".", DTP_METRIC_NAME_PREFIX, name);
    }

}
