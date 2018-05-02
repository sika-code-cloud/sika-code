package com.easy.cloud.core.lock.test;

import org.aspectj.lang.ProceedingJoinPoint;

import com.easy.cloud.core.lock.callback.result.EcLockResult;
import com.easy.cloud.core.lock.callback.result.processor.EcBaseLockResultProcessor;

/**
 * 
 * <p>
 * 测试的结果处理类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年4月16日 下午3:58:59
 */
public class LockTestProcessor extends EcBaseLockResultProcessor{

	@Override
	protected Object success(ProceedingJoinPoint pjp, EcLockResult distributedLockResult) {
//		System.out.println("-----------------自定义的结果处理方法洛");
		return super.doProceed(pjp);
	}

	@Override
	protected Object fail(ProceedingJoinPoint pjp, EcLockResult distributedLockResult) {
		throw new RuntimeException("锁获取失败");
	}

}
