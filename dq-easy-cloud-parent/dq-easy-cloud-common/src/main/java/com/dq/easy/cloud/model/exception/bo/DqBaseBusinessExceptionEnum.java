package com.dq.easy.cloud.model.exception.bo;

import com.dq.easy.cloud.model.basic.constant.error.DqBaseErrorCodeInf;
import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseServiceResult;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;

/**
 * 
 * <p>
 * 业务系统异常类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月2日 下午7:19:35
 */
public class DqBaseBusinessExceptionEnum extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMsg;
	private DqBaseErrorCodeInf dqBaseErrorCodeInf;
	// 异常详情信息
	private Object exceptionDetail;

	public DqBaseBusinessExceptionEnum(DqBaseErrorCodeInf dqBaseErrorCodeInf) {
		buildDqBaseErrorCodeInf(dqBaseErrorCodeInf);
	}
	
	public DqBaseBusinessExceptionEnum(String errorCode, String errorMsg) {
		buildErrorCode(errorCode).buildErrorMsg(errorMsg);
	}
	
	public DqBaseBusinessExceptionEnum() {
	}

	public static DqBaseBusinessExceptionEnum newInstance() {
		return new DqBaseBusinessExceptionEnum();
	}

	public static DqBaseBusinessExceptionEnum newInstance(DqBaseErrorCodeInf dqBaseErrorCodeInf) {
		return new DqBaseBusinessExceptionEnum(dqBaseErrorCodeInf);
	}

	public static DqBaseBusinessExceptionEnum newInstance(String errorCode, String errorMsg) {
		return new DqBaseBusinessExceptionEnum(errorCode, errorMsg);
	}

	public DqBaseBusinessExceptionEnum buildDqBaseErrorCodeInf(DqBaseErrorCodeInf dqBaseErrorCodeInf) {
		this.dqBaseErrorCodeInf = dqBaseErrorCodeInf;
		this.errorCode = dqBaseErrorCodeInf.getErrorCode();
		this.errorMsg = dqBaseErrorCodeInf.getErrorMsg();
		return this;
	}

	public DqBaseBusinessExceptionEnum buildExceptionDetail(Object exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
		return this;
	}

	/** 构建errorCode */
	private DqBaseBusinessExceptionEnum buildErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	/** 构建errorMsg */
	private DqBaseBusinessExceptionEnum buildErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
		return this;
	}

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

	public Object getExceptionDetail() {
		return exceptionDetail;
	}

	@Override
	public String getMessage() {
		DqBaseServiceResult result = null;
		if (DqBaseUtils.isNull(dqBaseErrorCodeInf)) {
			result = DqBaseServiceResult.newInstance();
			result.setErrorCode(errorCode);
			result.setErrorMsg(errorMsg);
		} else {
			result = DqBaseServiceResult.newInstanceOfError(dqBaseErrorCodeInf);
		}
		result.buildResult(exceptionDetail);
		return DqJSONUtils.parseObject(result, String.class);
	}

}
