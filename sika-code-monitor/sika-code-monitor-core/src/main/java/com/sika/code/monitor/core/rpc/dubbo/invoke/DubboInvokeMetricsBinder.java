package com.sika.code.monitor.core.rpc.dubbo.invoke;

import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER_SIDE;
import static org.apache.dubbo.common.constants.CommonConstants.SIDE_KEY;

/**
 * microMeter统计dubbo consumer端耗时
 *
 * @author daiqi
 * @version 1.0
 * @date 2023/7/17 下午3:55
 */
@Activate(group = {CommonConstants.CONSUMER, CommonConstants.PROVIDER})
public class DubboInvokeMetricsBinder implements Filter, Filter.Listener {

    private static final Logger logger = LoggerFactory.getLogger(DubboInvokeMetricsBinder.class);

    private static final String MONITOR_FILTER_START_TIME = "monitor_filter_start_time";

    private final MeterRegistry meterRegistry;
    private final InvokeTimedMetrics invokeTimedMetrics;

    public DubboInvokeMetricsBinder(MeterRegistry meterRegistry, InvokeTimedMetrics invokeTimedMetrics) {
        this.meterRegistry = meterRegistry;
        this.invokeTimedMetrics = invokeTimedMetrics;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        invocation.put(MONITOR_FILTER_START_TIME, System.nanoTime());
        return invoker.invoke(invocation);
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        collect(invoker, invocation);
    }

    @Override
    public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {
        logger.error("ConsumerMonitorFilter call onError occurred for service={} method={}",
            invoker.getUrl().toServiceString(), invocation.getMethodName(), t);
        collect(invoker, invocation);
    }

    private void collect(Invoker<?> invoker, Invocation invocation) {
        Object obj = invocation.get(MONITOR_FILTER_START_TIME);
        if (obj != null) {
            long startTime = (long)obj;
            long elapsed = System.nanoTime() - startTime;
            Class<?> serviceClass = invoker.getInterface();
            // 方法名
            String methodName = invocation.getMethodName();
            // 参数类型
            Class<?>[] parameterTypes = invocation.getParameterTypes();
            if (CONSUMER_SIDE.equals(invoker.getUrl().getParameter(SIDE_KEY))) {
                invokeTimedMetrics.collectDubboClientInvokeTimed(meterRegistry, serviceClass, methodName,
                    parameterTypes, elapsed);
            } else {
                invokeTimedMetrics.collectDubboServerInvokeTimed(meterRegistry, serviceClass, methodName,
                    parameterTypes, elapsed);
            }
        }
    }
}
