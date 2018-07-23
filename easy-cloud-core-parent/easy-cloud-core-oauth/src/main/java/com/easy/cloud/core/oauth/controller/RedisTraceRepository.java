package com.easy.cloud.core.oauth.controller;

import com.easy.cloud.core.cache.redis.handler.EcRedisTemplateHandler;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import org.springframework.boot.actuate.trace.Trace;
import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author daiqi
 * @create 2018-07-19 15:40
 */
@Service
public class RedisTraceRepository implements TraceRepository{
    private static final String EC_OAUTH_TRACE = EcStringUtils.generateKeyUseColonSeparator("ec", "oauth", "trace");
    @Override
    public List<Trace> findAll() {
        return EcRedisTemplateHandler.range(EC_OAUTH_TRACE, 0,500, Trace.class);
    }

    @Override
    public void add(Map<String, Object> traceInfo) {
        EcRedisTemplateHandler.leftPush(EC_OAUTH_TRACE, new Trace(new Date(), traceInfo));
    }
}
