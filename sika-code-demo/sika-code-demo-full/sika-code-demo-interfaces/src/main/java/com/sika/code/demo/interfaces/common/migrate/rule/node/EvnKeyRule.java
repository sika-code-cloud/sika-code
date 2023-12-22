package com.sika.code.demo.interfaces.common.migrate.rule.node;

import cn.hutool.core.collection.CollUtil;
import com.sika.code.migrate.pojo.MigrateRuleRequest;
import com.sika.code.migrate.pojo.MigrateRuleResponse;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

import java.util.List;

/**
 * <pre>
 * EveKey规则
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 23:32
 */
@LiteflowComponent("evnKeyRule")
public class EvnKeyRule extends NodeComponent {
    private static final String MIGRATE_EVN_KEY_KEY = "migrate-evn-key";
    private static final String EVN_KEY_KEY = "evn-key";
    @Override
    public void process() throws Exception {
        MigrateRuleRequest ruleRequest = getRequestData();
        List<String> migrateEvnKeys = ruleRequest.getRequestHeadParam().get(MIGRATE_EVN_KEY_KEY);
        List<String> evnKeys = ruleRequest.getRequestHeadParam().get(EVN_KEY_KEY);
        boolean match = CollUtil.isNotEmpty(migrateEvnKeys) && migrateEvnKeys.contains(CollUtil.getFirst(evnKeys));
        if (match) {
            getContextBean(MigrateRuleResponse.class).setMatch(true);
            setIsEnd(true);
        }
    }
}
