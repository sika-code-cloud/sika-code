package com.easy.cloud.core.jdbc.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.easy.cloud.core.basic.constant.EcBaseConfigConstant.EcConditionalSwitchKey;

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
		Environment env = context.getEnvironment();// 获取上下文环境
		Boolean jdbcSwitch = env.getProperty(EcConditionalSwitchKey.JDBC_SWITCH, Boolean.class);
		if (jdbcSwitch == null) {
			return true;
		}
		return jdbcSwitch;
	}

}
