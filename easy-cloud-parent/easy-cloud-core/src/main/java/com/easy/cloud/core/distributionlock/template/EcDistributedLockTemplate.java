package com.easy.cloud.core.distributionlock.template;

import java.util.concurrent.TimeUnit;

import com.easy.cloud.core.distributionlock.callback.EcDistributedLockCallback;
import com.easy.cloud.core.distributionlock.constant.EcDistributedLockConstant.EcDistributedLockTime;

/**
 * 
 * <p>
 * 分布式锁的操作模版接口类
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年4月12日 上午11:28:23
 */
public interface EcDistributedLockTemplate {
    TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    long DEFAULT_WAIT_TIME = EcDistributedLockTime.WAIT_TIME_DEFAULT;
    long DEFAULT_TIMEOUT   = EcDistributedLockTime.LEASE_TIME_DEFAULT;

    /**
     * 使用分布式锁，使用锁默认超时时间。
     * @param callback
     * @param fairLock 是否使用公平锁
     * @return
     */
    <T> T lock(EcDistributedLockCallback<T> callback, boolean fairLock);

    /**
     * 使用分布式锁。自定义锁的超时时间
     *
     * @param callback
     * @param leaseTime 锁超时时间。超时后自动释放锁。
     * @param timeUnit
     * @param fairLock 是否使用公平锁
     * @param <T>
     * @return
     */
    <T> T lock(EcDistributedLockCallback<T> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock);

    /**
     * 尝试分布式锁，使用锁默认等待时间、超时时间。
     * @param callback
     * @param fairLock 是否使用公平锁
     * @param <T>
     * @return
     */
    <T> T tryLock(EcDistributedLockCallback<T> callback, boolean fairLock);

    /**
     * 尝试分布式锁，自定义等待时间、超时时间。
     * @param callback
     * @param waitTime 获取锁最长等待时间
     * @param leaseTime 锁超时时间。超时后自动释放锁。
     * @param timeUnit
     * @param fairLock 是否使用公平锁
     * @param <T>
     * @return
     */
    <T> T tryLock(EcDistributedLockCallback<T> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock);
}
