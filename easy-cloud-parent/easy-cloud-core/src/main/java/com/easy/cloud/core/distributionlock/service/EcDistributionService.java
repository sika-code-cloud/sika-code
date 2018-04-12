package com.easy.cloud.core.distributionlock.service;

import java.util.Map;

import javax.annotation.Resource;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.distributionlock.annotation.EcDistributedLock;

@Service
public class EcDistributionService {
	@Autowired
    private RedissonClient redissonClient;

    @EcDistributedLock(param = "id", lockNameSuffix = ".lock")
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

    @EcDistributedLock(argNum = 1, lockNameSuffix = ".lock")
    public Integer aspect(String i) {
        RMap<String, Integer> map = redissonClient.getMap("distributionTest");

        Integer count = map.get("count");

        if (count > 0) {
            count = count - 1;
            map.put("count", count);
        }

        return count;
    }

//    @EcDistributedLock(lockName = "lock", lockNameSuffix = ".lock")
//    public int aspect(Action<Integer> action) {
//        return action.action();
//    }
}
