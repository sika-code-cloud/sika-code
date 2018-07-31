package com.easy.cloud.core.authority.constant;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeInf;

/**
 * @author daiqi
 * @create 2018-07-26 9:16
 */
public enum EcAuthorityErrorCodeEnum implements EcBaseErrorCodeInf {
    USER_NOT_LOGIN("AUTHORITY_000001", "用户没有登录"),
    USER_LOCKED("AUTHORITY_000002", "用户被冻结"),
    USER_NOT_EXIST("AUTHORITY_000003", "用户不存在"),
    USERNAME_PASSWORD_WRONG("AUTHORITY_000004", "账号/密码有误"),
    ;

    EcAuthorityErrorCodeEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private String errorCode;
    private String errorMsg;

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    private static String generateKey() {
        return null;
    }
}
