package com.easy.cloud.monitor.server.adapter;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * <p>
 * webmvc配置适配器类
 * </p>
 *
 * @author daiqi
 * @date 2018/7/25 20:02
 * @return
 */
@Component
public class EcWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        //当此参数设置为true的时候，那么/user.html，/user.aa，/user.*都能是正常访问的。
        configurer.setUseSuffixPatternMatch(false);
    }
}