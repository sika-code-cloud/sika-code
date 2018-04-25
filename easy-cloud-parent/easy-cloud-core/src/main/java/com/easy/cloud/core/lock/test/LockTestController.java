
package com.easy.cloud.core.lock.test;

import java.util.ArrayList;
import java.util.List;
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
	private int count =800;
	@Autowired
	private RedissonClient redissonClient;

	@Autowired
	private LockTestService service;

	@RequestMapping(value = "distributedLockTest")
	@ResponseBody
	public String distributedLockTest(@RequestParam Map<String, Object> paramsMap) throws Exception {
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(count);
		String id = EcMapUtils.getString(paramsMap, "id");
		String countStr = "count";
		if ("11".equals(id)) {
			countStr = "count1";
		}
		RMap<String, Integer> countMap = redissonClient.getMap(countStr);
		countMap.put(countStr, count);
		List<Thread> list = new ArrayList<>();
		// 创建线程
		for (int i = 0; i < count; ++i) { // create and start threads
			list.add(new Thread(new LockTestWorker(startSignal, doneSignal, service, redissonClient,id)));
		}
		long startime = System.currentTimeMillis();
		// 开始线程
		for (int i = 0; i < list.size(); ++i) { // create and start threads
			list.get(i).start();
		}

		startSignal.countDown(); // let all threads proceed
		doneSignal.await();
		System.out.println("所用时间：" + (System.currentTimeMillis() - startime ));
		System.out.println("All processors done. Shutdown connection");
		return "finish";
	}
}