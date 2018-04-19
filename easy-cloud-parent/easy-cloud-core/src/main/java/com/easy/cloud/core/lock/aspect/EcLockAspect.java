package com.easy.cloud.core.lock.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.lock.pojo.bo.EcLockBO;
import com.easy.cloud.core.lock.template.selector.EcLockTemplateSelector;

/**
 * 
 * <p>
 * 分布式锁切面类
 * </p>
 *
 * @author daiqi 创建时间 2018年4月12日 下午1:51:49
 */
@Aspect
@Order(value = 100) // 锁的优先级最高
@Component
public class EcLockAspect {
	@Autowired
	private EcLockTemplateSelector templateSelector;

	@Pointcut("@annotation(com.easy.cloud.core.lock.annotation.EcLockAnnotation)")
	public void distributedLockAspect() {

	}

	@Around(value = "distributedLockAspect()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		EcLockBO distributionLockBO = new EcLockBO(templateSelector, joinPoint);
		return distributionLockBO.buidDistributedLockDTOData().lock();
	}

	@AfterThrowing(value = "distributedLockAspect()", throwing = "ex")
	public void afterThrowing(Throwable ex) {
		throw new RuntimeException(ex);
	}

}
