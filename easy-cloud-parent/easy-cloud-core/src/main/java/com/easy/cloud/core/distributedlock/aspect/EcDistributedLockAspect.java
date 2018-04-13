package com.easy.cloud.core.distributedlock.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.distributedlock.pojo.bo.EcDistributedLockBO;
import com.easy.cloud.core.distributedlock.template.selector.EcDistributedLockTemplateSelector;

/**
 * 
 * <p>
 * 分布式锁切面类
 * </p>
 *
 * @author daiqi 创建时间 2018年4月12日 下午1:51:49
 */
@Aspect
@Order(value = Integer.MIN_VALUE) // 锁的优先级最高
@Component
public class EcDistributedLockAspect {
	@Autowired
	private EcDistributedLockTemplateSelector templateSelector;

	@Pointcut("@annotation(com.easy.cloud.core.distributionlock.annotation.EcDistributedLock)")
	public void distributedLockAspect() {
		
	}

	@Around(value = "distributedLockAspect()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			EcDistributedLockBO distributionLockBO = new EcDistributedLockBO(templateSelector, joinPoint);
			distributionLockBO.buidDistributedLockDTOData();
			return distributionLockBO.lock();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@AfterThrowing(value = "distributedLockAspect()", throwing = "ex")
	public void afterThrowing(Throwable ex) {
		throw new RuntimeException(ex);
	}

}
