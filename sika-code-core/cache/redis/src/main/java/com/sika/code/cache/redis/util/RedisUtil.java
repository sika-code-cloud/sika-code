package com.sika.code.cache.redis.util;


import com.sika.code.cache.redis.constant.BaseRedisKeyModular;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.array.ArrayUtil;
import com.sika.code.common.string.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * redis工具类
 * @author daiqi
 * @date 2018年4月26日 下午10:10:55
 */
public class RedisUtil {
	/**
	 *
	 * <p>生成统一格式以英文冒号分割的redis主键key</p>
	 *
	 * <pre>
	 *     所有redis的key应该使用此方法生成统一的主key
	 *     若keyPrefix == null || keyBody == null都将返回null
	 * </pre>
	 * <pre>
	 *     RedisUtil.generateRedisKey(null, 1234, "132") = null
	 *     RedisUtil.generateRedisKey(null, null, "132") = null
	 * 	   RedisUtil.generateRedisKey("sysuser", null) = null
	 *     RedisUtil.generateRedisKey("sysuser", 1234) = sysuser:1234
	 *     RedisUtil.generateRedisKey("sysuser", "zhangsan", "132") = sysuser:zhangsan:132
	 * </pre>
	 *
	 * @param redisKeyModular : BaseRedisKeyModular : 模块枚举类的实例 : 可以按照模块进行划分
	 * @param keyBody : Object : key的主体 : 子功能
	 * @param keyArgs : String... : 可变参数数组 : 若以上两个不能满足需求可以继续进行添加
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年4月26日 下午10:40:07
	 */
	public static String generateRedisKey(BaseRedisKeyModular redisKeyModular, Object keyBody, String...keyArgs) {
		if (redisKeyModular == null || StringUtil.isEmpty(redisKeyModular.getName())) {
			return null;
		}
		return generateRedisKey(redisKeyModular.getName(), keyBody, keyArgs);
	}

	/**
	 * 
	 * <p>生成统一格式以英文冒号分割的redis主键key</p>
	 *
	 * <pre>
	 *     所有redis的key应该使用此方法生成统一的主key
	 *     若keyPrefix == null || keyBody == null都将返回null
	 * </pre>
	 * <pre>
	 *     RedisUtil.generateRedisKey(null, 1234, "132") = null
	 *     RedisUtil.generateRedisKey(null, null, "132") = null
	 * 	   RedisUtil.generateRedisKey("sysuser", null) = null
	 *     RedisUtil.generateRedisKey("sysuser", 1234) = sysuser:1234
	 *     RedisUtil.generateRedisKey("sysuser", "zhangsan", "132") = sysuser:zhangsan:132
	 * </pre>
	 *
	 * @param keyPrefix : String : key的前缀 : 可以按照模块进行划分
	 * @param keyBody : Object : key的主体 : 子功能
	 * @param keyArgs : String... : 可变参数数组 : 若以上两个不能满足需求可以继续进行添加 
	 * @return 
	 *
	 * author daiqi
	 * 创建时间  2018年4月26日 下午10:40:07
	 */
	public static String generateRedisKey(String keyPrefix, Object keyBody, String...keyArgs) {
		if (StringUtil.isEmpty(keyPrefix)) {
			return null;
		}
		if (BaseUtil.isNull(keyBody)) {
			return null;
		}
		List<String> argList = getRedisKeyList(keyPrefix, String.valueOf(keyBody), keyArgs);
		// 将arrayList转换为数组
		String [] args = argList.toArray(new String [argList.size()]);
		return StringUtil.generateKeyUseColonSeparator(args);
	}
	
	/** 获取rediskey列表 */
	private static List<String> getRedisKeyList(String keyPrefix, String keyBody, String...keyArgs) {
		List<String> argList = new ArrayList<>();
		argList.add(keyPrefix);
		argList.add(String.valueOf(keyBody));
		if (ArrayUtil.isNotEmpty(keyArgs)) {
			for (String keyArg : keyArgs) {
				argList.add(keyArg);
			}
		}
		return argList;
	}
	
}
