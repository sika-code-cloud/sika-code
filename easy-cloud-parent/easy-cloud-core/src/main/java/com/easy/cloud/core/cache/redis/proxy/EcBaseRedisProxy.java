package com.easy.cloud.core.cache.redis.proxy;

import java.lang.reflect.Method;

import com.easy.cloud.core.basic.pojo.dto.EcBaseAspectDTO;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.cache.redis.annotation.EcRedisAnnotation;
import com.easy.cloud.core.cache.redis.pojo.bo.EcRedisBO;
import com.easy.cloud.core.cache.redis.pojo.dto.EcRedisDTO;
import com.easy.cloud.core.common.array.EcArrayUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;

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
	
	/** 获取目标方法执行的返回值---转换为范型class对应的对象 */
	protected final <T> T getTReturnValue(Class<T> clazz) {
		if (EcBaseUtils.isNull(clazz)) {
			return null;
		}
		Object returnValue = redisBO.proceed();
		return EcJSONUtils.parseObject(returnValue, clazz);
	}
	/**
	 * 
	 * <p>获取方法参数中范型class对应的值</p>
	 *
	 * <pre>若方法行参列表存在多个同类型的参数则默认取第一个</pre>
	 *
	 * @param clazz
	 * @return
	 *
	 * @author daiqi
	 * @创建时间  2018年4月26日 下午11:46:47
	 */
	protected final <T> T getTParam(Class<T> clazz) {
		if (EcBaseUtils.isNull(clazz)) {
			return null;
		}
		Object [] args = redisBO.getBaseAspectDTO().getTargetParameterValues();
		if (EcArrayUtils.isEmpty(args)) {
			return null;
		}
		for (Object arg : args) {
			if (EcBaseUtils.isNull(arg)) {
				continue;
			}
			if (EcBaseUtils.equals(arg.getClass(), clazz)) {
				return EcJSONUtils.parseObject(arg, clazz);
			}
		}
		return null;
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
