package com.easy.cloud.core.distributedlock.aspect;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.distributedlock.pojo.bo.EcDistributedLockBO;
import com.easy.cloud.core.distributedlock.template.selector.EcDistributedLockTemplateSelector;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.exception.dto.EcBaseServiceResultException;

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
public class EcDistributedLockAspect {
	@Autowired
	private EcDistributedLockTemplateSelector templateSelector;

	@Pointcut("@annotation(com.easy.cloud.core.distributedlock.annotation.EcDistributedLock)")
	public void distributedLockAspect() {
		
	}

	@Around(value = "distributedLockAspect()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
		EcDistributedLockBO distributionLockBO = new EcDistributedLockBO(templateSelector, joinPoint);
		return distributionLockBO.buidDistributedLockDTOData().lock();
	}

	@AfterThrowing(value = "distributedLockAspect()", throwing = "ex")
	public void afterThrowing(Throwable ex) {
		throw new RuntimeException(ex);
	}

}
