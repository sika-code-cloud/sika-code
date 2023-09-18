package com.sika.code.monitor.core.rpc.dubbo.threadpool;

import com.sika.code.monitor.core.threadpool.enums.ThreadPoolTypeEnum;
import com.sika.code.monitor.core.threadpool.metrics.ThreadPoolMetrics;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.BaseUnits;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.threadpool.manager.DefaultExecutorRepository;
import org.apache.dubbo.common.threadpool.manager.ExecutorRepository;
import org.apache.dubbo.registry.integration.RegistryProtocol;
import org.apache.dubbo.registry.integration.RegistryProtocolListener;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.cluster.ClusterInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * dubbo线程池指标收集器
 *
 * @author : daiqi
 * @date : 2023-06-17
 */
@Activate
public class DubboThreadPoolMetricsBinder implements RegistryProtocolListener {
    private static final Map<Integer, DubboThreadPoolItem> DUBBO_THREAD_POOL_MAP = new ConcurrentHashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(DubboThreadPoolMetricsBinder.class);

    private static double thresholdRate = 0.75;

    private final MeterRegistry meterRegistry;

    public DubboThreadPoolMetricsBinder(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public DubboThreadPoolMetricsBinder(MeterRegistry meterRegistry, double thresholdRate) {
        this.meterRegistry = meterRegistry;
        DubboThreadPoolMetricsBinder.thresholdRate = thresholdRate;
    }

    public synchronized void init() {
        ConcurrentMap<Integer, ExecutorService> executorsMap = getExecutorMap();
        if (CollectionUtils.isEmpty(executorsMap)) {
            return;
        }
        // 会存在多个线程池-轮询注入-每个端口不一样
        for (Map.Entry<Integer, ExecutorService> entry : executorsMap.entrySet()) {
            ExecutorService executor = entry.getValue();
            if (!(executor instanceof ThreadPoolExecutor)) {
                continue;
            }
            Integer port = entry.getKey();
            // 当等于Max_VALUE为消费者线程-此时无需监控
            if (port.equals(Integer.MAX_VALUE) || DUBBO_THREAD_POOL_MAP.containsKey(port)) {
                continue;
            } else {
                DubboThreadPoolItem threadPoolItem = new DubboThreadPoolItem((ThreadPoolExecutor)entry.getValue());
                DUBBO_THREAD_POOL_MAP.put(port, threadPoolItem);
                registryThreadMetrics(threadPoolItem, entry.getKey());
            }
        }
    }

    private void registryThreadMetrics(DubboThreadPoolItem threadPoolItem, Integer port) {
        ThreadPoolExecutor executor = threadPoolItem.getThreadPoolExecutor();
        AtomicInteger thresholdActive = threadPoolItem.getThresholdActive();
        String threadPoolNameFull = buildThreadPoolNameFull(port);
        // 将其注入至线程池指标中
        ThreadPoolMetrics.monitor(meterRegistry, executor, ThreadPoolTypeEnum.DUBBO, threadPoolNameFull);
        // 绑定线程阈值
        Tags tags = ThreadPoolMetrics.buildTags(threadPoolNameFull, ThreadPoolTypeEnum.DUBBO.getName());
        Gauge.builder(ThreadPoolMetrics.metricName("threshold.active.size"), executor,
                value -> thresholdActive.get()).description("触发阈值的活跃线程数量")
            .baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);
        Gauge.builder(ThreadPoolMetrics.metricName("threshold.size"), executor,
                value -> executor.getMaximumPoolSize() * thresholdRate).description("线程阈值")
            .baseUnit(BaseUnits.THREADS).tags(tags).register(meterRegistry);
    }

    public static void recordActiveThread() {
        try {
            Map<Integer, DubboThreadPoolItem> executorServiceMap = DubboThreadPoolMetricsBinder.getDubboThreadPoolMap();
            if (executorServiceMap == null || executorServiceMap.isEmpty()) {
                return;
            }
            for (Map.Entry<Integer, DubboThreadPoolItem> entry : executorServiceMap.entrySet()) {
                ThreadPoolExecutor executor = entry.getValue().getThreadPoolExecutor();
                AtomicInteger thresholdActive = entry.getValue().getThresholdActive();
                Integer port = entry.getKey();
                int active = executor.getActiveCount();
                int threshold = (int)(executor.getMaximumPoolSize() * thresholdRate);
                if (active < threshold) {
                    if (thresholdActive.get() > 0) {
                        thresholdActive.set(0);
                    }
                } else {
                    LOGGER.warn(
                        "dubbo线程池运行告警：端口号【{}】，dubbo线程池活跃数量【{}】，大于最大阈值【{}】，dubbo线程池状况信息【{}】",
                        port, active, threshold, executor);
                    thresholdActive.set(active);
                }
            }
        } catch (Exception e) {
            LOGGER.error("dubbo线程池监控异常", e);
        }
    }

    private static String buildThreadPoolNameFull(Integer port) {
        return "dubbo.thread.pool" + "." + port;
    }

    public static Map<Integer, DubboThreadPoolItem> getDubboThreadPoolMap() {
        return DUBBO_THREAD_POOL_MAP;
    }

    /**
     * 获取线程池的Map对象 - key为端口，值为线程池对象
     *
     * @return ConcurrentMap
     */
    public ConcurrentMap<Integer, ExecutorService> getExecutorMap() {
        ExecutorRepository executorRepository =
            ExtensionLoader.getExtensionLoader(ExecutorRepository.class).getDefaultExtension();
        if (!(executorRepository instanceof DefaultExecutorRepository)) {
            return null;
        }
        try {
            DefaultExecutorRepository defaultExecutorRepository = (DefaultExecutorRepository)executorRepository;
            // 通过反射获取数据 - 从而拿到线程池对象
            Field dataFiled = defaultExecutorRepository.getClass().getDeclaredField("data");
            dataFiled.setAccessible(true);
            ConcurrentMap<String, ConcurrentMap<Integer, ExecutorService>> executorMap =
                (ConcurrentMap<String, ConcurrentMap<Integer, ExecutorService>>)dataFiled.get(
                    defaultExecutorRepository);
            return executorMap.get(CommonConstants.EXECUTOR_SERVICE_COMPONENT_KEY);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public static class DubboThreadPoolItem {
        private final ThreadPoolExecutor threadPoolExecutor;
        private final AtomicInteger thresholdActive;

        public DubboThreadPoolItem(ThreadPoolExecutor threadPoolExecutor) {
            this.threadPoolExecutor = threadPoolExecutor;
            this.thresholdActive = new AtomicInteger(0);
        }

        public ThreadPoolExecutor getThreadPoolExecutor() {
            return threadPoolExecutor;
        }

        public AtomicInteger getThresholdActive() {
            return thresholdActive;
        }
    }

    //    @Override
    //    public void onApplicationEvent(ServiceBeanExportedEvent event) {
    //        // 等dubbo某一个service export操作完毕后，会通知到这里，此时dubbo的线程池肯定也就初始化好了
    //        init();
    //    }

    @Override
    public void onExport(RegistryProtocol registryProtocol, Exporter<?> exporter) {
        // 当注册到远端服务后，此时dubbo的线程池肯定也就初始化好了
        init();
    }

    @Override
    public void onRefer(RegistryProtocol registryProtocol, ClusterInvoker<?> invoker, URL url) {
        //no comment
    }

    @Override
    public void onDestroy() {
        //no comment
    }
}