package com.easy.cloud.core.lock.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.common.map.utils.EcMapUtils;

@RestController(value = "distributedLockTestController1")
@RequestMapping("/distributedLockTest")
public class DistributedLockTestController {
	private int count = 10;
	public static int count1 = 200;
    @Autowired
    private RedissonClient redissonClient;
    
    public static Map<Integer, Object> maps ;

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
        count1 = 1000;
        maps = new ConcurrentHashMap<>();
        person.put("id", Integer.parseInt(id));
        person.put("name", "张三");
        List<Thread> tList = new ArrayList<>();
        for (int i = 0; i < count; ++i) { // create and start threads
//        	Integer count1 = service.aspectTryLock(person);
//        	System.out.println("count:" + count1);
        	tList.add(new Thread(new DistributedLockTestWorker(startSignal, doneSignal, service, redissonClient,EcMapUtils.getString(paramsMap, "id"))));
        }
//        Thread.sleep(20);
    	for (int i = 0; i < tList.size(); ++i) { // create and start threads
    		System.out.println("------------threadId" + tList.get(i).getName());
    		tList.get(i).start();
    	}
//        new Thread(new DistributedLockTestWorker(startSignal, doneSignal, service, redissonClient,EcMapUtils.getString(paramsMap, "id"))).start();
//        new Thread(new Worker(startSignal, doneSignal, service, redissonClient,id)).start();

        startSignal.countDown(); // let all threads proceed
        doneSignal.await();
        Thread.sleep(10000);
        System.out.println("All processors done. Shutdown connection");
        Map<Integer, Object> treemap = new TreeMap<>();
        for(Integer key : maps.keySet()) {
        	treemap.put(key, maps.get(key));
        }
        for(Integer key : treemap.keySet()) {
        	System.out.println("--------------count的值" + key);
        }
        System.out.println("集合的size()" + maps.size());
        return "finish";
    }
}
