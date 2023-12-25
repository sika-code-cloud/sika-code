package com.sika.code.demo.interfaces.common.configure;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import com.sika.code.migrate.interceptor.MigrateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author daiqi
 * @create 2021-11-17 22:58
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    @Autowired
    private MigrateInterceptor migrateInterceptor;

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TraceIdInterceptor());
        // 注册Sa-Token的路由拦截器
//        registry.addInterceptor(new SaRouteInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/**", "/auth/login", "/test/login", "/**/anon", "/user/**","/**/favicon.ico", "/xrebel");
        //addPathPatterns拦截的路径
        String[] addPathPatterns = {
                "/auth/**"
        };
        //excludePathPatterns排除的路径
        String[] excludePathPatterns = {"/auth/error"};
        //创建用户拦截器对象并指定其拦截的路径和排除的路径
        registry.addInterceptor(migrateInterceptor).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }

    // 获取配置Bean (以代码的方式配置Sa-Token, 此配置会覆盖yml中的配置)
    @Bean
    @Primary
    public SaTokenConfig getSaTokenConfigPrimary() {
        SaTokenConfig config = new SaTokenConfig();
        // token名称 (同时也是cookie名称)
        config.setTokenName("sikaToken");
        // token有效期，单位s 默认30天
        config.setTimeout(30 * 24 * 60 * 60);
        // token临时有效期 (指定时间内无操作就视为token过期) 单位: 秒
        config.setActivityTimeout(1800);
        // 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)
        config.setIsConcurrent(true);
        // 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)
        config.setIsShare(true);
        config.setMaxLoginCount(-1);
        // token风格
        config.setTokenStyle("random-128");
        // 是否输出操作日志
        config.setIsLog(true);
        config.setJwtSecretKey("asdasdasifhueuiwyurfewbfjsdafjk");
        return config;
    }

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }

    @Bean
    public SaTokenListenerCustomer saTokenListenerCustomer() {
        return new SaTokenListenerCustomer();
    }
}