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
public class MongoTraceRepository implements TraceRepository{

    @Override
    public List<Trace> findAll() {
        return EcRedisTemplateHandler.rangeAll(EcStringUtils.generateKeyUseColonSeparator("ec", "oauth", "trace"), Trace.class);
    }

    @Override
    public void add(Map<String, Object> traceInfo) {
        EcRedisTemplateHandler.rightPush(EcStringUtils.generateKeyUseColonSeparator("ec", "oauth", "trace"), new Trace(new Date(), traceInfo));
    }
}
