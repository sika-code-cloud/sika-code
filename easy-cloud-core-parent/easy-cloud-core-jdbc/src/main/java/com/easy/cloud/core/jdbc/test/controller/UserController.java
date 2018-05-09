
package com.easy.cloud.core.jdbc.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.common.thread.factory.EcExecutors;
import com.easy.cloud.core.jdbc.test.UserEntity;
import com.easy.cloud.core.jdbc.test.UserService;

@RestController
@RequestMapping("/userController")
@EcLogAnnotation(logSwitch = false, analysisSwitch = false)
public class UserController extends EcBaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "testListPage")
	@ResponseBody
	public void testListPage() {
		List<UserEntity> retList = userService.listPage(1, 10);
		System.out.println("-------------------" + retList.size());
		for (UserEntity userEntity : retList) {
			System.out.println("-------------------" + EcJSONUtils.toJSONString(userEntity));
		}
	}

	@RequestMapping(value = "testSave")
	@ResponseBody
	public void testSave() {
		String userName = "username11111111";
		String password = "password333333333";
		Integer status = 2;
		ExecutorService threadPoolExecutor = EcExecutors.newFixedThreadPool(10);
		CountDownLatch startSignal = new CountDownLatch(1);
		long startime = System.currentTimeMillis();
		for (int i = 0 ;i < 1000; ++i) {
			threadPoolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					userService.saveUserEntity(new UserEntity(userName, password, status));
				}
			});
		}
		
		startSignal.countDown(); // let all threads proceed
		System.out.println("所用时间：" + (System.currentTimeMillis() - startime));
	}
	
	@RequestMapping(value = "testSavebatch")
	@ResponseBody
	public void testSavebatch() {
		String userName = "username11111111";
		String password = "password333333333";
		Integer status = 2;
		List<UserEntity> entitys = new ArrayList<>();
		for (int i = 0 ; i < 1 ; ++i) {
			entitys.add(new UserEntity(userName+i, password+i, status));
		}
		
		ExecutorService threadPoolExecutor = EcExecutors.newFixedThreadPool(10);
		CountDownLatch startSignal = new CountDownLatch(1);
		long startime = System.currentTimeMillis();
		for (int i = 0 ;i < 2000; ++i) {
//			threadPoolExecutor.execute(new Runnable() {
//				@Override
//				public void run() {
//					userService.saveUserEntity(new UserEntity(userName, password, status));
//				}
//			});
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					userService.saveBatch(entitys);
				}
			}).start();
		}
		
		startSignal.countDown(); // let all threads proceed
		System.out.println("所用时间：" + (System.currentTimeMillis() - startime));
	}

	@RequestMapping(value = "testUpdate")
	@ResponseBody
	public void testUpdate() {
		String userName = "earewrw";
		String password = "eeew22233";
		Integer status = 1;
		UserEntity userEntity = new UserEntity(userName, password, status);
		userEntity.setId(406871890473258206L);
		userService.updateUserEntity(userEntity);
	}

	@RequestMapping(value = "findById")
	@ResponseBody
	public void findById() {
		ExecutorService threadPoolExecutor = EcExecutors.newFixedThreadPool(10);
		CountDownLatch startSignal = new CountDownLatch(1);
		long startime = System.currentTimeMillis();
		for (int i = 0; i < 200; ++i) {
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