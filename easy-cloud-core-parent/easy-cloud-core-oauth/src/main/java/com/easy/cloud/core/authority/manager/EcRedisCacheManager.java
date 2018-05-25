package com.easy.cloud.core.authority.manager;

import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.cloud.core.cache.redis.handler.EcRedisTemplateHandler;
import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;

/**
 * 
 * <p>
 * 缓存管理类
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * @创建时间 2018年5月25日 下午6:28:04
 */
public class EcRedisCacheManager implements CacheManager {
	private static final Logger logger = LoggerFactory.getLogger(EcRedisCacheManager.class);

	// fast lookup by name map
	@SuppressWarnings("rawtypes")
	private final Map<String, Cache> caches = EcMapUtils.newConcurrentHashMap();

	/**
	 * The Redis key prefix for caches
	 */
	private String keyPrefix = "shiro_redis_cache:";

	/**
	 * Returns the Redis session keys prefix.
	 * 
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key prefix.
	 * 
	 * @param keyPrefix
	 *            The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		EcLogUtils.debug("cache的名称", name, logger);

		Cache<K, V> c = caches.get(name);

		if (c == null) {
			c = EcRedisTemplateHandler.get(name, Cache.class);
			// add it to the cache collection
			caches.put(name, c);
		}
		return c;
	}

}
