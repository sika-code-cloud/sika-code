package com.sika.code.demo.interfaces.common.migrate.rule.node;

import cn.hutool.core.collection.CollUtil;
import com.sika.code.migrate.pojo.MigrateRuleRequest;
import com.sika.code.migrate.pojo.MigrateRuleResponse;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

import java.util.List;

/**
 * <pre>
 *  公司规则
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 23:30
 */
@LiteflowComponent("companyRule")
public class CompanyRule extends NodeComponent {
    private static final String MIGRATE_COMPANY_ID_KEY = "migrate-company-id";
    private static final String COMPANY_ID_KEY = "company-id";
    @Override
    public void process() throws Exception {
        MigrateRuleRequest ruleRequest = getRequestData();
        List<String> migrateCompanyId = ruleRequest.getRequestHeadParam().get(MIGRATE_COMPANY_ID_KEY);
        List<String> companyId = ruleRequest.getRequestHeadParam().get(COMPANY_ID_KEY);
        boolean match = CollUtil.isNotEmpty(migrateCompanyId) && migrateCompanyId.contains(CollUtil.getFirst(companyId));
        if (match) {
            getContextBean(MigrateRuleResponse.class).setMatch(true);
            setIsEnd(true);
        }
    }
}
