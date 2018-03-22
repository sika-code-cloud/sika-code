package com.dq.easy.cloud.model.common.sign.constant;

import com.dq.easy.cloud.model.basic.constant.error.DqBaseErrorCodeInf;

/**
 * 
 * <p>
 * 签名错误码
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月23日 下午2:40:08
 */
public enum DqSignErrorCodeEnum implements DqBaseErrorCodeInf {
	/** HMACSHA256 签名异常---SIGN_000001 */
	SIGN_HMACSHA256_EXCEPTION ("SIGN_000001", "HMACSHA256 签名异常")
	;

	private String errorCode;
	private String errorMsg;

	private DqSignErrorCodeEnum(String errorCode, String errorMsg) {
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
