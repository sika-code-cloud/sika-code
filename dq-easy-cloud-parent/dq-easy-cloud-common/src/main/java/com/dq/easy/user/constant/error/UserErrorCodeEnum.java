package com.dq.easy.user.constant.error;

import com.dq.easy.cloud.module.basic.constant.error.DqBaseErrorCodeInf;

/**
 * 描述：用户错误代码枚举
 * @author THINK
 * @date 2018-03-28 11:04:32
 */
public enum UserErrorCodeEnum implements DqBaseErrorCodeInf {
	;
	
	/** 错误代码 */
	private String errorCode;
	/** 错误信息 */
	private String errorMsg;
		
	private UserErrorCodeEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	/** 获取错误代码 */
	public String getErrorCode() {
		return this.errorCode;
	}

	/** 设置错误代码 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/** 获取错误信息 */
	public String getErrorMsg() {
		return this.errorMsg;
	}

	/** 设置错误信息 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
