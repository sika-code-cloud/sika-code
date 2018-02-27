package com.dq.easy.cloud.pay.model.payment.pojo.dto;

/**
 * 
 * <p>
 * 支付错误数据传输对象
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月27日 上午10:44:27
 */
public class DqPayErrorDTO implements DqPayError{
	private String errorCode;
	private String errorMsg;
	private String string;
	
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getErrorMsg() {
		return errorMsg;
	}

	@Override
	public String getString() {
		return string;
	}

}
