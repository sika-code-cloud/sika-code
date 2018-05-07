package com.easy.cloud.core.basic.constant;

/**
 * 
 * <p>
 * 所有的配置性常量类
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * @创建时间 2018年5月4日 下午4:26:44
 */
public class EcBaseConfigConstant {
	/** 应用名称的key */
	public static final String APPLICATION_NAME_KEY = "spring.application.name";
	
	/** log注解所在完整类名 */
	public static final String LOG_ANNOTATION_NAME = "com.easy.cloud.core.common.log.annotation.EcLogAnnotation";
	/** 锁注解完整类名 */
	public static final String LOCK_ANNOTATION_NAME = "com.easy.cloud.core.lock.annotation.EcLockAnnotation";
	/** redis注解完整类名 */
	public static final String REDIS_ANNOTATION_NAME = "com.easy.cloud.core.cache.redis.annotation.EcRedisAnnotation";
	/** 雪花算法主键生成器所在完整类名 */
	public static final String SNOW_FLAKELD_WORLKER_NAME = "com.easy.cloud.core.jdbc.base.primarykey.EcKeyGeneratSnowflakeldWorlker";
	
	/**
	 * 
	 * <p>
	 * ec条件注解的开关key
	 * </p>
	 *
	 * @author daiqi
	 * @创建时间 2018年5月7日 下午5:13:37
	 */
	public static class EcConditionalSwitchKey {
		/** 日志开关key---ec.log.switch */
		public static final String LOG_SWITCH = "ec.log.switch";
		/** 审计开关key---ec.audit.switch */
		public static final String AUDIT_SWITCH = "ec.audit.switch";
		/** jdbc开关key---ec.jdbc.switch */
		public static final String JDBC_SWITCH = "ec.jdbc.switch";
		/** redis开关key---ec.redis.switch */
		public static final String REDIS_SWITCH = "ec.redis.switch";
		/** lock开关key---ec.lock.switch */
		public static final String LOCK_SWITCH = "ec.lock.switch";
	}
}
