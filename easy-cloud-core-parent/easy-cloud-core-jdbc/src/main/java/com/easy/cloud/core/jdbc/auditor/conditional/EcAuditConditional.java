package com.easy.cloud.core.jdbc.auditor.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 
 * <p>
 * 审计条件注解，通过matches的返回值来开启或者关闭审计功能
 * </p>
 * <pre>
 * 当matches方法返回false则关闭审计功能，当matches为true则开启审计功能
 * </pre>
 *
 * @author daiqi
 * @创建时间 2018年5月5日 上午10:44:28
 */
public class EcAuditConditional implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return true;
	}

}
