package com.easy.cloud.core.cache.redis.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.basic.constant.EcBaseConfigConstant;
import com.easy.cloud.core.cache.redis.pojo.bo.EcRedisBO;

/**
 * 
 * <p>
 * redis切面类
 * </p>
 *
 * @author daiqi 创建时间 2018年4月12日 下午1:51:49
 */
@Aspect
@Order
@Component
public class EcRedisAspect {

	@Pointcut("@annotation(" + EcBaseConfigConstant.REDIS_ANNOTATION_NAME + ")")
	public void redisAspect() {

	}

	@Pointcut(EcBaseConfigConstant.DAO_EXECUTION)
	public void daoAspect() {

	}

	@Around(value = "redisAspect()")
	public Object annotationAround(ProceedingJoinPoint joinPoint) {
		return doAround(joinPoint);
	}

	@Around(value = "daoAspect()")
	public Object daoAround(ProceedingJoinPoint joinPoint) {
		return doAround(joinPoint);
	}

	public Object doAround(ProceedingJoinPoint joinPoint) {
		EcRedisBO redisBO = new EcRedisBO();
		redisBO.buildRedisData(joinPoint);
		return redisBO.handle();
	}

	@AfterThrowing(value = "redisAspect()", throwing = "ex")
	public void afterThrowing(Throwable ex) {
		throw new RuntimeException(ex);
	}
}
