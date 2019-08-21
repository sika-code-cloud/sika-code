package com.sika.code.config.cors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;

/**
 * <p>
 * 跨域支持
 * </p>
 *
 * @author daiqi
 * @date 2018/11/28 19:08
 */
@Configuration
@ConditionalOnClass(Filter.class)
public class CorsConfig {
    /**
     * 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
     */
    private static final long MAX_AGE = 180000L;

    /**
     * 跨域支持
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        // 允许cookies跨域
        config.setAllowCredentials(true);
        // #允许向该服务器提交请求的URI，*表示全部允许
        config.addAllowedOrigin("*");
        // #允许访问的头信息,*表示全部
        config.addAllowedHeader("*");
        // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.setMaxAge(MAX_AGE);
        // 允许提交请求的方法，*表示全部允许
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
