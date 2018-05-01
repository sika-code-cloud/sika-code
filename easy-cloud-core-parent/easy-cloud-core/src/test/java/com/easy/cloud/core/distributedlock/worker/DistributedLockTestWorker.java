package com.easy.cloud.core.distributedlock.worker;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.redisson.api.RedissonClient;

import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.distributedlock.service.DistributedLockTestService;

public class DistributedLockTestWorker implements Runnable {

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    private final DistributedLockTestService service;
    private String id;

    public DistributedLockTestWorker(CountDownLatch startSignal, CountDownLatch doneSignal, DistributedLockTestService service, RedissonClient redissonClient) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.service = service;
    }
    public DistributedLockTestWorker(CountDownLatch startSignal, CountDownLatch doneSignal, DistributedLockTestService service, RedissonClient redissonClient, String id) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.service = service;
        this.id = id;
    }
    @Override
    public void run() {
        try {
            startSignal.await();

            System.out.println(Thread.currentThread().getName() + " start");
            Map<String, Object> person = EcMapUtils.newHashMap();
            if (id == null) {
            	id = "10";
            }
            person.put("id", Integer.parseInt(id));
            person.put("name", "张三");
//            Integer count = service.aspect(person);
            Integer count = service.aspectTryLock(person);
//            Integer count = service.aspect(id, person);
//            Integer count = service.aspect(id, "zhangsan");
//            Integer count = service.aspectTryLock(id, "zhangsan");

            System.out.println(Thread.currentThread().getName() + ": count = " + count + " id :" + id);

            doneSignal.countDown();

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}