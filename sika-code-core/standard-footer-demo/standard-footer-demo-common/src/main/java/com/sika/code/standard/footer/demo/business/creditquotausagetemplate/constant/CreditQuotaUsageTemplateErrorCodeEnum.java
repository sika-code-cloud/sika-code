package com.sika.code.standard.footer.demo.business.creditquotausagetemplate.constant;


import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 额度模板类型配置 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:03:28
 */
@Getter
@AllArgsConstructor
public enum CreditQuotaUsageTemplateErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例 --- CREDITQUOTAUSAGETEMPLATE_000001
     */
    CREDITQUOTAUSAGETEMPLATE_ERROR_CODE_ENUM("CREDITQUOTAUSAGETEMPLATE_000001", "错误枚举示例")
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
