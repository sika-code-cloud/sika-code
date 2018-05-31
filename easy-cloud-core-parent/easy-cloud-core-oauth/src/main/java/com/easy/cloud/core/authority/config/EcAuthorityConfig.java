package com.easy.cloud.core.authority.config;

import java.io.IOException;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.crazycake.shiro.SerializeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import com.easy.cloud.core.authority.manager.EcRedisManager;
import com.easy.cloud.core.authority.manager.EcSessionManager;
import com.easy.cloud.core.authority.realm.EcAuthorityRealm;
import com.easy.cloud.core.cache.redis.config.EcRedisProperties;

/**
 * 
 * <p>
 * 权限配置类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年5月25日 下午2:15:56
 */
@Configuration
@PropertySource({ "classpath:config/redis-default.properties" })
public class EcAuthorityConfig {
	@Value("${ec.redis.hostName}")
	private String host;
	@Value("${ec.redis.port}")
	private int port;
	@Value("${ec.redis.timeout}")
	private int timeout;
	@Value("${ec.redis.password}")
	private String password;

	@Autowired
	private EcRedisProperties redisProperties;
	
	@Value("classpath:config/shiro-filter.yml")
	private Resource shiroConfig;

	/** 将配置文件的属性设置到ShiroFilterFactoryBean对象中 */
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		Yaml yaml = new Yaml();
		ShiroFilterFactoryBean shiroFilterFactoryBean = null;
		try {
			shiroFilterFactoryBean = yaml.loadAs(shiroConfig.getInputStream(), ShiroFilterFactoryBean.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return shiroFilterFactoryBean;
	}

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = shiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		return shiroFilterFactoryBean;
	}

	/**
	 * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 ）
	 * 
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于
														// md5(md5(""));
		return hashedCredentialsMatcher;
	}

	@Bean
	public EcAuthorityRealm authorityRealm() {
		EcAuthorityRealm myShiroRealm = new EcAuthorityRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(authorityRealm());
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());
		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(cacheManager());
		return securityManager;
	}

	// 自定义sessionManager
	@Bean
	public SessionManager sessionManager() {
		EcSessionManager mySessionManager = new EcSessionManager();
		mySessionManager.setSessionDAO(redisSessionDAO());
		return mySessionManager;
	}

	/**
	 * 配置shiro redisManager
	 * <p>
	 * 使用的是shiro-redis开源插件
	 * 
	 * @return
	 */
	public RedisManager redisManager() {
		EcRedisManager redisManager = new EcRedisManager(redisProperties);
		redisManager.setHost(host);
		redisManager.setPort(port);
		// 配置缓存过期时间
		redisManager.setExpire(1800);
		redisManager.setTimeout(timeout);
		redisManager.setPassword(password);
		redisManager.init();
		return redisManager;
	}

	/**
	 * cacheManager 缓存 redis实现
	 * <p>
	 * 使用的是shiro-redis开源插件
	 * 
	 * @return
	 */
	@Bean
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis
	 * <p>
	 * 使用的是shiro-redis开源插件
	 */
	@Bean
	public SessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	public static void main(String[] args) {
		EcRedisManager redisManager = new EcRedisManager();
		redisManager.setHost("120.78.74.169");
		redisManager.setPort(6379);
		byte[] b = redisManager.get("d353c7b9-7425-4818-a4b7-5641183074a7".getBytes());
		Session s = (Session) SerializeUtils.deserialize(b);
		System.out.println(s.getId());
	}
}
