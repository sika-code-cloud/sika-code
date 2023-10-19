package com.sika.code.monitor.core.alert.matcher;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.sika.code.monitor.core.invoke.config.InvokeAlertRuleConfig;
import com.sika.code.monitor.core.invoke.config.InvokeTimedMetricsItemConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>
 * 基础告警匹配器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/9/28 14:55
 */
@Getter
@Setter
@Slf4j
public class AlertMatcher {
    protected static final InvokeAlertRuleConfig DEFAULT_CONFIG = new InvokeAlertRuleConfig();

    public static InvokeAlertRuleConfig matchInvokedTime(InvokeTimedMetricsItemConfig itemConfig,
                                                         List<String> waitMatchTagValues) {
        String metricsType = itemConfig.getMetricsType();
        List<InvokeAlertRuleConfig> alertRules = itemConfig.getAlertRules();
        // 优先匹配自定义
        InvokeAlertRuleConfig alertRuleConfig = matchInvokedTime(alertRules, waitMatchTagValues, metricsType);
        if (alertRuleConfig != null) {
            return alertRuleConfig;
        }
        // 若为空匹配全局
        List<InvokeAlertRuleConfig> globalAlertRuleConfigs = itemConfig.getMetricsConfig().getAlertRules();
        InvokeAlertRuleConfig globalAlertRuleConfig = matchInvokedTime(globalAlertRuleConfigs, waitMatchTagValues, metricsType);
        if (globalAlertRuleConfig != null) {
            return globalAlertRuleConfig;
        }
        return DEFAULT_CONFIG;
    }

    private static InvokeAlertRuleConfig matchInvokedTime(List<InvokeAlertRuleConfig> alertRuleConfigs,
                                                          List<String> waitMatchTagValues, String metricsType) {
        if (ArrayUtil.isEmpty(waitMatchTagValues)) {
            return null;
        }
        String waitMatchStr = CollUtil.join(waitMatchTagValues, StrPool.COLON);
        if (StrUtil.isBlank(waitMatchStr)) {
            return null;
        }
        if (StrUtil.isBlank(metricsType)) {
            return null;
        }
        if (CollUtil.isEmpty(alertRuleConfigs)) {
            return null;
        }
        log.info("开始进入正则表达式匹配：{}", waitMatchStr);
        return alertRuleConfigs.stream()
                .filter(config -> ReUtil.isMatch(config.getPatten(), waitMatchStr))
                .findFirst().orElse(null);
    }
}
