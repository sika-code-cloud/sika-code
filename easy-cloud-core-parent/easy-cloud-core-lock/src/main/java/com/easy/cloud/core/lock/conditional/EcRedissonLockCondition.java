package com.easy.cloud.core.lock.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.easy.cloud.core.basic.constant.EcBaseConfigConstant.EcConditionalSwitchKey;

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
		Environment env = context.getEnvironment();// 获取上下文环境
		Boolean lockSwitch = env.getProperty(EcConditionalSwitchKey.LOCK_SWITCH, Boolean.class);
		if (lockSwitch == null) {
			return true;
		}
		return lockSwitch;
	}

}
