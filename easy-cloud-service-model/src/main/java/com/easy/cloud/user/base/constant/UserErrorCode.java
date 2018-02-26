package com.easy.cloud.user.base.constant;

import com.dq.easy.cloud.model.basic.constant.error.DqBaseErrorCodeInf;

/**
 * 
 * <p>
 * 用户服务错误码
 * </p>
 *
 * <pre>
 * 详细描述
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月2日 下午4:25:50
 */
public enum UserErrorCode implements DqBaseErrorCodeInf{
	/** 用户不存在---U_000001 */
	USER_NOT_EXIST ("U_000001", "用户不存在"),
	/** 用户id不能为空 */
	USER_ID_CANT_NULL ("U_000002", "用户id不能为空"),
	/** 用户对象不能为空 */
	USER_CANT_NULL ("U_000003", "用户对象不能为空"),
	/** 用户名不能为空 */
	USER_NAME_CANT_EMPTY ("U_000004", "用户名不能为空"),
	/** 用户密码不能为空 */
	USER_PASSWOR_CANT_EMPTY ("U_000005", "用户密码不能为空"),
	/** 用户邮箱不能为空  */
	USER_EMAIL_CANT_EMPTY ("U_000006", "用户邮箱不能为空"),
	;
	private String errorCode;
	private String errorMsg;

	private UserErrorCode(String errorCode, String errorMsg) {
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

