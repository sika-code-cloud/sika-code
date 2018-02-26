package com.dq.easy.cloud.model.common.http.constant;

import com.dq.easy.cloud.model.basic.constant.error.DqBaseErrorCodeInf;

/**
 * 
 * <p>
 * http错误码
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 上午11:21:15
 */
public enum DqHttpErrorCode implements DqBaseErrorCodeInf {
	/** 内容格式有误---HTTP_CONTENT_FORMAT_WRONG---HTTP_000001 */
	HTTP_CONTENT_FORMAT_WRONG("HTTP_000001", "内容格式有误")
	;

	private String errorCode;
	private String errorMsg;

	private DqHttpErrorCode(String errorCode, String errorMsg) {
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
