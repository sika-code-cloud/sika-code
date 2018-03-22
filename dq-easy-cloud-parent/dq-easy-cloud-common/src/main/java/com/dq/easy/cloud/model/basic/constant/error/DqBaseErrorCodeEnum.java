package com.dq.easy.cloud.model.basic.constant.error;

/**
 * 
 * <p>
 * 错误代码常量基础类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月2日 下午4:07:03
 */
public enum DqBaseErrorCodeEnum implements DqBaseErrorCodeInf{
	/** 成功---A_000000 */
	SUCCESS("A_000000", "成功"),
	/** 异常---系统错误---A_000001 */
	SYS_ERROR("A_000001", "系统错误"),
	/** 异常---系统异常---A_000002 */
	SYS_EXCEPTION("A_000002", "系统异常"),
	/** 异常---运行时异常---A_000003 */
	RUNTIME_EXCEPTION("A_000003", "运行时异常"),
	/** 异常---查询对象不能为空---A_000004 */
	QUERY_OBJ_CANT_NULL("A_000004", "查询对象不能为空"),
	/** 异常---持久化对象不能为空---A_000005 */
	ENTITY_OBJ_CANT_NULL("A_000005", "持久化对象不能为空"),
	/** 异常---数据传输对象不能为空---A_000006 */
	DTO_OBJ_CANT_NULL("A_000006", "数据传输对象不能为空"),
	/** 异常---业务逻辑对象不能为空---A_000007 */
	BO_OBJ_CANT_NULL("A_000007", "业务逻辑对象不能为空"),
	/** 异常---视图对象不能为空---A_000008 */
	VO_OBJ_CANT_NULL("A_000008", "视图对象不能为空"),
	/** 异常---类型转换异常---A_000009 */
	TYPE_CONVERT_EXCEPTION("A_000009", "类型转换异常"),
	/** 异常---非法类型异常---A_000010 */
	ILLICIT_TYPE_EXCEPTION("A_000010", "非法类型异常")
	;

	private String errorCode;
	private String errorMsg;
	
	private DqBaseErrorCodeEnum(String errorCode, String errorMsg) {
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
