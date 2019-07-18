package com.sika.code.standard.request.filter;

import com.sika.code.basic.pojo.domain.BaseDomain;
import com.sika.code.standard.request.manager.HttpRequestManager;
import org.springframework.http.HttpHeaders;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 请求过滤器
 *
 * @author daiqi
 * @create 2019-07-05 13:21
 */
public class HttpRequestFilter implements Filter, BaseDomain {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpHeaders httpHeaders = HttpRequestManager.convertRequestHeader((HttpServletRequest) request);
            HttpRequestManager.addHttpHeadersToLocal(httpHeaders);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
