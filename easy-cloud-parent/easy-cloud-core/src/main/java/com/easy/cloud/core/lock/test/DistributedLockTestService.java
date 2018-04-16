package com.easy.cloud.core.lock.test;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.common.log.annotation.EcLog;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogLevelEnum;
import com.easy.cloud.core.lock.annotation.EcLock;
import com.easy.cloud.core.lock.callback.result.processor.EcDefaultLockResultProcessor;
import com.easy.cloud.core.lock.constant.EcLockConstant.EcLockNameDesc;
import com.easy.cloud.core.lock.constant.EcLockConstant.EcLockTemplateTypeEnum;

@Service(value = "distributedLockTestService1")
@EcLog(logSwitch = false, logAnalysisSwitch = false, logLevel = EcLogLevelEnum.INFO)
public class DistributedLockTestService {
	@Autowired
	private RedissonClient redissonClient;
	Lock lock = new ReentrantLock();

//	@EcLock(param = "id", namePre = EcLockNameDesc.LOCK_NAME_PRE_DEFAULT, templateType = EcLockTemplateTypeEnum.JDK)
	public Integer aspectUseJdk(Map<String, Object> person) {
		System.out.println("-------------使用@EcLock========");
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i  = DistributedLockTestController.count1 --;
		return i;
	}
//	@EcLock(param = "id", namePre = EcLockNameDesc.LOCK_NAME_PRE_DEFAULT, templateType = EcLockTemplateTypeEnum.JDK)
	public synchronized Integer aspectUseSycor(Map<String, Object> person) {
		System.out.println("-------------使用synchronized========");
		int i  = DistributedLockTestController.count1 --;
		return i;
	}
	@EcLock(param = "id", namePre = EcLockNameDesc.LOCK_NAME_PRE_DEFAULT, resultProcessorClass = DistributedLockTestProcessor.class)
	public Integer aspect(Map<String, Object> person) {
		
		RMap<String, Object> map = redissonClient.getMap("distributionTest");

		Map<String, Integer> countMap = (Map<String, Integer>) map.get("countMap");
		Integer count = countMap.get("count");
		if (count > 0) {
			count = count - 1;
			countMap.put("count", count);
			map.put("countMap", countMap);
		}

		return count;
	}

	@EcLock(param = "id", resultProcessorClass = EcDefaultLockResultProcessor.class, namePre = EcLockNameDesc.LOCK_NAME_PRE_DEFAULT, tryLock = true)
	public Integer aspectTryLock(Map<String, Object> person) {
		RMap<String, Object> map = redissonClient.getMap("distributionTest");

		Map<String, Integer> countMap = (Map<String, Integer>) map.get("countMap");
		Integer count = countMap.get("count");
		if (count > 0) {
			count = count - 1;
			countMap.put("count", count);
			map.put("countMap", countMap);
		}

		return count;
	}

	@EcLock
	public Integer aspect(String id, String userName) {
		RMap<String, Object> map = redissonClient.getMap("distributionTest");

		Map<String, Integer> countMap = (Map<String, Integer>) map.get("countMap");
		Integer count = countMap.get("count");
		if (count > 0) {
			count = count - 1;
			countMap.put("count", count);
			map.put("countMap", countMap);
		}

		return count;
	}

	@EcLock(argNum = 2, param = "id", nameSuffix = ".lock")
	public Integer aspect(String id, Map<String, Object> person) {
		RMap<String, Object> map = redissonClient.getMap("distributionTest");
		Map<String, Integer> countMap = (Map<String, Integer>) map.get("countMap");
		Integer count = countMap.get("count");
		if (count > 0) {
			count = count - 1;
			countMap.put("count", count);
			map.put("countMap", countMap);
		}
		return count;
	}

	@EcLock(argNum = 1, nameSuffix = ".lock", tryLock = true)
	public Integer aspectTryLock(String i, String userName) {
		RMap<String, Object> map = redissonClient.getMap("distributionTest");

		Map<String, Integer> countMap = (Map<String, Integer>) map.get("countMap");
		Integer count = countMap.get("count");
		if (count > 0) {
			count = count - 1;
			countMap.put("count", count);
			map.put("countMap", countMap);
		}

		return count;
	}
	// @EcDistributedLock(lockName = "lock", lockNameSuffix = ".lock")
	// public int aspect(Action<Integer> action) {
	// return action.action();
	// }
}
