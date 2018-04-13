package com.easy.cloud.core.distributedlock.service;

import java.util.Map;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.distributedlock.annotation.EcDistributedLock;
import com.easy.cloud.core.distributedlock.constant.EcDistributedLockConstant.EcDistributedLockNameDesc;

@Service(value = "distributedLockTestService1")
public class DistributedLockTestService {
	@Autowired
	private RedissonClient redissonClient;

	@EcDistributedLock(param = "id", lockNamePre = EcDistributedLockNameDesc.LOCK_NAME_PRE_DEFAULT)
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

	@EcDistributedLock(param = "id", lockNamePre = EcDistributedLockNameDesc.LOCK_NAME_PRE_DEFAULT, tryLock = true)
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

	@EcDistributedLock
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

	@EcDistributedLock(argNum = 2, param = "id", lockNameSuffix = ".lock")
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

	@EcDistributedLock(argNum = 1, lockNameSuffix = ".lock", tryLock = true)
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
