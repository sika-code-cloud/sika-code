package com.easy.cloud.core.cache.redis.utils;

import java.util.ArrayList;
import java.util.List;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.array.EcArrayUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * redis工具类
 * @author daiqi
 * @date 2018年4月26日 下午10:10:55
 */
public class EcRedisUtils {
	/**
	 * 
	 * <p>生成统一格式以英文冒号分割的redis主键key</p>
	 *
	 * <pre>
	 *     所有redis的key应该使用此方法生成统一的主key
	 *     若keyPrefix == null || keyBody == null都将返回null
	 * </pre>
	 * <pre>
	 *     EcRedisUtils.generateRedisKey(null, 1234, "132") = null
	 *     EcRedisUtils.generateRedisKey(null, null, "132") = null
	 * 	   EcRedisUtils.generateRedisKey("user", null) = null
	 *     EcRedisUtils.generateRedisKey("user", 1234) = user:1234
	 *     EcRedisUtils.generateRedisKey("user", "zhangsan", "132") = user:zhangsan:132
	 * </pre>
	 *
	 * @param keyPrefix : String : key的前缀 : 可以按照模块进行划分
	 * @param keyBody : Object : key的主体 : 建议能唯一区分的值
	 * @param keyArgs : String... : 可变参数数组 : 若以上两个不能满足需求可以继续进行添加 
	 * @return 
	 *
	 * author daiqi
	 * 创建时间  2018年4月26日 下午10:40:07
	 */
	public static String generateRedisKey(String keyPrefix, Object keyBody, String...keyArgs) {
		if (EcStringUtils.isEmpty(keyPrefix)) {
			return null;
		}
		if (EcBaseUtils.isNull(keyBody)) {
			return null;
		}
		List<String> argList = getRedisKeyList(keyPrefix, String.valueOf(keyBody), keyArgs);
		// 将arrayList转换为数组
		String [] args = argList.toArray(new String [argList.size()]);
		return EcStringUtils.generateKeyUseColonSeparator(args);
	}
	
	/** 获取rediskey列表 */
	private static List<String> getRedisKeyList(String keyPrefix, String keyBody, String...keyArgs) {
		List<String> argList = new ArrayList<>();
		argList.add(keyPrefix);
		argList.add(String.valueOf(keyBody));
		if (EcArrayUtils.isNotEmpty(keyArgs)) {
			for (String keyArg : keyArgs) {
				argList.add(keyArg);
			}
		}
		return argList;
	}
	
}
