package com.easy.cloud.core.cache.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPoolConfig;

@Component
@ConfigurationProperties(prefix="ec.redis")
public class EcRedisProperties extends JedisPoolConfig{

}
