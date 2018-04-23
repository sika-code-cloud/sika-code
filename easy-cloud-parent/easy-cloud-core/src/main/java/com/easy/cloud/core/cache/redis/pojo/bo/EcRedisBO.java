package com.easy.cloud.core.cache.redis.pojo.bo;

import org.aspectj.lang.ProceedingJoinPoint;

import com.easy.cloud.core.basic.pojo.bo.EcBaseAspectBO;
import com.easy.cloud.core.cache.redis.annotation.EcRedisAnnotation;
import com.easy.cloud.core.cache.redis.pojo.dto.EcRedisDTO;

/**
 * redis业务逻辑类
 * 
 * @author daiqi
 * @date 2018年4月20日 下午10:48:05
 */
public class EcRedisBO extends EcBaseAspectBO {
	private EcRedisDTO redisDTO;
	private EcRedisAnnotation redisAnnotation;

	public EcRedisBO(EcRedisDTO redisDTO) {
		this.redisDTO = redisDTO;
	}

	public EcRedisBO buildRedisData(ProceedingJoinPoint pjp) {
		super.buildBaseAspectData(pjp);
		this.buildRedisAnnotation();
		return this;
	}

	private EcRedisBO buildRedisAnnotation() {
		this.redisAnnotation = targetMethod.getAnnotation(EcRedisAnnotation.class);
		return this;
	}

	public EcRedisAnnotation getRedisAnnotation() {
		return redisAnnotation;
	}

}
