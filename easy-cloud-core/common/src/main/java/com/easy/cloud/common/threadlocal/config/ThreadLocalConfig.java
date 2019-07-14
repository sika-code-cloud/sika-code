package com.easy.cloud.common.threadlocal.config;

import com.easy.cloud.common.threadlocal.filter.ClearThreadLocalFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * 配置类
 *
 * @author daiqi
 * @create 2019-06-23 8:56
 */
@Configuration
public class ThreadLocalConfig {

    /**
     * <p>
     * 清理ThreadLocal的过滤器
     * </p>
     *
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean
     * @author daiqi
     * @date 2019/6/23 9:14
     */
    @Bean
    public FilterRegistrationBean clearThreadLocalFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new ClearThreadLocalFilter());
        bean.setName("clearThreadLocalFilter");
        bean.addUrlPatterns("/*");
        // 使用注解的order没有效果
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
