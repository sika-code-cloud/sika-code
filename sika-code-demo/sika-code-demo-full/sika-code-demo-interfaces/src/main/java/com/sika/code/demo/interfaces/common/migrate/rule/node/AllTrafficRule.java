package com.sika.code.demo.interfaces.common.migrate.rule.node;

import cn.hutool.core.collection.CollUtil;
import com.sika.code.migrate.pojo.MigrateRuleRequest;
import com.sika.code.migrate.pojo.MigrateRuleResponse;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

import java.util.List;

/**
 * <pre>
 *  全量规则
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 23:30
 */
@LiteflowComponent("allTrafficRule")
public class AllTrafficRule extends NodeComponent {
    private static final String ALL_TRAFFIC_KEY = "migrate-all-traffic";
    private static final String ALL_TRAFFIC = "1";

    @Override
    public void process() throws Exception {
        MigrateRuleRequest ruleRequest = getRequestData();
        List<String> allTraffic = ruleRequest.getRequestHeadParam().get(ALL_TRAFFIC_KEY);
        boolean match = CollUtil.isNotEmpty(allTraffic) && allTraffic.contains(ALL_TRAFFIC);
        if (match) {
            getContextBean(MigrateRuleResponse.class).setMatch(true);
            setIsEnd(true);
        }
    }
}
