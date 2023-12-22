package com.sika.code.demo.interfaces.common.migrate.rule.node;

import cn.hutool.core.collection.CollUtil;
import com.sika.code.migrate.pojo.MigrateRuleRequest;
import com.sika.code.migrate.pojo.MigrateRuleResponse;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

import java.util.List;

/**
 * <pre>
 *  黑名单路径规则
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 23:30
 */
@LiteflowComponent("blackListRule")
public class BlackListRule extends NodeComponent {
    private static final String MIGRATE_BLACK_LIST_KEY = "migrate-black-list";

    @Override
    public void process() throws Exception {
        MigrateRuleRequest ruleRequest = getRequestData();
        List<String> blackList = ruleRequest.getRequestHeadParam().get(MIGRATE_BLACK_LIST_KEY);
        if (CollUtil.isEmpty(blackList)) {
            return;
        }
        boolean match = blackList.contains(ruleRequest.getRequestUri());
        if (!match) {
            getContextBean(MigrateRuleResponse.class).setMatch(true);
        } else {
            this.setIsEnd(true);
        }
    }

}
