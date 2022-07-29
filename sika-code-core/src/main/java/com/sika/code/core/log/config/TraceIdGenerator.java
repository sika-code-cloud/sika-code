package com.sika.code.core.log.config;

import com.sika.code.core.log.util.LogUtil;
import com.yomahub.tlog.id.TLogIdGenerator;

/**
 * @author daiqi
 * @create 2021-11-27 1:38
 */
public class TraceIdGenerator extends TLogIdGenerator {
    @Override
    public String generateTraceId() {
        return LogUtil.createTraceId();
    }
}
