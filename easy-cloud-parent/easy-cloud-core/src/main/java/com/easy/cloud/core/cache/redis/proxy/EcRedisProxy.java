package com.easy.cloud.core.cache.redis.proxy;

import com.easy.cloud.core.cache.redis.pojo.bo.EcRedisBO;

/**
 * 处理redis逻辑得代理接口类
 * @author daiqi
 * @date 2018年4月20日 下午9:31:49
 */
public interface EcRedisProxy {
	Object handle(EcRedisBO redisBO);
}
