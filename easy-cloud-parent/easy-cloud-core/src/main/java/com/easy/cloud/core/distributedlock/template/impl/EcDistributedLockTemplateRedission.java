package com.easy.cloud.core.distributedlock.template.impl;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.distributedlock.annotation.EcDistributedLock;
import com.easy.cloud.core.distributedlock.callback.EcDistributedLockCallback;
import com.easy.cloud.core.distributedlock.callback.result.EcDistributedLockResult;
import com.easy.cloud.core.distributedlock.constant.EcDistributedLockConstant.EcDistributedLockTypeEnum;
import com.easy.cloud.core.distributedlock.constant.error.EcDistributedLockErrorCodeEnum;
import com.easy.cloud.core.distributedlock.pojo.dto.EcDistributedLockDTO;
import com.easy.cloud.core.distributedlock.template.EcDistributedLockTemplate;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;

/**
 * 
 * <p>
 * 使用redisson做为分布式锁的实现
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年4月13日 下午2:15:30
 */
public class EcDistributedLockTemplateRedission implements EcDistributedLockTemplate {
	/** 日志对象 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private RedissonClient redisson;

	public EcDistributedLockTemplateRedission() {
		
	}

	public EcDistributedLockTemplateRedission(RedissonClient redisson) {
		this.redisson = redisson;
	}

	@Override
	public <T> T lock(EcDistributedLockCallback<T> callback) {
		EcDistributedLockDTO distributedLockDTO = callback.getDistributedLockDTO();
		EcDistributedLock distributedLock = distributedLockDTO.getDistributedLock();
		EcLogUtils.info("锁的注解信息", distributedLock, logger);
		RLock lock = getLock(distributedLockDTO.getLockNameFull(), distributedLock.lockType());
		try {
			lock.lock(distributedLock.leaseTime(), distributedLock.timeUnit());
			return callback.process(new EcDistributedLockResult(true));
		} finally {
			if (lock != null && lock.isLocked()) {
				lock.unlock();
			}
		}
	}
	
	@Override
	public <T> T tryLock(EcDistributedLockCallback<T> callback) {
		EcDistributedLockDTO distributedLockDTO = callback.getDistributedLockDTO();
		EcDistributedLock distributedLock = distributedLockDTO.getDistributedLock();
		RLock lock = getLock(distributedLockDTO.getLockNameFull(), distributedLock.lockType());
		boolean isGainLock = false;
		try {
			isGainLock = lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());
			return callback.process(new EcDistributedLockResult(isGainLock));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (lock != null && lock.isLocked() && isGainLock) {
				lock.unlock();
			}
		}
		return null;
	}
	
	/** 根据完整的锁名获取锁 */
	private RLock getLock(String lockNameFull, EcDistributedLockTypeEnum lockType) {
		if (EcDistributedLockTypeEnum.isFair(lockType)) {
			return redisson.getLock(lockNameFull);
		} else if (EcDistributedLockTypeEnum.isUnfair(lockType)){
			return redisson.getFairLock(lockNameFull);
		} else if (EcDistributedLockTypeEnum.isRead(lockType)) {
			return redisson.getReadWriteLock(lockNameFull).readLock();
		} else if (EcDistributedLockTypeEnum.isWrite(lockType)) {
			return redisson.getReadWriteLock(lockNameFull).writeLock();
		}
		throw new EcBaseBusinessException(EcDistributedLockErrorCodeEnum.LOCK_TYPE_NOT_SUPPORT);
	}
	
	public void setRedisson(RedissonClient redisson) {
		this.redisson = redisson;
	}

}
