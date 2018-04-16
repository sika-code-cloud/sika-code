package com.easy.cloud.core.lock.template.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.lock.annotation.EcLock;
import com.easy.cloud.core.lock.callback.EcLockCallback;
import com.easy.cloud.core.lock.callback.result.EcLockResult;
import com.easy.cloud.core.lock.constant.EcLockConstant.EcLockContainer;
import com.easy.cloud.core.lock.constant.EcLockConstant.EcLockTypeEnum;
import com.easy.cloud.core.lock.constant.error.EcLockErrorCodeEnum;
import com.easy.cloud.core.lock.pojo.dto.EcLockDTO;
import com.easy.cloud.core.lock.template.EcLockTemplate;

/**
 * 
 * <p>
 * jdk的锁模板处理类
 * </p>
 *
 * <pre>
 *  说明：使用jdk提供的锁机制进行锁处理
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * @创建时间 2018年4月16日 上午9:12:50
 */
public class EcLockTemplateJdk implements EcLockTemplate {
	/** 日志对象 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final Lock fairLock = new ReentrantLock(true);
	private final Lock unFairLock = new ReentrantLock(false);
	private final Lock readLock = new StampedLock().asReadLock();
	private final Lock weiteLock = new StampedLock().asWriteLock();
	
	@Override
	public <T> T lock(EcLockCallback<T> callback) {
		return doLockLogic(callback);
	}


	@Override
	public <T> T tryLock(EcLockCallback<T> callback) {
		return doLockLogic(callback);
	}
	
	/** 执行加锁的业务逻辑 */
	private <T> T doLockLogic(EcLockCallback<T> callback) {
		EcLockDTO lockDTO = callback.getDistributedLockDTO();
		EcLock lockAnnotation = lockDTO.getLockAnnotation();
		EcLogUtils.debug("锁的注解信息", lockAnnotation, logger);
		String fullLockName = lockDTO.getLockNameFull();
		Lock lock = getLock(lockAnnotation.type());
		try {
			// 获取锁			
			if (lockAnnotation.tryLock()) {
				lock.tryLock(lockAnnotation.waitTime(), lockAnnotation.timeUnit());
			} else {
				lock.lock();
			}
			// 判断jdk锁容器中是否包含该锁名对应的锁			
			if (EcMapUtils.containsKey(EcLockContainer.getJdkLockContainer(), fullLockName)) {
				// 循环获取锁
				if (cyclicGetLock(fullLockName, lockAnnotation)) {
					return callback.process(new EcLockResult(true));
				}
				// 获取锁失败返回失败处理结果
				return callback.process(new EcLockResult(false));
			} else {
				EcLockContainer.putValueToJdkLockContainer(fullLockName, true);
				return callback.process(new EcLockResult(true));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (lock != null) {
				lock.unlock();
			}
			EcLockContainer.removeValueFromJdkLockContainer(fullLockName);
		}
	}
	
	/**
	 * 
	 * <p>
	 * 循环获取锁 ，在指定等待时间内加成功返回true，否则返回false
	 * </p>
	 *
	 * @param fullLockName
	 *            : String : 完整的额锁名
	 * @param lockAnnotation
	 *            : EcLock : 锁的注解
	 * @return
	 * @throws InterruptedException
	 * @author daiqi
	 * @创建时间 2018年4月16日 上午11:26:06
	 */
	private boolean cyclicGetLock(final String fullLockName, final EcLock lockAnnotation) throws InterruptedException {
		boolean getLockSucc = false;
		boolean loop = true;
		// 等待时间的毫秒值
		long waitTimeMillis = lockAnnotation.waitTime();
		if (lockAnnotation.waitTime() > -1) {
			if (TimeUnit.SECONDS.equals(lockAnnotation.timeUnit())) {
				waitTimeMillis = lockAnnotation.waitTime() * 1000;
			}
		}
		// 开始时间戳
		long beginTimestamp = EcDateUtils.getCurrentTimeMillis();
		while (loop) {
			Thread.sleep(30);
			if (EcDateUtils.getCurrentTimeMillis() - beginTimestamp > waitTimeMillis) {
				break;
			}
			// 如果key还被锁定，跳过当次循环继续下一次循环
			if (EcMapUtils.containsKey(EcLockContainer.getJdkLockContainer(), fullLockName)) {
				continue;
			} else {
				loop = false;
				getLockSucc = true;
			}
		}
		return getLockSucc;
	}

	/** 根据完整的锁名获取锁 */
	private Lock getLock(EcLockTypeEnum lockType) {
		if (EcLockTypeEnum.isFair(lockType)) {
			return fairLock;
		} else if (EcLockTypeEnum.isUnfair(lockType)) {
			return unFairLock;
		} else if (EcLockTypeEnum.isRead(lockType)) {
			return readLock;
		} else if (EcLockTypeEnum.isWrite(lockType)) {
			return weiteLock;
		}
		throw new EcBaseBusinessException(EcLockErrorCodeEnum.LOCK_TYPE_NOT_SUPPORT);
	}
}
