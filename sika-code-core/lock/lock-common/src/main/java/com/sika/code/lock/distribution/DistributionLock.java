package com.sika.code.lock.distribution;

import com.sika.code.lock.pojo.result.LockResult;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 分布式锁接口
 *
 * @author daiqi
 * @create 2019-07-28 17:38
 */
public interface DistributionLock {

    /**
     * 加锁
     *
     * @param lockKey
     * @return Lock
     */
    LockResult lock(String lockKey);

    /**
     * 带超时的锁
     *
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
    LockResult lock(String lockKey, int timeout);

    /**
     * 带超时的锁
     *
     * @param lockKey 锁的key
     * @param timeout 超时时间
     * @param unit    时间单位
     */
    LockResult lock(String lockKey, int timeout, TimeUnit unit);

    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    LockResult tryLock(String lockKey, int waitTime, int leaseTime);

    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @param unit      时间单位
     * @return
     */
    LockResult tryLock(String lockKey, int waitTime, int leaseTime, TimeUnit unit);


    /**
     * 加锁
     *
     * @param lockKey
     * @return Lock
     */
    LockResult fairLock(String lockKey);

    /**
     * 带超时的锁
     *
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
    LockResult fairLock(String lockKey, int timeout);

    /**
     * 带超时的锁
     *
     * @param lockKey 锁的key
     * @param timeout 超时时间
     * @param unit    时间单位
     */
    LockResult fairLock(String lockKey, int timeout, TimeUnit unit);

    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    LockResult tryFairLock(String lockKey, int waitTime, int leaseTime);

    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @param unit      时间单位
     * @return
     */
    LockResult tryFairLock(String lockKey, int waitTime, int leaseTime, TimeUnit unit);

    LockResult lock(List<String> lockKeys);

    LockResult lock(List<String> lockKeys, int timeout);

    LockResult lock(List<String> lockKeys, int timeout, TimeUnit unit);

    /**
     * 释放锁
     *
     * @param lockKey
     */
    void unlock(String lockKey);

    /**
     * 释放锁
     *
     * @param lock
     */
    void unlock(Lock lock);
}
