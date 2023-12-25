package com.sika.code.demo.interfaces.common.migrate.executor;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.BooleanUtil;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.google.common.collect.Maps;
import com.sika.code.core.base.constant.BaseTypeEnum;
import com.sika.code.core.base.util.JSONUtil;
import com.sika.code.migrate.builder.MigrateForestRequestBuilder;
import com.sika.code.migrate.builder.MigrateRequestRuleBuilder;
import com.sika.code.migrate.constant.MigrateTypeEnum;
import com.sika.code.migrate.executor.MigrateRequestExecutor;
import com.sika.code.migrate.pojo.MigrateRuleRequest;
import com.sika.code.migrate.pojo.MigrateRuleResponse;
import com.sika.code.migrate.rule.MigrateRequestRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <pre>
 *  迁移结果规则实现类
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/21 13:09
 */
@Slf4j
@Component
public class MigrateRequestExecutorImpl implements MigrateRequestExecutor {
    @Autowired
    private MigrateRequestRule migrateRule;

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 构建规则请求对象
        MigrateRuleRequest ruleRequest = new MigrateRequestRuleBuilder(request, response).build();
        log.info("迁移规则请求类：{}", JSONUtil.toJSONString(ruleRequest));
        // 通过迁移规则-获取迁移规则响应
        MigrateRuleResponse ruleResponse = migrateRule.match(ruleRequest);
        log.info("迁移规则响应类：{}", JSONUtil.toJSONString(ruleResponse));
        // 切换规则没有匹配-则当前请求不需要执行迁移
        if (ruleResponse == null || BooleanUtil.isFalse(ruleResponse.getMatch())) {
            return true;
        }
        if (BaseTypeEnum.notExist(ruleResponse.getMigrateType(), MigrateTypeEnum.class)) {
            log.info("不支持的迁移类型【{}】", ruleResponse.getMigrateType());
            return true;
        }
        ForestRequest<?> forestRequest = new MigrateForestRequestBuilder(ruleRequest, ruleResponse).build();
        // 命中匹配规则-则执行流量迁移规则
        if (MigrateTypeEnum.isSwitch(ruleResponse.getMigrateType())) {
            flowSwitching(ruleRequest, forestRequest);
            // 执行流量切换
            return false;
        } else if (MigrateTypeEnum.isContrast(ruleResponse.getMigrateType())) {
            // 执行流量对比
            flowContrast(forestRequest);
        } else if (MigrateTypeEnum.isDoubleWrite(ruleResponse.getMigrateType())) {
            // 执行流量双写
            flowDoubleWriter(forestRequest);
        }
        return true;
    }


    protected void logResponse(HttpServletResponse response) {
        log.info("response.getHeader:{}", JSONUtil.toJSONString(response.getHeaderNames()));
        Map<String, String> heads = Maps.newHashMap();
        for (String headerName : response.getHeaderNames()) {
            heads.put(headerName, response.getHeader(headerName));
        }
        log.info("response.getHeaderValue:{}", JSONUtil.toJSONString(heads));
    }

    // 流量双写逻辑
    public void flowDoubleWriter(ForestRequest<?> forestRequest) {
        forestRequest.async().execute();
    }

    // 流量比对
    public void flowContrast(ForestRequest<?> forestRequest) {
        forestRequest.async().execute();
    }

    public void flowSwitching(MigrateRuleRequest ruleRequest, ForestRequest<?> forestRequest) throws IOException {
        HttpServletResponse response = ruleRequest.getResponse();
        ForestResponse<?> forestResponse = forestRequest.execute(ForestResponse.class);
        buildResponse(forestResponse, response);
        IoUtil.writeUtf8(response.getOutputStream(), true, forestResponse.getResult());
    }


    protected void buildResponse(ForestResponse<?> forestResponse, HttpServletResponse response) {
        log.info("forestResponse:{}", JSONUtil.toJSONString(forestResponse.getHeaders()));
        log.info("forestResponse.getResult:{}", JSONUtil.toJSONString(forestResponse.getResult()));
        for (Map.Entry<String, String> header : forestResponse.getHeaders().entrySet()) {
            response.setHeader(header.getKey(), header.getValue());
        }
        logResponse(response);
    }

}
