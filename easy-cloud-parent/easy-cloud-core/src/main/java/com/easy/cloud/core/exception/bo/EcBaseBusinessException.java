package com.easy.cloud.core.exception.bo;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeInf;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;

/**
 * 
 * <p>
 * 业务系统异常类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月2日 下午7:19:35
 */
public class EcBaseBusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMsg;
	private EcBaseErrorCodeInf ecBaseErrorCodeInf;
	// 异常详情信息
	private Object exceptionDetail;

	public EcBaseBusinessException(EcBaseErrorCodeInf ecBaseErrorCodeInf) {
		buildDqBaseErrorCodeInf(ecBaseErrorCodeInf);
	}

	public EcBaseBusinessException(String errorCode, String errorMsg) {
		buildErrorCode(errorCode).buildErrorMsg(errorMsg);
	}

	public EcBaseBusinessException() {
	}

	public static EcBaseBusinessException newInstance() {
		return new EcBaseBusinessException();
	}

	public static EcBaseBusinessException newInstance(EcBaseErrorCodeInf ecBaseErrorCodeInf) {
		return new EcBaseBusinessException(ecBaseErrorCodeInf);
	}

	public static EcBaseBusinessException newInstance(String errorCode, String errorMsg) {
		return new EcBaseBusinessException(errorCode, errorMsg);
	}

	public EcBaseBusinessException buildDqBaseErrorCodeInf(EcBaseErrorCodeInf ecBaseErrorCodeInf) {
		this.ecBaseErrorCodeInf = ecBaseErrorCodeInf;
		this.errorCode = ecBaseErrorCodeInf.getErrorCode();
		this.errorMsg = ecBaseErrorCodeInf.getErrorMsg();
		return this;
	}

	public EcBaseBusinessException buildExceptionDetail(Object exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
		return this;
	}

	/** 构建errorCode */
	private EcBaseBusinessException buildErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	/** 构建errorMsg */
	private EcBaseBusinessException buildErrorMsg(String errorMsg) {
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
		EcBaseServiceResult result = null;
		if (EcBaseUtils.isNull(ecBaseErrorCodeInf)) {
			result = EcBaseServiceResult.newInstance();
			result.setErrorCode(errorCode);
			result.setErrorMsg(errorMsg);
		} else {
			result = EcBaseServiceResult.newInstanceOfError(ecBaseErrorCodeInf);
		}
		result.buildResult(exceptionDetail);
		return EcJSONUtils.parseObject(result, String.class);
	}

}
