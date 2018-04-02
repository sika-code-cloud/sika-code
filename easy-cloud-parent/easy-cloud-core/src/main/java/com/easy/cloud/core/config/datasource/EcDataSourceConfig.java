package com.easy.cloud.core.config.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.easy.cloud.core.conditional.datasource.EcDataSourceConditional;
import com.easy.cloud.core.jdbc.vo.druid.EcDruidDataSourceBO;
/**
 * 
 * @ClassName : DataSourceConfig 
 * @Description : 数据源配置 
 * @author daiqi
 * @date 2017年12月5日 下午11:16:17 
 *
 */
@Configuration
@Conditional(EcDataSourceConditional.class)
public class EcDataSourceConfig {
	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		return EcDruidDataSourceBO.getSingleDataSource(environment);
	}
}
