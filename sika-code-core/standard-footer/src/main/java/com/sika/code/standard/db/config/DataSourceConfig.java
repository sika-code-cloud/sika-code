package com.sika.code.standard.db.config;


import com.sika.code.standard.db.util.DataSourceKey;
import com.sika.code.standard.db.util.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * <p>
 * 数据源配置
 * </p>
 * <pre>
 *     在设置了spring.datasource.enable.dynamic 等于true是开启多数据源
 * </pre>
 *
 * @author daiqi
 * @date 2018/12/4 9:39
 */
@Configuration(value = "dataSourceConfig")
@ConditionalOnProperty(name = {"spring.datasource.dynamic.enable"}, matchIfMissing = false, havingValue = "true")
public class DataSourceConfig {

    /** 创建数据源 */
    /**
     * 所有引入db-core的模块都需要一个核心库
     */
    @Bean
    @ConfigurationProperties("spring.datasource.hikari.core")
    public DataSource dataSourceCore() {
        return DataSourceBuilder.create().build();
    }


    /**
     * 只需要纳入动态数据源到spring容器
     */
    @Bean
    @Primary
    public DataSource dataSource() {
        DynamicDataSource dataSource = new DynamicDataSource();
        DataSource coreDataSource = this.dataSourceCore();
        dataSource.addDataSource(DataSourceKey.CORE, coreDataSource);
        dataSource.setDefaultTargetDataSource(coreDataSource);
        return dataSource;
    }

    /**
     * 将数据源纳入spring事物管理
     */
    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
