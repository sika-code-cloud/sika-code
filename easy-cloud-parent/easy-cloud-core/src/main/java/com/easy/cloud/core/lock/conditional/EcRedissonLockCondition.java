package com.easy.cloud.core.lock.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 
 * <p>
 * 开启redisson锁的条件类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年4月16日 上午9:56:25
 */
public class EcRedissonLockCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return true;
	}

}
