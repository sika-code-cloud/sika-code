package com.easy.cloud.core.reptile.common.constant;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeInf;

public enum EcReptileErrorCodeEnum implements EcBaseErrorCodeInf{
	/** 异常---REPTILE_000001---爬虫动态bean数据列表不能为空 */
	REPTILE_DYNAMIC_BEAN_DATAS_CANT_NULL("REPTILE_000001", "爬虫动态bean数据列表不能为空"),
	/** 异常---REPTILE_000001---爬虫动态bean数据列表不能为空 */
	REPTILE_DATA_FIELD_DATAS_CANT_NULL("REPTILE_000002", "爬虫数据属性数据列表不能为空")
	;
	private String errorCode;
	private String errorMsg;

	private EcReptileErrorCodeEnum(String errorCode, String errorMsg) {
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
