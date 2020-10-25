package com.sika.code.standard.footer.demo.business.accessruletype.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 准入规则类型表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2019-07-18 23:29:17
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
