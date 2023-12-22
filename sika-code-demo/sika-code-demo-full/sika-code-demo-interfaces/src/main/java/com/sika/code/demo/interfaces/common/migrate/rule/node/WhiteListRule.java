package com.sika.code.demo.interfaces.common.migrate.rule.node;

import cn.hutool.core.collection.CollUtil;
import com.sika.code.migrate.pojo.MigrateRuleRequest;
import com.sika.code.migrate.pojo.MigrateRuleResponse;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

import java.util.List;

/**
 * <pre>
 *  白名单路径规则
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 23:30
 */
@LiteflowComponent("whiteListRule")
public class WhiteListRule extends NodeComponent {
    private static final String MIGRATE_WHITE_LIST_KEY = "migrate-white-list";
    @Override
    public void process() throws Exception {
        MigrateRuleRequest ruleRequest = getRequestData();
        List<String> whiteList = ruleRequest.getRequestHeadParam().get(MIGRATE_WHITE_LIST_KEY);
        boolean match = CollUtil.isNotEmpty(whiteList) && whiteList.contains(ruleRequest.getRequestUri());
        if (match) {
            getContextBean(MigrateRuleResponse.class).setMatch(true);
            setIsEnd(true);
        }
    }
}
