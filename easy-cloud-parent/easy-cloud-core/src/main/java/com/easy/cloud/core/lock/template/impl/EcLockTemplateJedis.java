package com.easy.cloud.core.lock.template.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.lock.annotation.EcLockAnnotation;
import com.easy.cloud.core.lock.callback.EcLockCallback;
import com.easy.cloud.core.lock.callback.result.EcLockResult;
import com.easy.cloud.core.lock.pojo.EcLock;
import com.easy.cloud.core.lock.pojo.dto.EcLockDTO;
import com.easy.cloud.core.lock.template.EcLockTemplate;

/**
 * 
 * <p>
 * 使用jediss做为锁的实现
 * </p>
 *
 * @author daiqi 创建时间 2018年4月13日 下午2:15:30
 */
public class EcLockTemplateJedis implements EcLockTemplate {
	/** 日志对象 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private StringRedisTemplate stringRedisTemplateLock;

	public EcLockTemplateJedis() {

	}

	public EcLockTemplateJedis(StringRedisTemplate stringRedisTemplateLock) {
		this.stringRedisTemplateLock = stringRedisTemplateLock;
	}

	@Override
	public <T> T lock(EcLockCallback<T> callback) {
		EcLockDTO lockDTO = callback.getDistributedLockDTO();
		EcLockAnnotation lockAnnotation = lockDTO.getLockAnnotation();
		EcLogUtils.debug("锁的注解信息", lockAnnotation, logger);
		String lockNameFull = lockDTO.getLockNameFull();

		EcLock ecLock = new EcLock(stringRedisTemplateLock, lockNameFull, lockAnnotation.type());
		try {
			boolean gainLock = ecLock.lock(lockAnnotation.waitTime(), lockAnnotation.leaseTime(), lockAnnotation.timeUnit());
			if (gainLock) {
				return callback.process(new EcLockResult(true));
			}
			return callback.process(new EcLockResult(false));
		} finally {
			ecLock.unlock();
		}
	}

	@Override
	public <T> T tryLock(EcLockCallback<T> callback) {
		EcLockDTO lockDTO = callback.getDistributedLockDTO();
		EcLockAnnotation lockAnnotation = lockDTO.getLockAnnotation();
		EcLock ecLock = new EcLock(stringRedisTemplateLock, lockDTO.getLockNameFull(), lockAnnotation.type());
		boolean isGainLock = false;
		try {
			isGainLock = ecLock.tryLock(lockAnnotation.waitTime(), lockAnnotation.leaseTime(), lockAnnotation.timeUnit());
			return callback.process(new EcLockResult(isGainLock));
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ecLock.unlock();
		}
	}

}
