package com.sika.code.demo.interfaces.common.migrate.builder;

import cn.hutool.core.collection.CollUtil;
import com.sika.code.migrate.builder.MigrateRuleResponseBuilder;
import com.sika.code.migrate.pojo.MigrateRuleRequest;

/**
 * <pre>
 *  规则响应构建者
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 23:16
 */
public class MigrateRuleResponseBuilderCustomer extends MigrateRuleResponseBuilder {
    private static final String ROOT_URL = "http://localhost:8082";
    private static final String FLOW_RESOURCE = "UC2.0";

    public MigrateRuleResponseBuilderCustomer(MigrateRuleRequest ruleRequest) {
        super(ruleRequest);
    }


    @Override
    protected String getMigrateType() {
        return  CollUtil.getFirst(ruleRequest.getRequestHeadParam().get("migrate-type"));
    }

    @Override
    protected String getRootUrl() {
        return ROOT_URL;
    }

    @Override
    protected String getFlowResource() {
        return FLOW_RESOURCE;
    }

}
