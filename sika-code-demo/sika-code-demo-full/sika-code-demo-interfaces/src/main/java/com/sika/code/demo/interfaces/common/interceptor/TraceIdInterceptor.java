package com.sika.code.demo.interfaces.common.interceptor;

import com.sika.code.core.log.util.LogUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author daiqi
 * @create 2021-11-17 23:03
 */
public class TraceIdInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LogUtil.putTraceId();
        return true;
    }
}
