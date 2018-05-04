
package com.easy.cloud.pay.core.payment.exception;

import com.easy.cloud.pay.core.payment.pojo.dto.EcPayError;

/**
 * 
 * <p>
 * 支付异常数据传输对象
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月24日 下午3:26:50
 */
public class EcPayExceptionDTO implements EcPayError {
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

    public EcPayExceptionDTO(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public EcPayExceptionDTO(String errorCode, String errorMsg, String content) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.content = content;
    }

    @Override
    public String getString() {
        return "支付错误: errcode=" + errorCode + ", errmsg=" + errorMsg + (null == content ? "" : "\n content:" + content);
    }
}
