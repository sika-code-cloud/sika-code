package com.sika.code.lock.distribution;

import com.sika.code.lock.pojo.result.LockResult;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 分布式锁助手接口
 *
 * @author daiqi
 * @create 2019-07-28 17:38
 */
public interface DistributionLockHandler {

    /**
     * <p>
     * 加锁 --- 非公平锁
     * </p>
     *
     * @param lockKey 锁的key
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:24
     */
    LockResult lock(String lockKey);

    /**
     * <p>
     * 带自动释放锁时间的锁 --- 非公平锁
     * </p>
     *
     * @param lockKey   锁的key
     * @param leaseTime 上锁后自动释放锁时间   单位：秒
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:23
     */
    LockResult lock(String lockKey, int leaseTime);

    /**
     * <p>
     * 带自动释放锁时间的锁 --- 非公平锁
     * </p>
     *
     * @param lockKey   锁的key
     * @param leaseTime 上锁后自动释放锁时间
     * @param unit      时间单位
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:23
     */
    LockResult lock(String lockKey, int leaseTime, TimeUnit unit);

    /**
     * <p>
     * 尝试获取锁 --- 非公平锁
     * </p>
     *
     * @param lockKey   : 锁的key
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:22
     */
    LockResult tryLock(String lockKey, int waitTime, int leaseTime);

    /**
     * <p>
     * 尝试获取锁 --- 非公平锁
     * </p>
     *
     * @param lockKey   锁的key
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @param unit      时间单位
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:21
     */
    LockResult tryLock(String lockKey, int waitTime, int leaseTime, TimeUnit unit);


    /**
     * <p>
     * 加锁 --- 公平锁
     * </p>
     *
     * @param lockKey 锁的key
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:20
     */
    LockResult fairLock(String lockKey);

    /**
     * <p>
     * 带自动释放锁时间的锁 --- 公平锁
     * </p>
     *
     * @param lockKey   锁的key
     * @param leaseTime 上锁后自动释放锁时间   单位：秒
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:19
     */
    LockResult fairLock(String lockKey, int leaseTime);

    /**
     * <p>
     * 带自动释放锁时间的锁 --- 公平锁
     * </p>
     *
     * @param lockKey   锁的key
     * @param leaseTime 上锁后自动释放锁时间
     * @param unit      时间单位
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:19
     */
    LockResult fairLock(String lockKey, int leaseTime, TimeUnit unit);

    /**
     * <p>
     * 尝试获取锁 --- 公平锁
     * </p>
     *
     * @param lockKey   锁key
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:18
     */
    LockResult tryFairLock(String lockKey, int waitTime, int leaseTime);

    /**
     * <p>
     * 尝试获取公平锁 --- 公平锁
     * </p>
     *
     * @param lockKey   锁key
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @param unit      时间单位
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:17
     */
    LockResult tryFairLock(String lockKey, int waitTime, int leaseTime, TimeUnit unit);

    /**
     * <p>
     * 联锁、支持多个key对象关联为一个联锁
     * </p>
     *
     * @param lockKeys : 锁key的列表
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:08
     */
    LockResult lock(Collection<String> lockKeys);

    /**
     * <p>
     * 联锁、支持多个key对象关联为一个联锁
     * </p>
     *
     * @param lockKeys : 锁key的列表
     * @param timeout  : 时间
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:08
     */
    LockResult lock(Collection<String> lockKeys, int timeout);

    /**
     * <p>
     * 联锁、支持多个key对象关联为一个联锁
     * </p>
     *
     * @param lockKeys : 锁key的列表
     * @param timeout  : 时间
     * @param unit     : 时间单位
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:08
     */
    LockResult lock(Collection<String> lockKeys, int timeout, TimeUnit unit);

    /**
     * <p>
     * 尝试联锁、支持多个key对象关联为一个联锁
     * </p>
     *
     * @param lockKeys  : 锁key的列表
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:08
     */
    LockResult tryLock(Collection<String> lockKeys, int waitTime, int leaseTime);

    /**
     * <p>
     * 尝试联锁、支持多个key对象关联为一个联锁
     * </p>
     *
     * @param lockKeys  : 锁key的列表
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @param unit      时间单位
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:08
     */
    LockResult tryLock(Collection<String> lockKeys, int waitTime, int leaseTime, TimeUnit unit);

    /**
     * 释放锁
     *
     * @param lock : 锁对象
     */
    void unlock(Lock lock);
}
