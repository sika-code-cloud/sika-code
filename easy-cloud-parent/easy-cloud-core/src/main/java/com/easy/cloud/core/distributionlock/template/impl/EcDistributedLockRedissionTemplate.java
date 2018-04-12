package com.easy.cloud.core.distributionlock.template.impl;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

import com.easy.cloud.core.distributionlock.callback.EcDistributedLockCallback;
import com.easy.cloud.core.distributionlock.template.EcDistributedLockTemplate;

public class EcDistributedLockRedissionTemplate implements EcDistributedLockTemplate{
	private RedissonClient redisson;

    public EcDistributedLockRedissionTemplate() {
    }

    public EcDistributedLockRedissionTemplate(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Override
    public <T> T lock(EcDistributedLockCallback<T> callback, boolean fairLock) {
        return lock(callback, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT, fairLock);
    }

    @Override
    public <T> T lock(EcDistributedLockCallback<T> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock) {
        RLock lock = getLock(callback.getLockName(), fairLock);
        try {
            lock.lock(leaseTime, timeUnit);
            return callback.process();
        } finally {
            if (lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    @Override
    public <T> T tryLock(EcDistributedLockCallback<T> callback, boolean fairLock) {
        return tryLock(callback, DEFAULT_WAIT_TIME, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT, fairLock);
    }

    @Override
    public <T> T tryLock(EcDistributedLockCallback<T> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock) {
        RLock lock = getLock(callback.getLockName(), fairLock);
        try {
            if (lock.tryLock(waitTime, leaseTime, timeUnit)) {
                return callback.process();
            }
        } catch (InterruptedException e) {

        } finally {
            if (lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
        return null;
    }

    private RLock getLock(String lockName, boolean fairLock) {
        RLock lock;
        if (fairLock) {
            lock = redisson.getFairLock(lockName);
        } else {
            lock = redisson.getLock(lockName);
            RReadWriteLock r = redisson.getReadWriteLock(lockName);
        }
        return lock;
    }

    public void setRedisson(RedissonClient redisson) {
        this.redisson = redisson;
    }
}
