package com.dq.easy.cloud.model.exception.bo;

/**
 * 
 * <p>
 * 业务系统异常类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月2日 下午7:19:35
 */
public class DqBaseBusinessException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMsg;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
}
