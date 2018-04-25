package com.easy.cloud.core.cache.redis.proxy;

import java.lang.reflect.Method;

import com.easy.cloud.core.basic.pojo.dto.EcBaseAspectDTO;
import com.easy.cloud.core.cache.redis.annotation.EcRedisAnnotation;
import com.easy.cloud.core.cache.redis.constant.EcRedisConstant.EcRedisActionType;
import com.easy.cloud.core.cache.redis.pojo.bo.EcRedisBO;
import com.easy.cloud.core.cache.redis.pojo.dto.EcRedisDTO;

/**
 * redis基础代理类---子类通过继承此类实现相关功能
 * 
 * @author daiqi
 * @date 2018年4月20日 下午10:49:57
 */
public abstract class EcBaseRedisProxy implements EcRedisProxy {
	private EcRedisDTO redisDTO;
	private EcRedisAnnotation redisAnnotation;
	private EcBaseAspectDTO baseAspectDTO;
	private Method targetMethod;
	private Class<?> targetClass;
	private EcRedisBO redisBO;

	@Override
	public Object handle(EcRedisBO redisBO) throws Throwable {
		// 1 数据初始化
		init(redisBO);
		// 执行处理
		return doHandle();
	}

	/** 数据初始化 */
	protected final void init(EcRedisBO redisBO) {
		this.redisBO = redisBO;
		this.redisDTO = redisBO.geRedisDTO();
		this.redisAnnotation = redisBO.getRedisAnnotation();
		this.targetClass = redisBO.getTargetClass();
		this.targetMethod = redisBO.getTargetMethod();
		this.baseAspectDTO = redisBO.getBaseAspectDTO();
	}

	/** 执行处理 */
	protected final Object doHandle() throws Throwable {
		switch (this.redisAnnotation.actionType()) {
		case QUERY:
			return query();
		case SAVE:
			return save();
		case UPDATE:
			return update();
		case DELETE:
			return delete();
		default:
			return redisBO.process();
		}
	}

	/** 查询 */
	protected abstract Object query();
	/** 更新 */
	protected abstract Object update();
	/** 保存 */
	protected abstract Object save();
	/** 删除 */
	protected abstract Object delete();
}
