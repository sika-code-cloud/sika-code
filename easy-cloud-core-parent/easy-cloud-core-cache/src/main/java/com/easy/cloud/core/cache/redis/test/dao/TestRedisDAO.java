package com.easy.cloud.core.cache.redis.test.dao;

import com.easy.cloud.core.cache.redis.annotation.EcRedisAnnotation;
import com.easy.cloud.core.cache.redis.constant.EcRedisConstant.EcRedisActionType;
import com.easy.cloud.core.cache.redis.proxy.demo.EcRedisDemoProxy;
import com.easy.cloud.core.common.log.pojo.dto.EcLogDTO;

public interface TestRedisDAO{
	@EcRedisAnnotation(actionType = EcRedisActionType.QUERY, proxyClass = EcRedisDemoProxy.class)
	EcLogDTO queryRequestPath(String id);
}
