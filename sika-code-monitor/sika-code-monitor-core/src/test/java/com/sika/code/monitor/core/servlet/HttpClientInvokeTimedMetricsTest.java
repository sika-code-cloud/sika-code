package com.sika.code.monitor.core.servlet;

import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.invoke.config.InvokeTimedMetricsConfig;
import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import io.micrometer.core.instrument.Tags;
import org.junit.jupiter.api.Test;

class HttpClientInvokeTimedMetricsTest {

    @Test
    public void testStartAndStop() throws InterruptedException {
        InvokeTimedMetricsConfig invokeTimedMetricsConfig =
            new InvokeTimedMetricsConfig("http.client.invoke.timed", "HTTP客户端方法执行耗时");
        Tags tags = Tags.of("domain", "domain").and("uri", "uri").and("method", "method.toUpperCase()");
        new InvokeTimedMetrics(new LoadMetricsConfigManager(null)).collectInvokeTimed(null, invokeTimedMetricsConfig, tags, 10L);
    }

}