package com.dq.easy.cloud.model.jdbc.vo.druid;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.jdbc.vo.AbstractDqDataSourceBO;

/**
 * 
 * @ClassName : DqDruidDataSourceBO 
 * @Description : druid的数据配置信息BO
 * @author daiqi
 * @date 2017年12月6日 上午10:09:13 
 *
 */
public class DqDruidDataSourceBO extends AbstractDqDataSourceBO{
	
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
	public static DqDruidDataSourceBO newInstance(){
		return new DqDruidDataSourceBO();
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
	private static DqDruidDataSourceBO newInstance(Environment environment){
		return new DqDruidDataSourceBO(environment);
	}
	
	/**
	 * 
	 * <p>读取配置文件获取单例的数据源</p>
	 *
	 * <pre></pre>
	 *
	 * @param environment : 传入读取配置文件的对象
	 * @return DataSource
	 *
	 * author daiqi
	 * 创建时间  2018年1月6日 下午3:45:58
	 */
	public static DataSource getSingleDataSource(Environment environment){
		return newInstance(environment).getDataSource();
	}
	private DqDruidDataSourceBO(Environment environment) {
		super(environment);
	}
	private DqDruidDataSourceBO() {
		super();
	}
	@Override
	protected void initActualDataSourceBean() {
		this.druidDataSource = new DruidDataSource();
	}


	@Override
	protected void initDataSourceLinkInfo() {
		super.initDataSourceLinkInfo();
		if(DqBaseUtils.isNull(this.druidDataSource)){
			return ;
		}
		
		this.druidDataSource.setUrl(url);
		this.druidDataSource.setUsername(username);
		this.druidDataSource.setPassword(password);
		this.druidDataSource.setDriverClassName(driverClassName);
	}

	@Override
	protected void initDataSourceParamsInfo() {
		super.initDataSourceParamsInfo();
		if(DqBaseUtils.isNull(this.druidDataSource)){
			return ;
		}
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
