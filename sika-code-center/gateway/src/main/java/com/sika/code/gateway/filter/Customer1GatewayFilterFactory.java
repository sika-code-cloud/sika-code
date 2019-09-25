package com.sika.code.gateway.filter;

import com.sika.code.common.json.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

/**
 * 自定义过滤器
 *
 * @author daiqi
 * @create 2019-08-26 22:59
 */
@Slf4j
public class Customer1GatewayFilterFactory extends AbstractGatewayFilterFactory<Customer1GatewayFilterFactory.Config> {

    public Customer1GatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        log.info("Config{}", JSONUtil.toJSONString(config));
        return (exchange, chain) -> {
            log.info("exchange1111：{}", JSONUtil.toJSONString(exchange.getAttributes()));
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}
