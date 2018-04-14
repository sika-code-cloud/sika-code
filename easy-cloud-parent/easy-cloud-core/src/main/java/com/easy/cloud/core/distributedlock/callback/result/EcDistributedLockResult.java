package com.easy.cloud.core.distributedlock.callback.result;

/**
 * 
 * <p>
 * 分布式锁的结果类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年4月14日 上午9:47:57
 */
public class EcDistributedLockResult {
	/** 是否获取锁---true:获取成功---false:获取失败 */
	private boolean isGainLock;

	public EcDistributedLockResult() {

	}

	public EcDistributedLockResult(boolean isGainLock) {
		this.isGainLock = isGainLock;
	}

	public EcDistributedLockResult buildGainLock(boolean isGainLock) {
		this.isGainLock = isGainLock;
		return this;
	}

	public boolean isGainLock() {
		return isGainLock;
	}

	public void setGainLock(boolean isGainLock) {
		this.isGainLock = isGainLock;
	}

}
