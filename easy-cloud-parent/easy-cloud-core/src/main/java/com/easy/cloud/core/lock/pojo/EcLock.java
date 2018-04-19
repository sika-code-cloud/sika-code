package com.easy.cloud.core.lock.pojo;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.cache.redis.handler.EcRedisTemplateHandler;
import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.lock.constant.EcLockConstant.EcLockTypeEnum;
import com.easy.cloud.core.lock.constant.error.EcLockErrorCodeEnum;

import redis.clients.jedis.Jedis;

/**
 * 
 * <p>
 * ec锁
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年4月18日 上午9:43:29
 */
public class EcLock {
	private static ReentrantLock firaLock = new ReentrantLock(true);
	private static ReentrantLock nonfairLock = new ReentrantLock();
	private static Lock readLock = new StampedLock().asReadLock();
	private static Lock writeLock = new StampedLock().asWriteLock();

	private static final String LOCK_SUCCESS = "OK";
	private static final String SET_IF_NOT_EXIST = "NX";
	private static final String SET_WITH_EXPIRE_TIME_MILL = "PX";
	private static final String SET_WITH_EXPIRE_TIME_SEC = "EX";
	private static final Long RELEASE_SUCCESS = 1L;
	private String lockName;
	private String lockValue;
	private Lock currentLock;
	private StringRedisTemplate stringRedisTemplateLock;

	public EcLock(StringRedisTemplate stringRedisTemplateLock, String lockName, EcLockTypeEnum lockType) {
		this.stringRedisTemplateLock = stringRedisTemplateLock;
		this.lockName = lockName;
		this.currentLock = getLock(lockType);
		lockValue = Thread.currentThread().getName();
	}

	/** 根据完整的锁名获取锁 */
	private Lock getLock(EcLockTypeEnum lockType) {
		if (EcLockTypeEnum.isFair(lockType)) {
			return firaLock;
		} else if (EcLockTypeEnum.isUnfair(lockType)) {
			return nonfairLock;
		} else if (EcLockTypeEnum.isRead(lockType)) {
			return readLock;
		} else if (EcLockTypeEnum.isWrite(lockType)) {
			return writeLock;
		}
		throw new EcBaseBusinessException(EcLockErrorCodeEnum.LOCK_TYPE_NOT_SUPPORT);
	}

	/** 锁 */
	public boolean lock(long leaseTime, TimeUnit timeUnit) {
		return loopGainDistributedLock(leaseTime, timeUnit);
	}

	/** 尝试锁 */
	public boolean tryLock(long leaseTime, TimeUnit timeUnit) throws InterruptedException {
		boolean getLock = currentLock.tryLock(leaseTime, timeUnit);
		if (!getLock) {
			return false;
		}
		return loopGainDistributedLock(leaseTime, timeUnit);
	}

	public void unlock() {
		releaseDistributedLock();
	}

	/** 循环获取分布式锁 */
	private boolean loopGainDistributedLock(long leaseTime, TimeUnit timeUnit) {
		boolean gainLock = false;
		long startTimestamp = EcDateUtils.getCurrentTimeMillis();
		while (true) {
			if (EcDateUtils.getCurrentTimeMillis() - startTimestamp < 0) {
				break;
			}
			if (gainDistributedLock(leaseTime, timeUnit)) {
				gainLock = true;
				break;
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return gainLock;
	}

	/** 获取分布式锁 */
	private boolean gainDistributedLock(long leaseTime, TimeUnit timeUnit) {
		RedisCallback<String> redisCallback = new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				String px = SET_WITH_EXPIRE_TIME_MILL;
				if (TimeUnit.SECONDS.equals(timeUnit)) {
					px = SET_WITH_EXPIRE_TIME_SEC;
				}
				Jedis jedis = (Jedis) connection.getNativeConnection();
				return jedis.set(lockName, lockValue, SET_IF_NOT_EXIST, px, leaseTime);
			}
		};
		String result = stringRedisTemplateLock.execute(redisCallback);
		if (EcStringUtils.equals(LOCK_SUCCESS, result)) {
			return true;
		}
		return false;
	}

	/**
	 * 释放分布式锁
	 * 
	 * @return 是否释放成功
	 */
	private boolean releaseDistributedLock() {
		RedisCallback<Object> redisCallback = new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
				Jedis jedis = (Jedis) connection.getNativeConnection();
				return jedis.eval(script, Collections.singletonList(lockName), Collections.singletonList(lockValue));
			}
		};
		Object result = stringRedisTemplateLock.execute(redisCallback);
		if (EcBaseUtils.equals(RELEASE_SUCCESS, result)) {
			return true;
		}
		return false;
	}
}
