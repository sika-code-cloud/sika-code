package com.easy.cloud.core.lock.test;

import java.util.Map;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.common.log.annotation.EcLog;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogLevelEnum;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.lock.annotation.EcLock;
import com.easy.cloud.core.lock.constant.EcLockConstant.EcLockTemplateTypeEnum;
import com.easy.cloud.core.lock.constant.EcLockConstant.EcLockTypeEnum;

@Service(value = "distributedLockTestService1")
@EcLog(logSwitch = false, logAnalysisSwitch = false, logLevel = EcLogLevelEnum.INFO)
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
	@EcLock(param = "id", argNum = 2, namePre = "lock", resultProcessorClass = LockTestProcessor.class,type = EcLockTypeEnum.UNFAIR , templateType = EcLockTemplateTypeEnum.REDISSION)
	public Integer pointAll(String userName, Map<String, Object> person) {
		RMap<String, Integer> countMap = redissonClient.getMap("count");
		Integer count = countMap.get("count");
		if (count > 0) {
			count = count - 1;
			countMap.put("count", count);
		}
		return count;
	}
	
	@EcLock(param = "id")
	public Integer pointParam(Map<String, Object> person, String userName) {
		
		RMap<String, Integer> countMap = redissonClient.getMap("count");
		Integer count = countMap.get("count");
		if (count > 0) {
			count = count - 1;
			countMap.put("count", count);
		}
		return count;
	}
	
	@EcLock
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
