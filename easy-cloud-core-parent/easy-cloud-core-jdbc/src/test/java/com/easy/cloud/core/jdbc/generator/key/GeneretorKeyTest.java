package com.easy.cloud.core.jdbc.generator.key;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;
import com.easy.cloud.core.jdbc.primarykey.snowflake.EcSnowflakeIdAlgorithm;
import com.easy.cloud.core.jdbc.user.entity.UserEntity;

public class GeneretorKeyTest {
	ExecutorService executorService = Executors.newFixedThreadPool(2000);

	@Test
	public void testGeneratorKey() {
		EcSnowflakeIdAlgorithm snowflakeIdWorkerBO = EcSnowflakeIdAlgorithm.getSingleInstance(0, 1);
		CountDownLatch startSignal = new CountDownLatch(1);
		long startime = System.currentTimeMillis();

		for (int i = 0; i < 1000; ++i) {
			executorService.execute(new Runnable() {
				public void run() {
					System.out.println(snowflakeIdWorkerBO.nextId());
				}
			});
		}
		startSignal.countDown(); // let all threads proceed
		System.out.println("所用时间：" + (System.currentTimeMillis() - startime));
	}
	@Test
	public void testGeneratorKey1() {
		System.out.println(3|5);
		System.out.println(7^5);
		UserEntity u = new UserEntity();
		System.out.println(u instanceof EcBaseEntity);
		System.out.println(EcBaseEntity.class.isAssignableFrom(EcBaseEntity.class));
	}
}
