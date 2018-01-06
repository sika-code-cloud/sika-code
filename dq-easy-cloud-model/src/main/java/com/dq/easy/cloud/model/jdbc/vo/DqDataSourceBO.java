package com.dq.easy.cloud.model.jdbc.vo;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;

import com.dq.easy.cloud.model.jdbc.constant.DqJdbcConstant;

/**
 * 所有dataSource的基类
 * @ClassName : DqDataSourceBO 
 * @Description : dataSource基逻辑对象 
 * @author daiqi
 * @date 2017年12月6日 上午10:09:58 
 *
 */
public abstract class DqDataSourceBO {
	
//	数据源对象
	protected static DataSource dataSource;
	
	protected Environment environment;
	
//	数据源基础信息
	protected String url;
	protected String username;
	protected String password;
	protected String driverClassName;
	
//	连接池的参数信息
	protected Integer initialSize;
	protected Integer maxActive;
	protected Integer minIdle;
	protected Integer maxWait;
	protected String validationQuery;
	protected Boolean testOnBorrow;
	protected Boolean testWhileIdle;
	protected Boolean poolPreparedStatements;
	
	
	protected DqDataSourceBO() {
		initDataSource();
	}
	protected DqDataSourceBO(Environment environment) {
		this.environment = environment;
		initDataSource();
	}
	
	
	/**
	 * 
	 * <p>初始化数据源信息</p>
	 *
	 * @author daiqi
	 * @date 2017年12月6日 上午11:36:42
	 */
	private void initDataSource(){
//		初始化实际的数据源对象
		initActualDataSourceBean();
//		初始化连接信息
		initDataSourceLinkInfo();
//		初始化连接池参数信息
		initDataSourceParamsInfo();
//		初始化bean--将实际数据源对象赋予dataSource引用
		initDataSourceBean();
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * 
	 * <p>初始化实际上的数据源对象</p>
	 *
	 * <pre>
	 * </pre>
	 *
	 * @author daiqi
	 * @date 2017年12月6日 上午11:49:46
	 */
	protected abstract void initActualDataSourceBean();
	/**
	 * 
	 * <p>初始化dataSourceBean的信息</p>
	 *
	 * <pre>
	 * 有子类选择不同的dataSource实现
	 * </pre>
	 *
	 * @author daiqi
	 * @date 2017年12月6日 上午10:48:35
	 */
	protected abstract void initDataSourceBean();
	/**
	 * 
	 * <p>初始化dataSource的连接信息</p>
	 *
	 * <pre>
	 * 默认连接mysql--子类可以通过重写此方法改变连接方式,默认从配置文件中读取,子类可以重写
	 * </pre>
	 *
	 * @author daiqi
	 * @date 2017年12月6日 上午10:49:22
	 */
	protected void initDataSourceLinkInfo(){
		this
		.buildUrl(environment.getProperty(DqJdbcConstant.DATA_BASE_URL_KEY))
		.buildUsername(environment.getProperty(DqJdbcConstant.DATA_BASE_USERNAME_KEY))
		.buildPassword(environment.getProperty(DqJdbcConstant.DATA_BASE_PASSWORD_KEY))
		.buildDriverClassName(environment.getProperty(DqJdbcConstant.DATA_BASE_DRIVER_CLASS_NAME_KEY))
		;
	}
	/**
	 * 
	 * <p>初始化dataSource的参数信息包括activeSize等等,默认从配置文件中读取,子类可以重写</p>
	 *
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @author daiqi
	 * @date 2017年12月6日 上午10:52:06
	 */
	protected void initDataSourceParamsInfo(){
		this
		.buidInitialSize(environment.getProperty(DqJdbcConstant.DATA_BASE_INITIAL_SIZE_KEY,Integer.class))
		.buildMaxActive(environment.getProperty(DqJdbcConstant.DATA_BASE_MAX_ACTIVE_KEY,Integer.class))
		.buildMaxWait(environment.getProperty(DqJdbcConstant.DATA_BASE_MAX_WAIT_KEY,Integer.class))
		.buildPoolPreparedStatements(environment.getProperty(DqJdbcConstant.DATA_BASE_POOL_PREPARED_STATEMENTS_KEY,Boolean.class))
		.buildTestOnBorrow(environment.getProperty(DqJdbcConstant.DATA_BASE_TEST_ON_BORROW_KEY,Boolean.class))
		.buildTestWhileIdle(environment.getProperty(DqJdbcConstant.DATA_BASE_TEST_WHILE_IDLE_KEY,Boolean.class))
		.buildValidationQuery(environment.getProperty(DqJdbcConstant.DATA_BASE_VALIDATION_QUERY_KEY))
		.buildMinIdle(environment.getProperty(DqJdbcConstant.DATA_BASE_MIN_IDLE_KEY,Integer.class))
		;
	}
	
	
	public DqDataSourceBO buidInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
		return this;
	}

	public DqDataSourceBO buildMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
		return this;
	}

	public DqDataSourceBO buildMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
		return this;
	}

	public DqDataSourceBO buildMaxWait(Integer maxWait) {
		this.maxWait = maxWait;
		return this;
	}

	public DqDataSourceBO buildValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
		return this;
	}

	public DqDataSourceBO buildTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
		return this;
	}

	public DqDataSourceBO buildTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
		return this;
	}

	public DqDataSourceBO buildPoolPreparedStatements(Boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
		return this;
	}

	public DqDataSourceBO buildUrl(String url){
		this.url = url;
		return this;
	}
	public DqDataSourceBO buildUsername(String username){
		this.username = username;
		return this;
	}
	public DqDataSourceBO buildPassword(String password){
		this.password = password;
		return this;
	}
	public DqDataSourceBO buildDriverClassName(String driverClassName){
		this.driverClassName = driverClassName;
		return this;
	}
	
}
