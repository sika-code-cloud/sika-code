package com.easy.cloud.core.cache.redis.constant;

/**
 * 
 * @ClassName : DqRedisConstant 
 * @Description : reids常量类 
 * @author daiqi
 * @date 2017年12月7日 下午5:50:02 
 *
 */
public class EcRedisConstant {
	
	/**
	 * redisTemplate的注入名称
	 * @author daiqi
	 * @date 2018年4月25日 下午11:58:45
	 */
	public static class EcRedisTemplateName {
		/**容器注入RedisTemplate类对象的名称---值为字符串的redisTemplate---stringRedisTemplate*/
		public static final String REDIS_TEMPLATE_VALUE_STR_NAME = "stringRedisTemplate";
		/**容器注入RedisTemplate类对象的名称---值为序列化对象的redisTemplate---redisTemlateValueSerializer*/
		public static final String REDIS_TEMPLATE_VALUE_SERIALIZER_NAME = "redisTemlateValueSerializer";
		/**容器注入RedisTemplate类锁对象的名称---值为字符串的lockTemplateJedis---lockTemplateJedis*/
		public static final String REDIS_TEMPLATE_VALUE_STR_LOCK_NAME = "lockTemplateJedis";
	}
	
	
	/**
	 * 执行redis的Action类型
	 * @author daiqi
	 * @date 2018年4月25日 下午11:48:03
	 */
	public static enum EcRedisActionType {
		/** action---查询 */
		QUERY(1, "query"),
		/** action---更新 */
		UPDATE(2, "update"),
		/** action---保存 */
		SAVE(3, "save"),
		/** action---删除 */
		DELETE(4, "delete"),
		;
		private int type;
		private String value;
		
		private EcRedisActionType(int type, String value) {
			this.type = type;
			this.value = value;
		}
		public int getType() {
			return type;
		}
		public String getValue() {
			return value;
		}
		
	}
}
