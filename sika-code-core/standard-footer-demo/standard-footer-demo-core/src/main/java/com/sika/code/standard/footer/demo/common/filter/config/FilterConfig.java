package com.sika.code.standard.footer.demo.common.filter.config;

import com.sika.code.standard.footer.demo.common.filter.FirstFilter;
import com.sika.code.standard.footer.demo.common.filter.FourFilter;
import com.sika.code.standard.footer.demo.common.filter.SecondFilter;
import com.sika.code.standard.footer.demo.common.filter.ThreeFilter;
import com.google.common.collect.Lists;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author daiqi
 * @create 2019-06-23 0:18
 */
@Configuration
public class FilterConfig {
//    @Bean
    public FilterRegistrationBean secondFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new SecondFilter());
        List<String> urls = Lists.newArrayList();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

//    @Bean
    public FilterRegistrationBean threeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new ThreeFilter());
        List<String> urls = Lists.newArrayList();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        filterRegistrationBean.setOrder(3);
        return filterRegistrationBean;
    }

//    @Bean
    public FilterRegistrationBean fourFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new FourFilter());
        List<String> urls = Lists.newArrayList();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        filterRegistrationBean.setOrder(4);
        return filterRegistrationBean;
    }

//    @Bean
    public FilterRegistrationBean firstFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new FirstFilter());
        List<String> urls = Lists.newArrayList();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

}
