package com.easy.cloud.core.lock.test;

import java.util.Map;
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
public class LockTestController {
	private int count = 400;
	@Autowired
	private RedissonClient redissonClient;

	@Autowired
	private LockTestService service;

	@RequestMapping(value = "distributedLockTest")
	@ResponseBody
	public String distributedLockTest(@RequestParam Map<String, Object> paramsMap) throws Exception {
		long startime = System.currentTimeMillis();
		RMap<String, Integer> countMap = redissonClient.getMap("count");
		countMap.put("count", 800);
		RMap<String, Integer> countMap1 = redissonClient.getMap("count1");
		countMap1.put("count1", count);
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(count);
		String id = EcMapUtils.getString(paramsMap, "id");
		for (int i = 0; i < count; ++i) { // create and start threads
			new Thread(new LockTestWorker(startSignal, doneSignal, service, redissonClient,id)).start();
		}

		startSignal.countDown(); // let all threads proceed
		doneSignal.await();
		System.out.println("所用时间：" + (System.currentTimeMillis() - startime ));
		System.out.println("All processors done. Shutdown connection");
		return "finish";
	}
}
