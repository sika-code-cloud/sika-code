package com.easy.cloud.core.distributedlock.constant.error;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeInf;

/**
 * 
 * <p>
 * 错误枚举类
 * </p>
 *
 * @author daiqi 创建时间 2018年4月13日 下午2:08:45
 */
public enum EcDistributedLockErrorCodeEnum implements EcBaseErrorCodeInf {
	/** 异常---不支持对该锁类型解锁---DL_000001 */
	LOCK_TYPE_NOT_SUPPORT("DL_000001", "不支持对该锁类型解锁"),
	/** 异常---分布式锁结果不能为空---DL_000002 */
	LOCK_RESULT_CANT_NULL("DL_000002", "分布式锁结果不能为空"),
	/** 异常---分布式锁获取失败---DL_000003 */
	LOCK_GAIN_FAIL("DL_000003", "分布式锁获取失败"),
	/** 异常---分布式锁结果处理对象创建失败---DL_000004 */
	RESULT_PROCESSOR_OBJ_CREATE_FAIL("DL_000004", "分布式锁结果处理对象创建失败"),
	/** 异常---分布式锁名不能为空---DL_000005 */
	LOCK_NAME_CANT_EMPTY("DL_000005", "分布式锁名不能为空"),
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
