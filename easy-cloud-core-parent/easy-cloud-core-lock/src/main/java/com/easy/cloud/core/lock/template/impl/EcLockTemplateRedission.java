package com.easy.cloud.core.lock.template.impl;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.lock.annotation.EcLockAnnotation;
import com.easy.cloud.core.lock.callback.EcLockCallback;
import com.easy.cloud.core.lock.callback.result.EcLockResult;
import com.easy.cloud.core.lock.constant.EcLockConstant.EcLockTypeEnum;
import com.easy.cloud.core.lock.constant.error.EcLockErrorCodeEnum;
import com.easy.cloud.core.lock.pojo.dto.EcLockDTO;
import com.easy.cloud.core.lock.template.EcLockTemplate;

/**
 * 
 * <p>
 * 使用redisson做为锁的实现
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年4月13日 下午2:15:30
 */
public class EcLockTemplateRedission implements EcLockTemplate {
	/** 日志对象 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private RedissonClient redisson;
	public EcLockTemplateRedission() {
		
	}

	public EcLockTemplateRedission(RedissonClient redisson) {
		this.redisson = redisson;
	}

	@Override
	public <T> T lock(EcLockCallback<T> callback) {
		EcLockDTO lockDTO = callback.getDistributedLockDTO();
		EcLockAnnotation lockAnnotation = lockDTO.getLockAnnotation();
		EcLogUtils.debug("锁的注解信息", lockAnnotation, logger);
		RLock lock = getLock(lockDTO.getLockNameFull(), lockAnnotation.type());
		try {
			lock.lock(lockAnnotation.leaseTime(), lockAnnotation.timeUnit());
			return callback.process(new EcLockResult(true));
		} finally {
			if (lock != null && lock.isLocked()) {
				lock.unlock();
			}
		}
	}
	
	@Override
	public <T> T tryLock(EcLockCallback<T> callback) {
		EcLockDTO lockDTO = callback.getDistributedLockDTO();
		EcLockAnnotation lockAnnotation = lockDTO.getLockAnnotation();
		RLock lock = getLock(lockDTO.getLockNameFull(), lockAnnotation.type());
		boolean isGainLock = false;
		try {
			isGainLock = lock.tryLock(lockAnnotation.waitTime(), lockAnnotation.leaseTime(), lockAnnotation.timeUnit());
			return callback.process(new EcLockResult(isGainLock));
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (lock != null && lock.isLocked() && isGainLock) {
				lock.unlock();
			}
		}
	}
	
	/** 根据完整的锁名获取锁 */
	private RLock getLock(String lockNameFull, EcLockTypeEnum lockType) {
		if (EcLockTypeEnum.isFair(lockType)) {
			return redisson.getLock(lockNameFull);
		} else if (EcLockTypeEnum.isUnfair(lockType)){
			return redisson.getFairLock(lockNameFull);
		} else if (EcLockTypeEnum.isRead(lockType)) {
			return redisson.getReadWriteLock(lockNameFull).readLock();
		} else if (EcLockTypeEnum.isWrite(lockType)) {
			return redisson.getReadWriteLock(lockNameFull).writeLock();
		}
		throw new EcBaseBusinessException(EcLockErrorCodeEnum.LOCK_TYPE_NOT_SUPPORT);
	}
	
	public void setRedisson(RedissonClient redisson) {
		this.redisson = redisson;
	}

}
