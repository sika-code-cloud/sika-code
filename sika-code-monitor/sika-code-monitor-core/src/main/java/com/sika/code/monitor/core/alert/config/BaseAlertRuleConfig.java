package com.sika.code.monitor.core.alert.config;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2023/9/28 14:53
 */
@Getter
@Setter
public class BaseAlertRuleConfig {
    /**
     * 匹配的路径表达式
     */
    private String patten = ".*";
}
