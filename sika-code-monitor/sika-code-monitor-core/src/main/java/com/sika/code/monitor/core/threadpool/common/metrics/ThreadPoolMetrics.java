package com.sika.code.monitor.core.threadpool.common.metrics;

import cn.hutool.core.text.StrPool;
import com.sika.code.monitor.core.threadpool.common.enums.ThreadPoolTypeEnum;
import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.BaseUnits;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.micrometer.core.lang.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.ToDoubleFunction;

/**
 * 线程监控工具类
 *
 * @author : daiqi
 * @date : 2023-06-15
 */
@Slf4j
public class ThreadPoolMetrics implements MeterBinder {
    private static final String DTP_METRIC_NAME_PREFIX = "thread.pool";
    /** 线程池实例 */
    private final ThreadPoolExecutor executor;
    /** 线程池类型 */
    private final String threadPoolType;
    /** 线程池名称 */
    private final String threadPoolNamePrefix;
    /** 拒绝数量函数 */
    private ToDoubleFunction<ThreadPoolExecutor> rejectCountFunc;
    private static final Map<ThreadPoolExecutor, AtomicLong> EXECUTOR_TASK_REJECT_COUNT = new ConcurrentHashMap<>();

    public ThreadPoolMetrics(ThreadPoolExecutor executor, String threadPoolType, String threadPoolNamePrefix) {
        this.executor = executor;
        this.threadPoolType = threadPoolType;
        this.threadPoolNamePrefix = threadPoolNamePrefix;
    }

    public ThreadPoolMetrics(ThreadPoolExecutor executor, String threadPoolType, String threadPoolNamePrefix,
        ToDoubleFunction<ThreadPoolExecutor> rejectCountFunc) {
        this.executor = executor;
        this.threadPoolType = threadPoolType;
        this.threadPoolNamePrefix = threadPoolNamePrefix;
        this.rejectCountFunc = rejectCountFunc;
    }

    public static void monitor(@NonNull MeterRegistry registry, @NonNull ThreadPoolExecutor executor,
        @NonNull ThreadPoolTypeEnum threadPoolTypeEnum, @NonNull String threadPoolName) {
        new ThreadPoolMetrics(executor, threadPoolTypeEnum.getType(), threadPoolName).bindTo(registry);
    }

    public static void monitor(@NonNull MeterRegistry registry, @NonNull ThreadPoolExecutor executor,
        @NonNull String threadPoolType, @NonNull String threadPoolName) {
        new ThreadPoolMetrics(executor, threadPoolType, threadPoolName).bindTo(registry);
    }

    public static void monitor(@NonNull MeterRegistry registry, @NonNull ThreadPoolExecutor executor,
        @NonNull ThreadPoolTypeEnum threadPoolTypeEnum, @NonNull String threadPoolName,
        ToDoubleFunction<ThreadPoolExecutor> rejectCountFunc) {
        new ThreadPoolMetrics(executor, threadPoolTypeEnum.getType(), threadPoolName,
            rejectCountFunc).bindTo(registry);
    }

    public static void monitor(@NonNull MeterRegistry registry, @NonNull ThreadPoolExecutor executor,
        @NonNull String threadPoolType, @NonNull String threadPoolName,
        ToDoubleFunction<ThreadPoolExecutor> rejectCountFunc) {
        new ThreadPoolMetrics(executor, threadPoolType, threadPoolName, rejectCountFunc).bindTo(
            registry);
    }

    @Override
    public void bindTo(@NonNull MeterRegistry registry) {
        log.info("线程池类型【{}】线程池名称【{}】加入监控开始", threadPoolType, threadPoolNamePrefix);
        registerThreadPoolMetrics(registry);
        log.info("线程池类型【{}】线程池名称【{}】加入监控成功", threadPoolType, threadPoolNamePrefix);
    }

    private void registerThreadPoolMetrics(MeterRegistry meterRegistry) {
        // prometheus会将指标转为自己的命名风格：threadPoolType.thread.pool.core.size
        // 定义标签
        Tags tags = buildTags(threadPoolNamePrefix, threadPoolType);
        Gauge.builder(metricName("core.size"), executor, ThreadPoolExecutor::getCorePoolSize)
            .description(buildDesc("核心线程数")).baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        Gauge.builder(metricName("largest.size"), executor, ThreadPoolExecutor::getLargestPoolSize)
            .description(buildDesc("历史最高线程数")).baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        Gauge.builder(metricName("max.size"), executor, ThreadPoolExecutor::getMaximumPoolSize)
            .description(buildDesc("最大线程数")).baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        Gauge.builder(metricName("active.size"), executor, ThreadPoolExecutor::getActiveCount)
            .description(buildDesc("活跃线程数")).baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        Gauge.builder(metricName("thread.count"), executor, ThreadPoolExecutor::getPoolSize)
            .description(buildDesc("当前线程数")).baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        Gauge.builder(metricName("queue.size"), executor, e -> e.getQueue().size())
            .description(buildDesc("任务队列大小")).baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        FunctionCounter.builder(metricName("task.count"), executor, ThreadPoolExecutor::getTaskCount)
            .description(buildDesc("任务总量")).baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);

        FunctionCounter.builder(metricName("completed.task.count"), executor, ThreadPoolExecutor::getCompletedTaskCount)
            .baseUnit(BaseUnits.THREADS).description(buildDesc("已完成的任务量")).tags(tags).register(meterRegistry);

        buildRejectTaskCount(executor);
        FunctionCounter.builder(metricName("reject.task.count"), executor, this.rejectCountFunc)
            .baseUnit(BaseUnits.THREADS).description(buildDesc("已拒绝的任务量")).tags(tags).register(meterRegistry);

    }

    private String buildDesc(String desc) {
        return desc;
    }

    public void buildRejectTaskCount(ThreadPoolExecutor executor) {
        if (this.rejectCountFunc == null) {
            this.rejectCountFunc = value -> bindAndRetRejectTaskCount(executor).get();
        }
    }

    private static AtomicLong bindAndRetRejectTaskCount(ThreadPoolExecutor executor) {
        return EXECUTOR_TASK_REJECT_COUNT.computeIfAbsent(executor, threadPoolExecutor -> new AtomicLong(0));
    }

    /**
     * 累加当前线程池拒绝的任务数量
     *
     * @param executor - 线程池对象
     */
    public static void incrementRejectTaskCount(ThreadPoolExecutor executor) {
        bindAndRetRejectTaskCount(executor).incrementAndGet();
    }

    public static String metricName(String name) {
        return String.join(StrPool.DOT, DTP_METRIC_NAME_PREFIX, name);
    }

    public static Tags buildTags(String threadPoolName, String threadPoolType) {
        return Tags.of("thread.pool.name", threadPoolName).and("thread.pool.type", threadPoolType);
    }

}
