package com.easy.cloud.common.threadlocal.filter;

import com.easy.cloud.basic.pojo.domain.BaseDomain;
import com.easy.cloud.common.threadlocal.manager.ThreadLocalManager;
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
            // 手动清理标志为true，则不清理
            if (!ThreadLocalManager.getManualCleanFromThreadLocal()) {
                ThreadLocalManager.removeThreadLocal();
            }
            // 手动清理标志为true，则不清理
            if (!ThreadLocalManager.getManualCleanFromInheritable()) {
                ThreadLocalManager.removeInheritable();
            }
        }

    }

    @Override
    public void destroy() {

    }
}
