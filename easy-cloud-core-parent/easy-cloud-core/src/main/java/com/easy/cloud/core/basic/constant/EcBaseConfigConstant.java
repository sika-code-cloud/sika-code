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
}
