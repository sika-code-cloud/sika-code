package com.easy.cloud.core.distributedlock.test;

import org.aspectj.lang.ProceedingJoinPoint;

import com.easy.cloud.core.distributedlock.callback.result.EcDistributedLockResult;
import com.easy.cloud.core.distributedlock.callback.result.processor.EcBaseDistributedLockResultProcessor;

public class DistributedLockTestProcessor extends EcBaseDistributedLockResultProcessor{

	@Override
	protected Object success(ProceedingJoinPoint pjp, EcDistributedLockResult distributedLockResult) {
		System.out.println("-----------------调用测试的结果处理方法洛");
		return super.doProceed(pjp);
	}

	@Override
	protected Object fail(ProceedingJoinPoint pjp, EcDistributedLockResult distributedLockResult) {
		throw new RuntimeException("锁获取失败");
	}

}
