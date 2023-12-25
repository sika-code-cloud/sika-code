package com.sika.code.db.sharding.starter.configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.MultiDataPermissionHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.sika.code.db.sharding.core.plugin.ShardingHintPlusPlugin;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MonitorPluginAutoConfig
 *
 * @author : daiqi
 * @date : 2023-08-24
 */
@Configuration
@ConditionalOnClass(Interceptor.class)
@Slf4j
public class ShardingHintAutoConfiguration {

    @Bean
    public ShardingHintPlusPlugin shardingHintPlusPlugin() {
        return new ShardingHintPlusPlugin();
    }

//    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new DataPermissionInterceptor(new MultiDataPermissionHandler() {

            @Override
            public Expression getSqlSegment(final Table table, final Expression where, final String mappedStatementId) {
                try {
                    String sqlSegment = "delete";
                    if (sqlSegment == null) {
                        log.info("{} {} AS {} : NOT FOUND", mappedStatementId, table.getName(), table.getAlias());
                        return null;
                    }
                    Expression sqlSegmentExpression = CCJSqlParserUtil.parseCondExpression(sqlSegment);
                    log.info("{} {} AS {} : {}", mappedStatementId, table.getName(), table.getAlias(), sqlSegmentExpression.toString());
                    return sqlSegmentExpression;
                } catch (JSQLParserException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }));
        return interceptor;
    }

}
