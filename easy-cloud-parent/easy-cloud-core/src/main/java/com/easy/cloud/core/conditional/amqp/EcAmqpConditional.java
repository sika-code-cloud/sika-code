package com.easy.cloud.core.conditional.amqp;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 
 * <p>
 * amqp条件类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月27日 下午7:51:38
 */
public class EcAmqpConditional implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return true;
	}


}
