package com.dq.easy.cloud.model.config.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.dq.easy.cloud.model.jdbc.vo.druid.DqDruidDataSourceBO;
/**
 * 
 * @ClassName : DataSourceConfig 
 * @Description : 数据源配置 
 * @author daiqi
 * @date 2017年12月5日 下午11:16:17 
 *
 */
@Configuration
public class DqDataSourceConfig {
	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		return DqDruidDataSourceBO.getSingleDataSource(environment);
	}
}
