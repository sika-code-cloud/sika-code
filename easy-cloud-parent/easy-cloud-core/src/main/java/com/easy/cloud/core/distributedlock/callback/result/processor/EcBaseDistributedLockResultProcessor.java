package com.easy.cloud.core.distributedlock.callback.result.processor;

import org.aspectj.lang.ProceedingJoinPoint;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.distributedlock.callback.result.EcDistributedLockResult;
import com.easy.cloud.core.distributedlock.constant.error.EcDistributedLockErrorCodeEnum;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;

/**
 * 
 * <p>
 * 分布式锁的结果处理器基础类
 * </p>
 *
 * <pre>
 *  说明：分布式锁结果处理类继承该对象
 *  约定：所有继承该对象的结果处理类必须提供默认的构造函数
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * @创建时间    2018年4月14日 上午11:49:30
 */
public abstract class EcBaseDistributedLockResultProcessor {

	/**
	 * 
	 * <p>
	 * 处理类
	 * </p>
	 * 
	 * @param pjp
	 * @param distributedLockResult
	 * @return Object
	 * @author daiqi
	 * @创建时间 2018年4月14日 上午11:08:03
	 */
	public final Object proceed(ProceedingJoinPoint pjp, EcDistributedLockResult distributedLockResult) {
		if (EcBaseUtils.isNull(distributedLockResult)) {
			throw new EcBaseBusinessException(EcDistributedLockErrorCodeEnum.LOCK_RESULT_CANT_NULL); 
		}
		if (distributedLockResult.isGainLock()) {
			return success(pjp, distributedLockResult);
		} else {
			return fail(pjp, distributedLockResult);
		}
	}
	
	/** 执行Proceed */
	protected Object doProceed(ProceedingJoinPoint pjp){
		try {
			return pjp.proceed();
		} catch (Throwable throwable) {
			throw new RuntimeException(throwable);
		}
	}

	/**
	 * 
	 * <p>
	 * 分布式锁成功方法
	 * </p>
	 *
	 * @param pjp
	 * @param distributedLockResult
	 * @return
	 * @author daiqi
	 * @创建时间 2018年4月14日 上午10:31:40
	 */
	protected abstract Object success(ProceedingJoinPoint pjp, EcDistributedLockResult distributedLockResult);

	/**
	 * 
	 * <p>
	 * 分布式锁失败方法
	 * </p>
	 *
	 * @param pjp
	 * @param distributedLockResult
	 * @return
	 * @author daiqi
	 * @创建时间 2018年4月14日 上午10:31:40
	 */
	protected abstract Object fail(ProceedingJoinPoint pjp, EcDistributedLockResult distributedLockResult);

}
