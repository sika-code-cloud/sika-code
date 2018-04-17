package com.easy.cloud.core.lock.template.impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.cloud.core.cache.redis.handler.EcRedisTemplateHandler;
import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.lock.annotation.EcLock;
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
		EcLock lockAnnotation = lockDTO.getLockAnnotation();
		EcLogUtils.debug("锁的注解信息", lockAnnotation, logger);
//		RLock lock = getLock(lockDTO.getLockNameFull(), lockAnnotation.type());
		String name = Thread.currentThread().getName();
		String lockNameFull = lockDTO.getLockNameFull();
		
		boolean flag = true;
		try {
			System.out.println("--------------11-" + lockNameFull);
			synchronized (this) {
				while (true) {
					flag = EcRedisTemplateHandler.setIfAbsent(lockNameFull, name);
					String value = EcRedisTemplateHandler.get(lockNameFull, String.class);
					if (flag && value.equals(name)) {
						break;
					}
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				EcRedisTemplateHandler.set(lockNameFull, name, 2000);
				if (!flag) {
					return callback.process(new EcLockResult(false));
				}
			}
			return callback.process(new EcLockResult(true));
//			lock.lock(lockAnnotation.leaseTime(), lockAnnotation.timeUnit());
		} finally {
			EcRedisTemplateHandler.delete(lockNameFull);
		}
	}
	
	@Override
	public <T> T tryLock(EcLockCallback<T> callback) {
		EcLockDTO lockDTO = callback.getDistributedLockDTO();
		EcLock lockAnnotation = lockDTO.getLockAnnotation();
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
