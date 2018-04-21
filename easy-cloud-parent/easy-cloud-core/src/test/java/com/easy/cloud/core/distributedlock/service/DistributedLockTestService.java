package com.easy.cloud.core.distributedlock.service;

import java.util.Map;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.distributedlock.processor.DistributedLockTestProcessor;
import com.easy.cloud.core.lock.annotation.EcLockAnnotation;
import com.easy.cloud.core.lock.callback.result.processor.EcDefaultLockResultProcessor;
import com.easy.cloud.core.lock.constant.EcLockConstant.EcLockNameDesc;

@Service(value = "distributedLockTestService1")
@EcLogAnnotation(logSwitch = false, analysisSwitch = false)
public class DistributedLockTestService extends EcBaseService{
	@Autowired
	private RedissonClient redissonClient;

	@EcLockAnnotation(param = "id", namePre = EcLockNameDesc.LOCK_NAME_PRE_DEFAULT, resultProcessorClass = DistributedLockTestProcessor.class)
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

	@EcLockAnnotation(param = "id",resultProcessorClass = EcDefaultLockResultProcessor.class, namePre = EcLockNameDesc.LOCK_NAME_PRE_DEFAULT, tryLock = true)
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

	@EcLockAnnotation
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

	@EcLockAnnotation(argNum = 2, param = "id", nameSuffix = ".lock")
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

	@EcLockAnnotation(argNum = 1, nameSuffix = ".lock", tryLock = true)
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
