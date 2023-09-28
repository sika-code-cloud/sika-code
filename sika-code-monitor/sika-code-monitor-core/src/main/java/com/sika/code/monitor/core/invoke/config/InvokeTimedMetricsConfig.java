package com.sika.code.monitor.core.invoke.config;

import com.google.common.collect.Lists;
import com.sika.code.monitor.core.common.config.BaseMetricsConfig;
import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import com.sika.code.monitor.core.invoke.enums.InvokeTimedTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 执行好事指标配置
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/9/28 8:16
 */
@Getter
@Setter
public class InvokeTimedMetricsConfig extends BaseMetricsConfig<InvokeTimedMetricsItemConfig> {
    private List<InvokeAlertRuleConfig> alertRules = Lists.newCopyOnWriteArrayList();

    @Override
    public Class<? extends BaseMetricsTypeEnum> getMetricsTypeEnumClass() {
        return InvokeTimedTypeEnum.class;
    }

    @Override
    public Class<InvokeTimedMetricsItemConfig> getMetricsItemConfigClass() {
        return InvokeTimedMetricsItemConfig.class;
    }

}
