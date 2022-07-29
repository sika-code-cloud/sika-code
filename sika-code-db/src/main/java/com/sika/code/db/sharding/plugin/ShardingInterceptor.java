package com.sika.code.db.sharding.plugin;

import com.sika.code.core.util.BeanUtil;
import com.sika.code.db.sharding.context.ShardingContext;
import com.sika.code.db.sharding.executor.DefaultShardingExecutor;
import com.sika.code.db.sharding.executor.ShardingExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author ：sikadai
 * @description：
 * @date ：2019/6/14 10:10
 */
@Slf4j(topic = "策略分库分表拦截器【ShardingInterceptor】")
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class ShardingInterceptor implements Interceptor {

    private static final String PARAMETER_OBJECT = "delegate.boundSql.parameterObject";
    private ShardingExecutor shardingExecutor;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            log.info("进入分库分表拦截器：====================");
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
            Object parameterObject = metaStatementHandler.getValue(PARAMETER_OBJECT);
            getShardingExecutor().doShardingDbTable(metaStatementHandler, parameterObject);
            log.info("完成分库分表拦截器：====================");
            // 传递给下一个拦截器处理
            return invocation.proceed();
        } finally {
            ShardingContext.remove();
        }
    }

    @Override
    public Object plugin(Object arg0) {
        if (arg0 instanceof StatementHandler) {
            return Plugin.wrap(arg0, this);
        } else {
            return arg0;
        }
    }

    @Override
    public void setProperties(Properties arg0) {

    }

    public void setShardingExecutor(ShardingExecutor shardingExecutor) {
        this.shardingExecutor = shardingExecutor;
    }

    protected ShardingExecutor getShardingExecutor() {
        return this.shardingExecutor;
    }
}