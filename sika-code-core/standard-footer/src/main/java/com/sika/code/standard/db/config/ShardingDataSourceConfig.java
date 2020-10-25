package com.sika.code.standard.db.config;

import com.sika.code.standard.db.util.DataSourceKey;
import com.sika.code.standard.db.util.DynamicDataSource;
import com.sika.code.standard.db.util.ModuloDatabaseShardingAlgorithm;
import com.sika.code.standard.db.util.ModuloTableShardingAlgorithm;
import com.google.common.collect.Maps;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingjdbc.core.jdbc.core.datasource.ShardingDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * <p>
 * TODO 尚未完整
 * 分表分库基础设置
 * </p>
 * <pre>
 *     在设置了spring.datasource.SHARDING.enable 等于true是开启分表分库
 * </pre>
 *
 * @author daiqi
 * @date 2018/12/4 9:38
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
/** 排除DataSourceConfiguratrion */
@ConditionalOnProperty(name = {"spring.datasource.sharding.enable"}, matchIfMissing = false, havingValue = "true")
public class ShardingDataSourceConfig {

    // 创建数据源
    // 所有引入db-core的模块都需要一个核心库，可以是user-center，也可以是oauth-center,file-center,sms-center
    @Bean
    @ConfigurationProperties("spring.datasource.hikari.master")
    public DataSource dataSourceMaster() {
        return DataSourceBuilder.create().build();
    }

    // 从数据
    @Bean
    @ConfigurationProperties("spring.datasource.hikari.slave")
    public DataSource dataSourceSlave() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.hikari.single")
    public DataSource dataSourceSingle() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "shardingDataSource")
    public DataSource getShardingDataSource(@Qualifier("dataSourceMaster") DataSource dataSourceMaster,
                                            @Qualifier("dataSourceSlave") DataSource dataSourceSlave) throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig;
        shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("t_order");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
                new StandardShardingStrategyConfiguration("user_id", ModuloDatabaseShardingAlgorithm.class.getName()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(
                new StandardShardingStrategyConfiguration("order_id", ModuloTableShardingAlgorithm.class.getName()));
        // 设置分库映射
        Map<String, DataSource> dataSourceMap = Maps.newHashMap();
        dataSourceMap.put("dataSourceMaster", dataSourceMaster);
        dataSourceMap.put("dataSourceSlave", dataSourceSlave);

        return new ShardingDataSource(shardingRuleConfig.build(dataSourceMap));
    }


    @Bean // 只需要纳入动态数据源到spring容器
    @Primary
    public DataSource dataSource(@Qualifier("shardingDataSource") DataSource shardingDataSource) {
        DynamicDataSource dataSource = new DynamicDataSource();
        DataSource coreDataSource = this.dataSourceSingle();
        dataSource.addDataSource(DataSourceKey.SHARDING, shardingDataSource);
        dataSource.addDataSource(DataSourceKey.CORE, coreDataSource);
        dataSource.setDefaultTargetDataSource(coreDataSource);
        return dataSource;
    }


    /**
     * 设置表的node
     *
     * @return
     */
    @Bean
    public TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("test_msg${0..1}.t_order_${0..1}");
        orderTableRuleConfig.setKeyGeneratorColumnName("order_id");
        return orderTableRuleConfig;
    }

    /**
     * 需要手动配置事务管理器
     *
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
