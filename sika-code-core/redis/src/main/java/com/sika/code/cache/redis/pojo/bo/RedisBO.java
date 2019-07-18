package com.sika.code.cache.redis.pojo.bo;

import com.sika.code.cache.redis.annotation.RedisAnnotation;
import com.sika.code.cache.redis.proxy.RedisProxy;
import com.sika.code.cache.redis.selector.RedisProxySelector;
import com.sika.code.basic.pojo.bo.BaseAspectBO;
import com.sika.code.cache.redis.pojo.dto.RedisDTO;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * redis业务逻辑类
 * 
 * @author daiqi
 * @date 2018年4月20日 下午10:48:05
 */
public class RedisBO extends BaseAspectBO {
	/** redis数据传输对象 */
	private RedisDTO redisDTO;
	/** redis注解对象 */
	private RedisAnnotation redisAnnotation;
	/** redis代理类选择器 */
	private RedisProxySelector redisProxySelector;

	public RedisBO() {

	}

	public RedisBO(RedisDTO redisDTO) {
		this.redisDTO = redisDTO;
	}

	/** 构建redis传输数据 */
	public RedisBO buildRedisData(ProceedingJoinPoint pjp) {
		super.buildBaseAspectData(pjp);
		this.buildRedisAnnotation();
		return this;
	}

	/** redis切面通过调用此方法进行对redis进行处理 */
	public Object handle() {
		if (this.redisAnnotation == null) {
			return super.proceed();
		}
		Class<? extends RedisProxy> handleClass = redisAnnotation.proxyClass();
		RedisProxy redisProxy = RedisProxySelector.getProxy(handleClass);
		if (redisProxy == null) {
			return super.proceed();
		}
		try {
			return redisProxy.handle(this);
		} catch (Exception e) {
			e.printStackTrace();
			return super.proceed();
		}
	}

	private RedisBO buildRedisAnnotation() {
		this.redisAnnotation = super.targetMethod.getAnnotation(RedisAnnotation.class);
		return this;
	}

	public RedisBO buildRedisProxySelector(RedisProxySelector redisProxySelector) {
		this.redisProxySelector = redisProxySelector;
		return this;
	}

	public RedisAnnotation getRedisAnnotation() {
		return redisAnnotation;
	}

	public RedisDTO geRedisDTO() {
		return this.redisDTO;
	}
}
