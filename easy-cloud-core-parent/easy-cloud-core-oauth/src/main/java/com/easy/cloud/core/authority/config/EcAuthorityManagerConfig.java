package com.easy.cloud.core.authority.config;

import com.easy.cloud.core.authority.manager.EcSessionManager;
import com.easy.cloud.core.authority.realm.EcAuthorityRealm;
import com.easy.cloud.core.cache.redis.config.EcRedisProperties;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daiqi
 * @create 2018-06-27 10:02
 */
@Configuration
public class EcAuthorityManagerConfig {
    @Value("${ec.redis.hostName}")
    private String host;
    @Value("${ec.authority.redis.port}")
    private int port;
    @Value("${ec.authority.redis.timeout}")
    private int timeout;
    @Value("${ec.authority.redis.password}")
    private String password;
    @Autowired
    private EcRedisProperties redisProperties;

    /**
     * <p>
     * 配置加密管理者
     * </p>
     *
     * @param authorityRealm
     * @param redisSessionDAO
     * @return org.apache.shiro.mgt.SecurityManager
     * @author daiqi
     * @date 2018/6/27 10:39
     */
    @Bean
    public SecurityManager securityManager(EcAuthorityRealm authorityRealm, SessionDAO redisSessionDAO) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authorityRealm);
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager(redisSessionDAO));
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /**
     * 自定义sessionManager
     */
    public SessionManager sessionManager(SessionDAO redisSessionDAO) {
        EcSessionManager sessionManager = new EcSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }

    /**
     * <p>
     * 配置shiro redisManager
     * </p>
     * <pre>
     *     使用的是shiro-redis开源插件
     * </pre>
     *
     * @param
     * @return org.crazycake.shiro.RedisManager
     * @author daiqi
     * @date 2018/6/27 10:39
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        // 配置缓存过期时间
        redisManager.setTimeout(timeout);
        if (EcStringUtils.isNotEmpty(password)) {
            redisManager.setPassword(password);
        }
        redisManager.setJedisPoolConfig(redisProperties);
        return redisManager;
    }

    /**
     * <p>
     * cacheManager 缓存 redis实现
     * </p>
     * <pre>
     *     使用的是shiro-redis开源插件
     * </pre>
     *
     * @param
     * @return org.crazycake.shiro.RedisCacheManager
     * @author daiqi
     * @date 2018/6/27 10:38
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

}
