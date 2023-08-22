package com.sika.code.monitor.core.http;

import com.sika.code.monitor.core.common.InvokeTimedMetrics;
import com.sika.code.monitor.core.common.MetricsUtil;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * dubbo请求耗时指标
 * <pre>
 *     1. HttpTimedMetrics httpTimedMetrics = HttpTimedMetrics.start(register, ...)
 *     2. httpTimedMetrics.stop(register)
 * </pre>
 *
 * @author : daiqi
 * @date : 2023-06-19
 */
public class HttpClientInvokeTimedMetrics extends InvokeTimedMetrics {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientInvokeTimedMetrics.class);
    /** 请求域名前缀 */
    private final String domain;
    /** 请求路径 */
    private final String uri;
    /** 请求方式 - post/get/put等 */
    private final String method;

    public HttpClientInvokeTimedMetrics(String domain, String uri, String method) {
        this.domain = domain;
        this.uri = MetricsUtil.buildSimplifyClassOrUriName(uri);
        this.method = method;
    }

    @Override
    public Tags getMetricsTags() {
        return Tags.of("domain", domain).and("uri", uri).and("method", method.toUpperCase());
    }

    @Override
    public String getMetricsDescription() {
        return "HTTP客户端方法执行耗时";
    }

    @Override
    public String getMetricsName() {
        return "http.client.invoke.timed";
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

}
