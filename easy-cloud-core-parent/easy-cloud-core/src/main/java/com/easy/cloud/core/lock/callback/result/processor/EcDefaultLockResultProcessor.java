package com.easy.cloud.core.lock.callback.result.processor;

import org.aspectj.lang.ProceedingJoinPoint;

import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.lock.callback.result.EcLockResult;
import com.easy.cloud.core.lock.constant.error.EcLockErrorCodeEnum;

/**
 * 
 * <p>
 * 分布式锁的结果处理器类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年4月14日 上午9:47:57
 */
public class EcDefaultLockResultProcessor extends EcBaseLockResultProcessor {

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
	protected Object success(ProceedingJoinPoint pjp, EcLockResult distributedLockResult) {
		System.out.println("=============调用默认的结果处理方法洛");
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
	protected Object fail(ProceedingJoinPoint pjp, EcLockResult distributedLockResult) {
		throw new EcBaseBusinessException(EcLockErrorCodeEnum.LOCK_GAIN_FAIL);
	}

}
