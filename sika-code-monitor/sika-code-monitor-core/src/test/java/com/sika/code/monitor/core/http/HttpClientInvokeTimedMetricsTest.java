package com.sika.code.monitor.core.http;

import com.sika.code.monitor.core.common.metrics.InvokeTimedMetrics;
import com.sika.code.monitor.core.common.util.MetricsUtil;
import io.micrometer.core.instrument.Tags;
import org.junit.jupiter.api.Test;

class HttpClientInvokeTimedMetricsTest {

    @Test
    public void testStartAndStop() throws InterruptedException {
        InvokeTimedMetrics invokeTimedMetrics =
                new InvokeTimedMetrics(null,"http.client.invoke.timed", "HTTP客户端方法执行耗时");
        Tags tags = Tags.of("domain", "domain").and("uri", "uri").and("method", "method.toUpperCase()");
        MetricsUtil.collectInvokeTimed( invokeTimedMetrics, tags, 10L);
    }

}