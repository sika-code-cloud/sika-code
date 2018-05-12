package com.easy.cloud.core.cache.redis.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.easy.cloud.core.basic.constant.EcBaseConfigConstant.EcConditionalSwitchKey;


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
		Environment env = context.getEnvironment();// 获取上下文环境
		Boolean redisSwitch = env.getProperty(EcConditionalSwitchKey.REDIS_SWITCH, Boolean.class);
		if (redisSwitch == null) {
			return true;
		}
		return redisSwitch;
	}

}
