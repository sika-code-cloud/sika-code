package com.easy.cloud.core.lock.test;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.redisson.api.RedissonClient;
import com.easy.cloud.core.common.map.utils.EcMapUtils;

public class LockTestWorker implements Runnable {
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;
	private final LockTestService service;
	private String id;

	public LockTestWorker(CountDownLatch startSignal, CountDownLatch doneSignal, LockTestService service,
			RedissonClient redissonClient) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
		this.service = service;
	}

	public LockTestWorker(CountDownLatch startSignal, CountDownLatch doneSignal, LockTestService service,
			RedissonClient redissonClient, String id) {
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
			Integer randomNum = 10 + (int)(Math.random()*10);
			String randomId = "10";
			if (randomNum >= 15) {
				randomId = "11";
			}
			person.put("id", randomId);
			person.put("name", "张三");
			// 不加注解锁测试
//			Integer count = service.unLock(id, person);
			// 指定注解锁所有的参数
//			Integer count = service.pointAll(id, person);
			// 指定注解的param属性
//			 Integer count = service.pointParam(person, "张三");
			// 使用注解的默认的参数
			 Integer count = service.useDefault(randomId, "张三");
			System.out.println(Thread.currentThread().getName() + ": count = " + count + " id :" + randomId);
			doneSignal.countDown();

		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}