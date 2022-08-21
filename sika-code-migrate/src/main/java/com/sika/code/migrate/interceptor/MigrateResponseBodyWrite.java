package com.sika.code.migrate.interceptor;

import com.sika.code.migrate.executor.MigrateResultExecutor;
import com.sika.code.migrate.pojo.MigrateRuleResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@Slf4j
public class MigrateResponseBodyWrite implements ResponseBodyAdvice {
    @Autowired
    protected MigrateResultExecutor migrateResultExecutor;

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter,
                                  MediaType mediaType, Class arg3, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        try {
            MigrateRuleResult ruleResult = new MigrateRuleResult();
            ruleResult.setResult(body);
            migrateResultExecutor.execute(ruleResult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return body;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class clazz) {
        return true;
    }

}