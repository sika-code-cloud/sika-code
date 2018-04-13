package com.easy.cloud.core.distributedlock.template;

import com.easy.cloud.core.distributedlock.callback.EcDistributedLockCallback;

/**
 * 
 * <p>
 * 分布式锁的操作模版接口类
 * </p>
 *
 * @author daiqi 
 * @创建时间 2018年4月12日 上午11:28:23
 */
public interface EcDistributedLockTemplate {

	/**
	 * 使用分布式锁。
	 * 
	 * @param callback
	 * @return
	 */
	<T> T lock(EcDistributedLockCallback<T> callback);

	/**
	 * 尝试分布式锁
	 * 
	 * @param callback
	 * @param <T>
	 * @return
	 */
	<T> T tryLock(EcDistributedLockCallback<T> callback);

}
