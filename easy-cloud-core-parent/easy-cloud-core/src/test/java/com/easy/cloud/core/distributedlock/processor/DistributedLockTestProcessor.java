package com.easy.cloud.core.distributedlock.processor;

import org.aspectj.lang.ProceedingJoinPoint;

import com.easy.cloud.core.lock.callback.result.EcLockResult;
import com.easy.cloud.core.lock.callback.result.processor.EcBaseLockResultProcessor;

public class DistributedLockTestProcessor extends EcBaseLockResultProcessor{

	@Override
	protected Object success(ProceedingJoinPoint pjp, EcLockResult distributedLockResult) {
		System.out.println("-----------------调用测试的结果处理方法洛");
		return super.doProceed(pjp);
	}

	@Override
	protected Object fail(ProceedingJoinPoint pjp, EcLockResult distributedLockResult) {
		throw new RuntimeException("");
	}

}
