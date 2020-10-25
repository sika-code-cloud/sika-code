package com.sika.code.lock.redis.distribution;

import com.sika.code.lock.distribution.DistributionLockHandler;
import com.sika.code.lock.pojo.result.LockResult;
import com.sika.code.lock.pojo.result.TryLockResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.Collection;
import java.util.LinkedHashSet;
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
public class DistributionLockHandlerRedis implements DistributionLockHandler {

    private RedissonClient redissonClient;

    @Override
    public LockResult lock(String lockKey) {
        return lock(lockKey, -1, null);
    }

    @Override
    public LockResult lock(String lockKey, int leaseTime) {
        return lock(lockKey, leaseTime, TimeUnit.SECONDS);
    }


    @Override
    public LockResult lock(String lockKey, int leaseTime, TimeUnit unit) {
        RLock lock = redissonClient.getLock(lockKey);
        return lockCore(lock, leaseTime, unit);
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
    public LockResult fairLock(String lockKey, int leaseTime) {
        return fairLock(lockKey, leaseTime, TimeUnit.SECONDS);
    }

    @Override
    public LockResult fairLock(String lockKey, int leaseTime, TimeUnit unit) {
        RLock lock = redissonClient.getFairLock(lockKey);
        return lockCore(lock, leaseTime, unit);
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
    public LockResult lock(Collection<String> lockKeys) {
        return lock(lockKeys, -1, null);
    }

    @Override
    public LockResult lock(Collection<String> lockKeys, int leaseTime) {
        return lock(lockKeys, leaseTime, TimeUnit.SECONDS);
    }


    @Override
    public LockResult lock(Collection<String> lockKeys, int leaseTime, TimeUnit unit) {
        RedissonMultiLock lock = buildMultiLock(lockKeys);
        return lockCore(lock, leaseTime, unit);
    }

    @Override
    public LockResult tryLock(Collection<String> lockKeys, int waitTime, int leaseTime) {
        return tryLock(lockKeys, waitTime, leaseTime, TimeUnit.SECONDS);
    }

    @Override
    public LockResult tryLock(Collection<String> lockKeys, int waitTime, int leaseTime, TimeUnit unit) {
        RedissonMultiLock lock = buildMultiLock(lockKeys);
        return tryLockCore(lock, waitTime, leaseTime, unit);
    }

    @Override
    public void unlock(Lock lock) {
        lock.unlock();
    }

    /**
     * <p>
     * 根据锁key列表构建级联锁
     * </p>
     *
     * @param lockKeys : 锁key列表
     * @return org.redisson.RedissonMultiLock
     * @author daiqi
     * @date 2019/7/29 10:37
     */
    protected RedissonMultiLock buildMultiLock(Collection<String> lockKeys) {
        Set<RLock> locks = new LinkedHashSet<>(lockKeys.size());
        for (String lockKey : lockKeys) {
            locks.add(redissonClient.getLock(lockKey));
        }
        RLock[] lockArray = locks.toArray(new RLock[locks.size()]);
        return new RedissonMultiLock(lockArray);
    }

    /**
     * <p>
     * 锁核心逻辑
     * </p>
     *
     * @param lock      : 锁对象
     * @param leaseTime : 上锁后自动释放锁时间
     * @param unit      : 时间单位
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:31
     */
    protected LockResult lockCore(RLock lock, int leaseTime, TimeUnit unit) {
        lock.lock(leaseTime, unit);
        return new LockResult(lock);
    }

    /**
     * <p>
     * 尝试锁核心逻辑
     * </p>
     *
     * @param lock      : 锁对象
     * @param waitTime  : 最多等待时间
     * @param leaseTime : 上锁后自动释放锁时间
     * @param unit      : 时间单位
     * @return com.sika.code.lock.pojo.result.LockResult
     * @author daiqi
     * @date 2019/7/29 10:31
     */
    protected LockResult tryLockCore(RLock lock, int waitTime, int leaseTime, TimeUnit unit) {
        boolean success = false;
        try {
            success = lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            // 什么都不做
        }
        return new TryLockResult(lock, success);
    }

}
