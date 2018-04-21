package com.easy.cloud.core.cache.redis.proxy;

import com.easy.cloud.core.cache.redis.pojo.bo.EcRedisBO;

/**
 * redis基础代理类---子类通过继承此类实现相关功能
 * @author daiqi
 * @date 2018年4月20日 下午10:49:57
 */
public abstract class EcBaseRedisProxy implements EcRedisProxy{

	@Override
	public Object handle(EcRedisBO redisBO) {
		return null;
	}

}
