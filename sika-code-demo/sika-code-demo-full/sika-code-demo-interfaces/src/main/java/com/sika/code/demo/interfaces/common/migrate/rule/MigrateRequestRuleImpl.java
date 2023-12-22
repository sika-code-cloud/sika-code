package com.sika.code.demo.interfaces.common.migrate.rule;

import com.sika.code.demo.interfaces.common.migrate.builder.MigrateRuleResponseBuilderCustomer;
import com.sika.code.migrate.builder.MigrateRuleResponseBuilder;
import com.sika.code.migrate.pojo.MigrateRuleRequest;
import com.sika.code.migrate.pojo.MigrateRuleResponse;
import com.sika.code.migrate.rule.MigrateRequestRule;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <pre>
 *  迁移规则实现类
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 14:51
 */
@Component
public class MigrateRequestRuleImpl implements MigrateRequestRule {

    @Resource
    protected FlowExecutor flowExecutor;

    @Override
    public MigrateRuleResponse match(MigrateRuleRequest ruleRequest) {
        MigrateRuleResponseBuilder responseBuilder = new MigrateRuleResponseBuilderCustomer(ruleRequest);
        MigrateRuleResponse ruleResponse = responseBuilder.build();
        if (matchRule(ruleRequest, ruleResponse)) {
            responseBuilder.reBuild(ruleResponse);

        }
        return ruleResponse;
    }

    private boolean matchRule(MigrateRuleRequest ruleRequest, MigrateRuleResponse ruleResponse) {
        // 匹配规则链
        // 1. 黑名单URL匹配
        // 2. 全量规则匹配
        // 3. EVN_KEY规则匹配
        // 4. 公司ID规则匹配
        // 5. 白名单规则匹配
        LiteflowResponse response = flowExecutor.execute2Resp("migrateRule", ruleRequest, ruleResponse);
        if (response.isSuccess()) {
            return ruleResponse.getMatch();
        } else {
            return false;
        }
    }
}
