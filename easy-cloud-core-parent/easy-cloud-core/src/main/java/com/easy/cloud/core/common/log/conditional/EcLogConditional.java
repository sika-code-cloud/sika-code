package com.easy.cloud.core.common.log.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.easy.cloud.core.basic.constant.EcBaseConfigConstant.EcConditionalSwitchKey;

/**
 * 
 * <p>
 * 日志条件注解
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年5月7日 下午5:11:01
 */
public class EcLogConditional implements Condition{

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Environment env = context.getEnvironment();// 获取上下文环境
		Boolean ecLogSwitch = env.getProperty(EcConditionalSwitchKey.LOG_SWITCH, Boolean.class);
		if (ecLogSwitch == null) {
			return true;
		}
		return ecLogSwitch;
	}

}
