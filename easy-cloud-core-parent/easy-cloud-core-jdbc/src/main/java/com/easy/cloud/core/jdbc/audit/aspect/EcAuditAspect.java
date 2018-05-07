package com.easy.cloud.core.jdbc.audit.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.basic.constant.EcBaseConfigConstant;
import com.easy.cloud.core.jdbc.audit.aware.EcAuditorAware;
import com.easy.cloud.core.jdbc.audit.conditional.EcAuditConditional;
import com.easy.cloud.core.jdbc.audit.pojo.bo.EcAuditBO;

/**
 * 
 * <p>
 * 审计切面
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年5月5日 上午10:43:03
 */
@Aspect
@Component
@Conditional(value = EcAuditConditional.class)
public class EcAuditAspect {
	@Autowired
	private EcAuditorAware<?> auditorAware;

	@Pointcut(EcBaseConfigConstant.DAO_EXECUTION)
	public void auditAspect() {

	}

	@Around(value = "auditAspect()")
	public Object doAround(ProceedingJoinPoint joinPoint) {
		EcAuditBO auditBO = new EcAuditBO();
		auditBO.buildAuditorData(joinPoint).buildAuditor(auditorAware.getCurrentAuditor());
		return auditBO.procced();
	}

	@AfterThrowing(value = "auditAspect()", throwing = "ex")
	public void afterThrowing(Throwable ex) {
		throw new RuntimeException(ex);
	}
}
