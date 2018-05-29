package com.easy.cloud.core.lock.test;

import java.util.Map;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogLevelEnum;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.lock.annotation.EcLockAnnotation;
import com.easy.cloud.core.lock.constant.EcLockConstant.EcLockTemplateTypeEnum;
import com.easy.cloud.core.lock.constant.EcLockConstant.EcLockTypeEnum;

@Service(value = "distributedLockTestService1")
@EcLogAnnotation(logSwitch = false, analysisSwitch = false, level = EcLogLevelEnum.INFO)
public class LockTestService {
	@Autowired
	private RedissonClient redissonClient;

	/**
	 * 
	 * <p>
	 * 不加锁注解的测试
	 * </p>
	 */
	public Integer unLock(String id, Map<String, Object> person) {
		RMap<String, Integer> countMap = redissonClient.getMap("count");
		Integer count = countMap.get("count");
		if (count > 0) {
			count = count - 1;
			countMap.put("count", count);
		}
		return count;
	}

	/**
	 * 
	 * <p>
	 * 指定注解所有的属性测试
	 * </p>
	 *
	 * <pre>
	 *     param : String : 指定作为锁名的的参数的key
	 *     param : int : 使用形参中第几个作为key的选择对、从1开始
	 *     namePre : String : 锁名称的前缀
	 *     resultProcessorClass : LockTestProcessor : 指定结果处理类的class
	 *     type : EcLockTypeEnum : 指定锁的类型--公平锁、非公平锁等等
	 *     templateType : EcLockTemplateTypeEnum : 指定锁的模板类型，如使用redisson以及数据库等等
	 * </pre>
	 *
	 * @param userName
	 * @param person
	 * @return
	 * @author daiqi
	 * @创建时间 2018年4月16日 下午4:08:38
	 */
	@EcLockAnnotation(param = "id", argNum = 2, namePre = "lock", resultProcessorClass = LockTestProcessor.class, type = EcLockTypeEnum.UNFAIR, templateType = EcLockTemplateTypeEnum.REDISSION)
	public Integer pointAllUseRedisson(String userName, Map<String, Object> person) {
		String countStr = "count";
		if (person.get("id").equals("11")) {
			countStr = "count1";
		}
		RMap<String, Integer> countMap = redissonClient.getMap(countStr);
		Integer count = countMap.get(countStr);
		if (count > 0) {
			count = count - 1;
			countMap.put(countStr, count);
		}
		return count;
	}

	@EcLockAnnotation(param = "id", argNum = 2, namePre = "lock", resultProcessorClass = LockTestProcessor.class, type = EcLockTypeEnum.UNFAIR, templateType = EcLockTemplateTypeEnum.JEDIS)
	public Integer pointAllUseJedis(String userName, Map<String, Object> person) {
		String countStr = "count";
		if (person.get("id").equals("11")) {
			countStr = "count1";
		}
		RMap<String, Integer> countMap = redissonClient.getMap(countStr);
		Integer count = countMap.get(countStr);
		if (count > 0) {
			count = count - 1;
			countMap.put(countStr, count);
		}
		return count;
	}

	@EcLockAnnotation(param = "id", argNum = 2, namePre = "lock", resultProcessorClass = LockTestProcessor.class, type = EcLockTypeEnum.UNFAIR, templateType = EcLockTemplateTypeEnum.JEDIS)
	public Integer pointAllUseJedis1(String userName, Map<String, Object> person) {
		String countStr = "count";
		if (person.get("id").equals("11")) {
			countStr = "count1";
		}
		RMap<String, Integer> countMap = redissonClient.getMap(countStr);
		Integer count = countMap.get(countStr);
		if (count > 0) {
			count = count - 1;
			countMap.put(countStr, count);
		}
		return count;
	}

	@EcLockAnnotation(param = "id", argNum = 2, namePre = "lock", tryLock = true, resultProcessorClass = LockTestProcessor.class, type = EcLockTypeEnum.UNFAIR, templateType = EcLockTemplateTypeEnum.REDISSION)
	public Integer pointAllTry(String userName, Map<String, Object> person) {
		RMap<String, Integer> countMap = redissonClient.getMap("count");
		Integer count = countMap.get("count");
		if (count > 0) {
			count = count - 1;
			countMap.put("count", count);
		}
		return count;
	}

	@EcLockAnnotation(param = "id")
	public Integer pointParam(Map<String, Object> person, String userName) {

		RMap<String, Integer> countMap = redissonClient.getMap("count");
		Integer count = countMap.get("count");
		if (count > 0) {
			count = count - 1;
			countMap.put("count", count);
		}
		return count;
	}

	@EcLockAnnotation
	public Integer useDefault(String id, String userName) {
		String key = "count";
		if (EcStringUtils.equals(id, "11")) {
			key = "count1";
		}
		RMap<String, Integer> countMap = redissonClient.getMap(key);
		Integer count = countMap.get(key);
		if (count > 0) {
			count = count - 1;
			countMap.put(key, count);
		}
		return count;
	}

}
