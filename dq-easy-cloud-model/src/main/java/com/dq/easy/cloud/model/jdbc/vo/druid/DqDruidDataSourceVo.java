package com.dq.easy.cloud.model.jdbc.vo.druid;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;
import com.dq.easy.cloud.model.jdbc.vo.DqDataSourceVo;

/**
 * 
 * @ClassName : DqDruidDataSourceVo 
 * @Description : druid的数据配置信息Vo
 * @author daiqi
 * @date 2017年12月6日 上午10:09:13 
 *
 */
public class DqDruidDataSourceVo extends DqDataSourceVo{
	
	private DruidDataSource druidDataSource;
	
	/**
	 * 
	 * <p></p>
	 *
	 * <pre>
	 * </pre>
	 *
	 * @return
	 * @author daiqi
	 * @date 2017年12月6日 上午11:29:52
	 */
	public static DqDruidDataSourceVo newInstance(){
		return new DqDruidDataSourceVo();
	}
	/**
	 * 
	 * <p>从传入的Environment中获取属性</p>
	 *
	 * <pre>
	 * </pre>
	 *
	 * @param environment
	 * @return
	 * @author daiqi
	 * @date 2017年12月6日 下午3:54:56
	 */
	public static DqDruidDataSourceVo newInstance(Environment environment){
		return new DqDruidDataSourceVo(environment);
	}
	
	
	/**
	 * 
	 * <p>获取单例数据源</p>
	 *
	 * <pre>
	 * </pre>
	 *
	 * @return
	 * @author daiqi
	 * @date 2017年12月6日 下午1:13:07
	 */
	public static DataSource getSingleDataSource(){
		return newInstance().getDataSource();
	}
	public static DataSource getSingleDataSource(Environment environment){
		return newInstance(environment).getDataSource();
	}
	private DqDruidDataSourceVo(Environment environment) {
		super(environment);
	}
	private DqDruidDataSourceVo() {
		super();
	}
	@Override
	protected void initActualDataSourceBean() {
		this.druidDataSource = new DruidDataSource();
	}


	@Override
	protected void initDataSourceLinkInfo() {
		super.initDataSourceLinkInfo();
		
		this.druidDataSource.setUrl(url);
		this.druidDataSource.setUsername(username);
		this.druidDataSource.setPassword(password);
		this.druidDataSource.setDriverClassName(driverClassName);
	}

	@Override
	protected void initDataSourceParamsInfo() {
		super.initDataSourceParamsInfo();
		
		this.druidDataSource.setInitialSize(initialSize);
		this.druidDataSource.setMaxActive(maxActive);
		this.druidDataSource.setMinIdle(minIdle);
		this.druidDataSource.setMaxWait(maxWait);
		this.druidDataSource.setValidationQuery(validationQuery);
		this.druidDataSource.setTestOnBorrow(testOnBorrow);
		this.druidDataSource.setTestWhileIdle(testWhileIdle);
		this.druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
	}
	
	@Override
	public void initDataSourceBean() {
		dataSource = druidDataSource;
	}
}
