package com.sika.code.demo.interfaces.common.filter;

import com.sika.code.demo.interfaces.common.filter.wrapper.CacheRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 缓存过滤器
 *
 * @author Lion Li
 */
public class CacheRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        // 判断是否为json请求
        if (request instanceof HttpServletRequest
                && StringUtils.startsWithIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
            // 创建缓存装饰器
            requestWrapper = new CacheRequestWrapper((HttpServletRequest) request, response);
        }
        if (null == requestWrapper) {
            chain.doFilter(request, response);
        } else {
            // 对下面的链路传递缓存装饰器
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy() {

    }
}