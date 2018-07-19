package com.easy.cloud.core.oauth.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author daiqi
 * @create 2018-07-19 16:29
 */
@Component(value = "amazon")
public class AmazonHealth implements HealthIndicator {
    @Override
    public Health health() {
        try {
            RestTemplate rest = new RestTemplate();
            rest.getForObject("http://www.amazon.com", String.class);
            return Health.up().withDetail("detail", "成功啦").build();
        } catch (Exception e) {
            return Health.down().build();
        }
    }
}
