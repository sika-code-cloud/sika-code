package com.easy.cloud.core.jdbc.user.auditor;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.thread.factory.EcExecutors;
import com.easy.cloud.core.common.thread.pool.EcThreadPoolExecutor;
import com.easy.cloud.core.jdbc.EcCoreJdbcApplication;
import com.easy.cloud.core.jdbc.user.entity.UserEntity;
import com.easy.cloud.core.jdbc.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EcCoreJdbcApplication.class) // 指定spring-boot的启动类
public class JdbcTemplateTests {
	@Autowired
	UserService userService;

	@Test
	public void testListPage() {
		List<UserEntity> retList = userService.listPage(1, 10);
		System.out.println("-------------------" + retList.size());
		for (UserEntity userEntity : retList) {
			System.out.println("-------------------" + EcJSONUtils.toJSONString(userEntity));
		}
	}

	@Test
	public void testSave() {
		String userName = "username11111111";
		String password = "password333333333";
		Integer status = 2;
		ExecutorService threadPoolExecutor = EcExecutors.newFixedThreadPool(10);
		CountDownLatch startSignal = new CountDownLatch(1);
		long startime = System.currentTimeMillis();
			threadPoolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					userService.saveUserEntity(new UserEntity(userName, password, status));
				}
			});
		startSignal.countDown(); // let all threads proceed
		System.out.println("所用时间：" + (System.currentTimeMillis() - startime));
	}

	@Test
	public void testUpdate() {
		String userName = "earewrw";
		String password = "eeew22233";
		Integer status = 1;
		UserEntity userEntity = new UserEntity(userName, password, status);
		userEntity.setId(406871890473258206L);
		userService.updateUserEntity(userEntity);
	}
	@Test
	public void findById() {
		ExecutorService threadPoolExecutor = EcExecutors.newFixedThreadPool(10);
		CountDownLatch startSignal = new CountDownLatch(1);
		long startime = System.currentTimeMillis();
		for (int i = 0 ; i < 3; ++i) {
			threadPoolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					userService.findById(406871890473258119L);
				}
			});
		}
		startSignal.countDown(); // let all threads proceed
		System.out.println("所用时间：" + (System.currentTimeMillis() - startime));
	}
}
 