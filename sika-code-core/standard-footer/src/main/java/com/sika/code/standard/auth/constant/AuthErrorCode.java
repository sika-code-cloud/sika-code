package com.sika.code.standard.auth.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;

/**
 * @author daiqi
 * @create 2018-12-14 22:22
 */
public enum AuthErrorCode implements BaseErrorCode {
    /** 登录信息失效 --- AUTH_000001 */
    LOGIN_INFO_INVALID("AUTH_000001", "%s"),
    /** token非法 --- AUTH_000002 */
    TOKEN_SIGNATURE_EXCEPTION("AUTH_000002", "token非法"),
    /** token的秘钥有误 --- AUTH_000003 */
    JWT_SECRET_WRONG("AUTH_000003", "token的秘钥有误"),
    /** 登录信息失效 --- AUTH_000004 */
    LOGIN_INFO_WRONG("AUTH_000004", "登录信息有误"),
    /** 用户名或密码有误 --- AUTH_000005 */
    USERNAME_PASSWORD_WRONG("AUTH_000005", "用户名或密码有误"),
    /** 权限不足 --- AUTHENTICATE_000001 */
    PERMISSION_DENIED("AUTHENTICATE_000001", "权限不足"),
    /** 自动授权登录失败 --- AUTH_000006 */
    AUTO_AUTHORIZED_LOGIN_FAIL("AUTH_000006", "自动授权登录失败"),
    ;
    private String code;
    private String message;

    AuthErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
