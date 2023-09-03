package com.sika.code.monitor.core.db.invoke.plugin;

import com.sika.code.monitor.core.common.metrics.InvokeTimedMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DbClientInvokedTimedPlugin
 *
 * @author : daiqi
 * @date : 2023-06-19
 */
@Intercepts(value = {@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
    @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class,
            BoundSql.class}), @Signature(type = Executor.class, method = "query",
    args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class DbClientInvokedTimedPlugin implements Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbClientInvokedTimedPlugin.class);

    private MeterRegistry meterRegistry;

    public void setMeterRegistry(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        String methodName = mappedStatement.getId();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("sqlCommandType:{},methodName：{}", sqlCommandType.name(), methodName);
        }
        long startTime = System.nanoTime();
        try {
            return invocation.proceed();
        } finally {
            InvokeTimedMetrics.collectDBClientInvokeTimed(meterRegistry, sqlCommandType.name(), methodName,
                System.nanoTime() - startTime);
        }
    }
}