package com.sika.code.common.threadlocal.filter;

import com.sika.code.basic.pojo.domain.BaseDomain;
import com.sika.code.common.threadlocal.manager.ThreadLocalManager;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * 清理ThreadLocal值的过滤器
 * <p>
 *
 * @author daiqi
 * @create 2019-06-23 8:56
 */
@Slf4j
public class ClearThreadLocalFilter implements Filter , BaseDomain {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } finally {
            ThreadLocalManager.removeAuto();
        }

    }

    @Override
    public void destroy() {

    }
}
