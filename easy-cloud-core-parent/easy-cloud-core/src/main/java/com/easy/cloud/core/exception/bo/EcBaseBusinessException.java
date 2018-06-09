package com.easy.cloud.core.exception.bo;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeInf;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.common.array.EcArrayUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

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
	private Object[] formatValues;
	private boolean isFormated;
	// 异常详情信息
	private Object exceptionDetail;

	public EcBaseBusinessException(EcBaseErrorCodeInf baseErrorCodeInf, Object... formatValues) {
		this(baseErrorCodeInf.getErrorCode(), baseErrorCodeInf.getErrorMsg(), formatValues);
	}

	public EcBaseBusinessException(String errorCode, String errorMsg, Object... formatValues) {
		buildErrorCode(errorCode).buildErrorMsg(errorMsg);
		this.buildFormatValues(formatValues);
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

	/** 构建需要替换msg中参数的值 */
	private EcBaseBusinessException buildFormatValues(Object... formatValues) {
		this.formatValues = formatValues;
		return this;
	}

	public Object[] getFormatValues() {
		return formatValues;
	}

	public void setFormatValues(String[] formatValues) {
		this.formatValues = formatValues;
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
		if (!isFormated) {
			if (EcStringUtils.isNotEmpty(errorMsg) && EcArrayUtils.isNotEmpty(this.formatValues)) {
				this.errorMsg = String.format(errorMsg, this.formatValues);
				this.isFormated = true;
			}
		}
		EcBaseServiceResult result = EcBaseServiceResult.newInstance();
		result.buildErrorCodeAndMsg(errorCode, errorMsg);
		result.buildResult(exceptionDetail);
		return EcJSONUtils.parseObject(result, String.class);
	}

}
