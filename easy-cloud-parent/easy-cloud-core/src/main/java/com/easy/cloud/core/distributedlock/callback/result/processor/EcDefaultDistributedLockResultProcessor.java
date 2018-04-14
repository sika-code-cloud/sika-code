package com.easy.cloud.core.distributedlock.callback.result.processor;

import org.aspectj.lang.ProceedingJoinPoint;

import com.easy.cloud.core.distributedlock.callback.result.EcDistributedLockResult;
import com.easy.cloud.core.distributedlock.constant.error.EcDistributedLockErrorCodeEnum;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;

/**
 * 
 * <p>
 * 分布式锁的结果处理器类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年4月14日 上午9:47:57
 */
public class EcDefaultDistributedLockResultProcessor extends EcBaseDistributedLockResultProcessor {

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
	protected Object success(ProceedingJoinPoint pjp, EcDistributedLockResult distributedLockResult) {
		return super.doProceed(pjp);
	}

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
	protected Object fail(ProceedingJoinPoint pjp, EcDistributedLockResult distributedLockResult) {
		throw new EcBaseBusinessException(EcDistributedLockErrorCodeEnum.LOCK_RESULT_CANT_NULL);
	}

}
