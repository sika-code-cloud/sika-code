package com.sika.code.monitor.core.http;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class HttpClientInvokeTimedMetricsTest {

    @Test
    public void testStartAndStop() throws InterruptedException {
        HttpClientInvokeTimedMetrics invokeTimedMetrics =
                new HttpClientInvokeTimedMetrics("111", "11", "22");
        invokeTimedMetrics.start(null);
        TimeUnit.SECONDS.sleep(1);
        invokeTimedMetrics.stop(null);
    }

    @Test
    public void testStartAndStop1() {
        HttpClientInvokeTimedMetrics invokeTimedMetrics =
                new HttpClientInvokeTimedMetrics("111", "11", "22");
        invokeTimedMetrics.record(null, 10L);
    }
}