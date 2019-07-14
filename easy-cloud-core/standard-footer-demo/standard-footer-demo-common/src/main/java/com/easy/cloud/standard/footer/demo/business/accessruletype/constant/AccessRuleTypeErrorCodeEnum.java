package com.easy.cloud.standard.footer.demo.business.accessruletype.constant;

import com.easy.cloud.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 准入规则类型表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-21 23:04:45
 */
@Getter
@AllArgsConstructor
public enum AccessRuleTypeErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    ACCESSRULETYPE_ERROR_CODE_ENUM("ACCESSRULETYPE_000001", "错误枚举示例")
    ;

    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;
}
