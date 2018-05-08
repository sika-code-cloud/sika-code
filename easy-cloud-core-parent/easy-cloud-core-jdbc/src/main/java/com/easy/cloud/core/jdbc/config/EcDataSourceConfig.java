package com.easy.cloud.core.jdbc.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @ClassName : DataSourceConfig
 * @Description : 数据源配置
 * @author daiqi
 * @date 2017年12月5日 下午11:16:17
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:config/db.properties" })
public class EcDataSourceConfig {
	@Value(value = "${mybatis.mapper-locations}")
	private String mapperLocations;

	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "ec.hikari.datasource")
	public DataSource dataSourece() {
		HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder.create().build();
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSourece());
		// 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			sessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
			return sessionFactory.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
