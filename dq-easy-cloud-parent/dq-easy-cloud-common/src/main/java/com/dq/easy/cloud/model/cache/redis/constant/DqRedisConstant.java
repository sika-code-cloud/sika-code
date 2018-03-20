package com.dq.easy.cloud.model.cache.redis.constant;

/**
 * 
 * @ClassName : DqRedisConstant 
 * @Description : reids常量类 
 * @author daiqi
 * @date 2017年12月7日 下午5:50:02 
 *
 */
public class DqRedisConstant {
	/**容器注入RedisTemplate类对象的名称---值为字符串的redisTemplate---stringRedisTemplate*/
	public static final String REDIS_TEMPLATE_VALUE_STR_NAME = "stringRedisTemplate";
	/**容器注入RedisTemplate类对象的名称---值为序列化对象的redisTemplate---redisTemlateValueSerializer*/
	public static final String REDIS_TEMPLATE_VALUE_SERIALIZER_NAME = "redisTemlateValueSerializer";
}
