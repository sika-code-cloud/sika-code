package com.easy.cloud.core.distributionlock.worker;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.distributionlock.constant.EcDistributedLockConstant;
import com.easy.cloud.core.distributionlock.service.EcDistributionService;

public class WorkerNo implements Runnable {

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    private final EcDistributionService service;
    private RedissonClient redissonClient;

    public WorkerNo(CountDownLatch startSignal, CountDownLatch doneSignal, EcDistributionService service, RedissonClient redissonClient) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.service = service;
        this.redissonClient = redissonClient;
    }

    @Override
    public void run() {
        try {
            startSignal.await();

            System.out.println(Thread.currentThread().getName() + " start");
            Map<String, Object> person = EcMapUtils.newHashMap();
            person.put("id", 1);
            person.put("name", "张三");
//            Integer count = service.aspect("1");


            doneSignal.countDown();

        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}