package com.dq.easy.cloud.model.jdbc.constant;

/**
 * 
 * @ClassName : DqJdbcConstant 
 * @Description : 常量类 
 * @author daiqi
 * @date 2017年12月6日 上午11:14:59 
 *
 */
public class DqJdbcConstant {
	/**在properties中数据库key---url的key---spring.datasource.url*/
	public static final String DATA_BASE_URL_KEY = "spring.datasource.url";
	
	/**在properties中数据库key---username的key---spring.datasource.username*/
	public static final String DATA_BASE_USERNAME_KEY = "spring.datasource.username";
	
	/**在properties中数据库key---password的key---spring.datasource.password*/
	public static final String DATA_BASE_PASSWORD_KEY = "spring.datasource.password";
	
	/**在properties中数据库key---数据库驱动的key---spring.datasource.driver-class-name*/
	public static final String DATA_BASE_DRIVER_CLASS_NAME_KEY = "spring.datasource.driver-class-name";
	
	/**在properties中数据库key---连接池的初始化连接数量的key---spring.datasource.initial-size*/
	public static final String DATA_BASE_INITIAL_SIZE_KEY = "spring.datasource.initial-size";
	
	/**在properties中数据库key---连接池的初始化最大活动连接数量的key---spring.datasource.max-active*/
	public static final String DATA_BASE_MAX_ACTIVE_KEY = "spring.datasource.max-active";
	
	/**在properties中数据库key---连接池的初始化最大等待的key---spring.datasource.max-wait*/
	public static final String DATA_BASE_MAX_WAIT_KEY = "spring.datasource.max-wait";
	
	/**在properties中数据库key---连接池的初始化min-Idle的key---spring.datasource.min-idle*/
	public static final String DATA_BASE_MIN_IDLE_KEY = "spring.datasource.min-idle";
	
	/**在properties中数据库key---连接校验sql的key---spring.datasource.validation-query*/
	public static final String DATA_BASE_VALIDATION_QUERY_KEY = "spring.datasource.validation-query";
	
	/**在properties中数据库key---test-on-borrow的key---spring.datasource.test-on-borrow*/
	public static final String DATA_BASE_TEST_ON_BORROW_KEY = "spring.datasource.test-on-borrow";
	
	/**在properties中数据库key---test-while-idle的key---spring.datasource.test-while-idle*/
	public static final String DATA_BASE_TEST_WHILE_IDLE_KEY = "spring.datasource.test-while-idle";
	
	/**在properties中数据库key---pool-prepared-statements的key---spring.datasource.pool-prepared-statements*/
	public static final String DATA_BASE_POOL_PREPARED_STATEMENTS_KEY = "spring.datasource.pool-prepared-statements";
}
