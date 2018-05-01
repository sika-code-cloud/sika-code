package com.easy.cloud.core.lock.constant.error;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeInf;

/**
 * 
 * <p>
 * 错误枚举类
 * </p>
 *
 * @author daiqi 创建时间 2018年4月13日 下午2:08:45
 */
public enum EcLockErrorCodeEnum implements EcBaseErrorCodeInf {
	/** 异常---不支持对该锁类型解锁---DL_000001 */
	LOCK_TYPE_NOT_SUPPORT("DL_000001", "不支持对该锁类型解锁"),
	/** 异常---锁结果不能为空---DL_000002 */
	LOCK_RESULT_CANT_NULL("DL_000002", "锁结果不能为空"),
	/** 异常---锁获取失败---DL_000003 */
	LOCK_GAIN_FAIL("DL_000003", "锁获取失败"),
	/** 异常---锁结果处理对象创建失败---DL_000004 */
	RESULT_PROCESSOR_OBJ_CREATE_FAIL("DL_000004", "锁结果处理对象创建失败"),
	/** 异常---锁名不能为空---DL_000005 */
	LOCK_NAME_CANT_EMPTY("DL_000005", "锁名不能为空"),
	/** 异常---资源已经被锁定---DL_000006 */
	RESOURCES_BE_LOCKED("DL_000006", "资源已经被锁定"),
	;

	private String errorCode;
	private String errorMsg;

	private EcLockErrorCodeEnum(String errorCode, String errorMsg) {
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
