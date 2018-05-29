package com.easy.cloud.pay.core.payment.pojo.dto;

/**
 * 
 * <p>
 * 支付错误码说明
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午3:09:25
 */
public interface EcPayError {

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    String getErrorCode();

    /**
     * 获取错误消息
     *
     * @return 错误消息
     */
    String getErrorMsg();

    /**
     * 获取异常信息
     * @return 异常信息
     */
    String getString();

}
