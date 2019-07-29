package com.sika.code.lock.redis.distribution;

import com.sika.code.lock.distribution.DistributionLock;
import com.sika.code.lock.pojo.result.LockResult;
import com.sika.code.lock.pojo.result.TryLockResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 使用redis实现分布式锁
 *
 * @author daiqi
 * @create 2019-07-28 17:39
 */
@AllArgsConstructor
@Slf4j
public class DistributionLockRedis implements DistributionLock {

    private RedissonClient redissonClient;

    @Override
    public LockResult lock(String lockKey) {
        return lock(lockKey, -1, null);
    }


    @Override
    public LockResult lock(String lockKey, int timeout) {
        return lock(lockKey, timeout, TimeUnit.SECONDS);
    }


    @Override
    public LockResult lock(String lockKey, int timeout, TimeUnit unit) {
        RLock lock = redissonClient.getLock(lockKey);
        return lockCore(lock, timeout, unit);
    }


    @Override
    public LockResult tryLock(String lockKey, int waitTime, int leaseTime) {
        return tryLock(lockKey, waitTime, leaseTime, TimeUnit.SECONDS);
    }

    @Override
    public LockResult tryLock(String lockKey, int waitTime, int leaseTime, TimeUnit unit) {
        RLock lock = redissonClient.getLock(lockKey);
        return tryLockCore(lock, waitTime, leaseTime, unit);
    }

    @Override
    public LockResult fairLock(String lockKey) {
        return fairLock(lockKey, -1, null);
    }

    @Override
    public LockResult fairLock(String lockKey, int timeout) {
        return fairLock(lockKey, timeout, TimeUnit.SECONDS);
    }

    @Override
    public LockResult fairLock(String lockKey, int timeout, TimeUnit unit) {
        RLock lock = redissonClient.getFairLock(lockKey);
        return lockCore(lock, timeout, unit);
    }

    @Override
    public LockResult tryFairLock(String lockKey, int waitTime, int leaseTime) {
        return tryFairLock(lockKey, waitTime, leaseTime, TimeUnit.SECONDS);
    }

    @Override
    public LockResult tryFairLock(String lockKey, int waitTime, int leaseTime, TimeUnit unit) {
        RLock lock = redissonClient.getFairLock(lockKey);
        return tryLockCore(lock, waitTime, leaseTime, unit);
    }

    @Override
    public LockResult lock(List<String> lockKeys) {
        return lock(lockKeys, -1, null);
    }

    @Override
    public LockResult lock(List<String> lockKeys, int timeout) {
        return lock(lockKeys, timeout, TimeUnit.SECONDS);
    }


    @Override
    public LockResult lock(List<String> lockKeys, int timeout, TimeUnit unit) {
        Set<RLock> locks = new LinkedHashSet<>(lockKeys.size());
        for (String lockKey : lockKeys) {
            locks.add(redissonClient.getLock(lockKey));
        }
        RLock[] lockArray = locks.toArray(new RLock[locks.size()]);
        RedissonMultiLock lock = new RedissonMultiLock(lockArray);
        return lockCore(lock, timeout, unit);
    }

    @Override
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        unlock(lock);
    }

    @Override
    public void unlock(Lock lock) {
        lock.unlock();
    }

    private LockResult lockCore(RLock lock, int timeout, TimeUnit unit) {
        lock.lock(timeout, unit);
        return new LockResult(lock);
    }

    private LockResult tryLockCore(RLock lock, int waitTime, int leaseTime, TimeUnit unit) {
        boolean success = false;
        try {
            success = lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            // 什么都不做
        }
        return new TryLockResult(lock, success);
    }

}
