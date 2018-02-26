package com.dq.easy.cloud.model.common.log.constant;

import com.dq.easy.cloud.model.basic.constant.error.DqBaseErrorCodeInf;

/**
 * 日志错误码
 * @author daiqi
 * @date 2018年2月8日 下午10:29:01
 */
public enum DqLogErrorCode implements DqBaseErrorCodeInf{
	/** dqLogAnalysisDTO不能为空---LOG_000001 */
	DQ_LOG_ANALYSIS_DTO_CANT_NULL("LOG_000001","dqLogAnalysisDTO不能为空"),
	/** dqLogDTO不能为空---LOG_000002 */
	DQ_LOG_DTO_CANT_NULL("LOG_000002","dqLogDTO不能为空")
	;
	
	private String errorCode;
	private String errorMsg;
	
	private DqLogErrorCode(String errorCode, String errorMsg) {
		
	}
	@Override
	public String getErrorCode() {
		return this.errorCode;
	}

	@Override
	public String getErrorMsg() {
		return this.errorMsg;
	}
}
