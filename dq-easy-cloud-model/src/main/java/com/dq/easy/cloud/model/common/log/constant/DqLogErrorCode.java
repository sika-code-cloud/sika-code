package com.dq.easy.cloud.model.common.log.constant;

import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.basic.constant.DqBaseErrorCode;

/**
 * 日志错误码
 * @author daiqi
 * @date 2018年2月8日 下午10:29:01
 */
@Component
public class DqLogErrorCode extends DqBaseErrorCode{
	/** dqLogAnalysisDTO不能为空 */
	public static final String DQ_LOG_ANALYSIS_DTO_CANT_NULL = "LOG_000001";
	/** dqLogDTO不能为空 */
	public static final String DQ_LOG_DTO_CANT_NULL = "LOG_000002";
	
	static {
		setErrorMsg(DQ_LOG_ANALYSIS_DTO_CANT_NULL, "dqLogAnalysisDTO不能为空");
		setErrorMsg(DQ_LOG_DTO_CANT_NULL, "dqLogDTO不能为空");
	}
}
