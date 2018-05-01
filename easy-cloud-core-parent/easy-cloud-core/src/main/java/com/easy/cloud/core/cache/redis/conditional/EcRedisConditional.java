package com.easy.cloud.core.cache.redis.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * 
 * <p>
 * redis条件
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月27日 下午7:51:02
 */
public class EcRedisConditional implements Condition{
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return true;
	}

}
