package com.easy.cloud.core.cache.redis.proxy;

import com.easy.cloud.core.cache.redis.annotation.EcRedisAnnotation;
import com.easy.cloud.core.cache.redis.pojo.bo.EcRedisBO;

/**
 * redis基础代理类---子类通过继承此类实现相关功能
 * 
 * @author daiqi
 * @date 2018年4月20日 下午10:49:57
 */
public abstract class EcBaseRedisProxy implements EcRedisProxy {
	protected EcRedisBO redisBO;
	protected EcRedisAnnotation redisAnnotation;

	@Override
	public final Object handle(EcRedisBO redisBO) {
		// 1 数据初始化
		init(redisBO);
		// 执行处理
		return doHandle();
	}

	/** 数据初始化 */
	protected final void init(EcRedisBO redisBO) {
		initCoreData(redisBO);
		initOtherData();
	}

	/** 初始化核心数据 */
	private final void initCoreData(EcRedisBO redisBO) {
		this.redisBO = redisBO;
		this.redisAnnotation = redisBO.getRedisAnnotation();
	}

	/** 初始化其他数据---子类可以通过重写此方法来初始化自己的数据 */
	protected void initOtherData() {

	}

	/** 执行处理 */
	protected final Object doHandle() {
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
			return redisBO.proceed();
		}
	}
	
	/** 查询---子类通过重写此方法实现查询业务逻辑 */
	protected Object query() {
		return redisBO.proceed();
	}

	/** 更新---子类通过重写此方法实现更新业务逻辑 */
	protected Object update() {
		return redisBO.proceed();
	}

	/** 保存---子类通过重写此方法实现保存业务逻辑 */
	protected Object save() {
		return redisBO.proceed();
	}

	/** 删除---子类通过重写此方法实现删除业务逻辑 */
	protected Object delete() {
		return redisBO.proceed();
	}
	
}
