package com.sika.code.retryer.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 重试名称枚举
 *
 * @author daiqi
 * @create 2019-12-27 0:54
 */
@Getter
@AllArgsConstructor
public enum RetryerNameEnum {
    /**
     * 枚举名称
     */
    DEFAULT("默认的重试名称枚举");
    private String desc;
}
