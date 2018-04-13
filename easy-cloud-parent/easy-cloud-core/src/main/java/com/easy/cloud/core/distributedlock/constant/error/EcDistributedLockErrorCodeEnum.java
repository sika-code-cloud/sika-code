package com.easy.cloud.core.distributedlock.constant.error;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeInf;
/**
 * 
 * <p>
 * 错误枚举类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年4月13日 下午2:08:45
 */
public enum EcDistributedLockErrorCodeEnum implements EcBaseErrorCodeInf{
	/** 异常---不支持对该锁类型解锁---DL_000001 */
	LOCK_TYPE_NOT_SUPPORT("DL_000001", "不支持对该锁类型解锁")
	;
	
	private String errorCode;
	private String errorMsg;

	private EcDistributedLockErrorCodeEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
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
