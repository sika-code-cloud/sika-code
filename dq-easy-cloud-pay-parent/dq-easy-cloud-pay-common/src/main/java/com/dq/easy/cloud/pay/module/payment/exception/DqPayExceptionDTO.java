
package com.dq.easy.cloud.pay.module.payment.exception;

import com.dq.easy.cloud.pay.module.payment.pojo.dto.DqPayError;

/**
 * 
 * <p>
 * 支付异常数据传输对象
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月24日 下午3:26:50
 */
public class DqPayExceptionDTO implements DqPayError {
    private String errorCode;
    private String errorMsg;
    private String content;

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    public DqPayExceptionDTO(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public DqPayExceptionDTO(String errorCode, String errorMsg, String content) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.content = content;
    }

    @Override
    public String getString() {
        return "支付错误: errcode=" + errorCode + ", errmsg=" + errorMsg + (null == content ? "" : "\n content:" + content);
    }
}
