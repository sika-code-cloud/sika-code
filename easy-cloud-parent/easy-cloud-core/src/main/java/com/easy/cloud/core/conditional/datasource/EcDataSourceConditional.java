package com.easy.cloud.core.conditional.datasource;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 
 * <p>
 * 数据元条件
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月27日 下午7:51:56
 */
public class EcDataSourceConditional implements Condition{

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return false;
	}

}
