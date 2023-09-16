package com.sika.code.monitor.core.http.invoke;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * microMeter统计dubbo consumer端耗时
 *
 * @author daiqi
 * @version 1.0
 * @date 2023/7/17 下午3:55
 */
public class HttpServerInvokeMetrics implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(HttpServerInvokeMetrics.class);

    private static final String MONITOR_FILTER_START_TIME = "monitor_filter_start_time";

    private final MeterRegistry meterRegistry;

    public HttpServerInvokeMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {

    }
}
