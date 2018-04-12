package com.easy.cloud.core.distributionlock.worker;

import java.util.concurrent.CountDownLatch;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import com.easy.cloud.core.distributionlock.manager.EcDistributedLockManager;

public class Worker1 implements Runnable {

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    private final EcDistributedLockManager distributedLockManager;
    private RedissonClient redissonClient;

    public Worker1(CountDownLatch startSignal, CountDownLatch doneSignal, EcDistributedLockManager distributedLockManager, RedissonClient redissonClient) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.distributedLockManager = distributedLockManager;
        this.redissonClient = redissonClient;
    }

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + " start");

            startSignal.await();

            Integer count = aspect("lock");

            System.out.println(Thread.currentThread().getName() + ": count = " + count);

            doneSignal.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int aspect(String lockName) {
        return distributedLockManager.aspect(lockName, this);
    }

    public int aspectBusiness(String lockName) {
        RMap<String, Integer> map = redissonClient.getMap("distributionTest");

        Integer count = map.get("count");

        if (count > 0) {
            count = count - 1;
            map.put("count", count);
        }

        return count;
    }

}