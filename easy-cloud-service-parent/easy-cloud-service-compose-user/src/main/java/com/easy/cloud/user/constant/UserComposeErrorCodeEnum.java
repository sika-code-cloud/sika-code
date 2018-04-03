package com.easy.cloud.user.constant;


import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeInf;

/**
 * 
 * <p>
 * 用户组合服务错误码
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月6日 上午11:40:43
 */
public enum UserComposeErrorCodeEnum implements EcBaseErrorCodeInf{
	/** 用户邮箱不能为空  */
	LOGIN_MODE_WRONG ("UC_000001", "用户邮箱不能为空"),
	;

	private String errorCode;
	private String errorMsg;

	private UserComposeErrorCodeEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getErrorMsg() {
		return errorMsg;
	}
}
