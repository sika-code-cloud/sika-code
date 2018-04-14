package com.easy.cloud.core.distributedlock.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.common.map.utils.EcMapUtils;

@RestController(value = "distributedLockTestController1")
@RequestMapping("/distributedLockTest")
public class DistributedLockTestController {
	private int count = 10;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private DistributedLockTestService service;

    @RequestMapping(value = "distributedLockTest")
    @ResponseBody
    public String distributedLockTest(@RequestParam Map<String, Object> paramsMap) throws Exception {

        RMap<String, Object> map = redissonClient.getMap("distributionTest");
        Map<String, Integer> countMap = (Map<String, Integer>) map.get("countMap");
        if (countMap.get("count") == null || countMap.get("count") == 0) {
        	countMap = new HashMap<>(); 
        	countMap.put("count", 1000);
        	map.put("countMap", countMap);
        	System.out.println("---------------啦啦啦");
        }
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(count);
        count = 200;
        String id = EcMapUtils.getString(paramsMap, "id");
        Map<String, Object> person = EcMapUtils.newHashMap();
        if (id == null) {
        	id = "10";
        }
        person.put("id", Integer.parseInt(id));
        person.put("name", "张三");
        for (int i = 0; i < count; ++i) { // create and start threads
//        	Integer count1 = service.aspectTryLock(person);
//        	System.out.println("count:" + count1);
            new Thread(new DistributedLockTestWorker(startSignal, doneSignal, service, redissonClient,EcMapUtils.getString(paramsMap, "id"))).start();
        }
//        new Thread(new Worker(startSignal, doneSignal, service, redissonClient,id)).start();

        startSignal.countDown(); // let all threads proceed
        doneSignal.await();
        System.out.println("All processors done. Shutdown connection");

        return "finish";
    }
}
