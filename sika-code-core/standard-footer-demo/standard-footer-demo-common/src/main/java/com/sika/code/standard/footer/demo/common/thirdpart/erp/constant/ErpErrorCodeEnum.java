package com.sika.code.standard.footer.demo.common.thirdpart.erp.constant;


import com.sika.code.basic.errorcode.BaseErrorCode;

/**
 * Erp常量类
 *
 * @author daiqi
 * @create 2019-04-04 17:47
 */
public enum ErpErrorCodeEnum implements BaseErrorCode {
    ;
    private String code;
    private String message;

    ErpErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
