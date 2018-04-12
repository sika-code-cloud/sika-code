package com.easy.cloud.core.distributionlock.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.distributionlock.service.EcDistributionService;
import com.easy.cloud.core.distributionlock.worker.Worker;

@RestController
@RequestMapping("/distributedLockTest")
public class EcDistributedLockTestController {
	private int count = 10;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private EcDistributionService service;

    @RequestMapping(value = "distributedLockTest", method = RequestMethod.GET)
    public String distributedLockTest(@RequestParam String id) throws Exception {

        RMap<String, Object> map = redissonClient.getMap("distributionTest");
        Map<String, Integer> countMap = (Map<String, Integer>) map.get("countMap");
        if (countMap.get("count") == null || countMap.get("count") == 0) {
        	countMap = new HashMap<>(); 
        	countMap.put("count", 100);
        	map.put("countMap", countMap);
        	System.out.println("---------------啦啦啦");
        }
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(count);
        count = 100;
        for (int i = 0; i < count; ++i) { // create and start threads
            new Thread(new Worker(startSignal, doneSignal, service, redissonClient,id)).start();
        }

        startSignal.countDown(); // let all threads proceed
        doneSignal.await();
        System.out.println("All processors done. Shutdown connection");

        return "finish";
    }
}
