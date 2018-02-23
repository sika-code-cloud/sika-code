package com.dq.easy.cloud.model.basic.constant;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 
 * <p>
 * 错误代码常量基础类
 * </p>
 *
 * <pre>
 * 1、所有的服务错误代码应该继承该常量类
 * 2、各个子类的静态块中往ERROR_CODE_AND_MSG_MAP中添加错误说明信息
 * 3、每个子类添加@Component注解。这样就能加载类从而使得子类的错误说明信息能加载到ERROR_CODE_AND_MSG_MAP容器中
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月2日 下午4:07:03
 */
@Component
public class DqBaseErrorCode {
	/** 成功---A_000000 */
	public static final String SUCCESS = "A_000000";
	/** 异常---系统错误---A_000001 */
	public static final String SYS_ERROR = "A_000001";
	/** 异常---系统异常---A_000002 */
	public static final String SYS_EXCEPTION = "A_000002";
	/** 异常---运行时异常---A_000003 */
	public static final String RUNTIME_EXCEPTION = "A_000003";
	/** 异常---查询对象不能为空---A_000004 */
	public static final String QUERY_OBJ_CANT_NULL = "A_000004";
	/** 异常---持久化对象不能为空---A_000005 */
	public static final String ENTITY_OBJ_CANT_NULL = "A_000005";
	/** 异常---数据传输对象不能为空---A_000006 */
	public static final String DTO_OBJ_CANT_NULL = "A_000006";
	/** 异常---业务逻辑对象不能为空---A_000007 */
	public static final String BO_OBJ_CANT_NULL = "A_000007";
	/** 异常---视图对象不能为空---A_000008 */
	public static final String VO_OBJ_CANT_NULL = "A_000008";
	/** 异常---类型转换异常---A_000009 */
	public static final String TYPE_CONVERT_EXCEPTION = "A_000009";
	/** 异常---非法类型异常---A_000009 */
	public static final String ILLICIT_TYPE_EXCEPTION = "A_000010";
	
	/** 错误代码和消息容器 */
	private static final Map<String, String> ERROR_CODE_AND_MSG_MAP = new HashMap<>();
	
	static{
		setErrorMsg(SUCCESS, "成功");
		setErrorMsg(SYS_ERROR, "系统错误");
		setErrorMsg(SYS_EXCEPTION, "系统异常");
		setErrorMsg(RUNTIME_EXCEPTION, "运行时异常");
		setErrorMsg(QUERY_OBJ_CANT_NULL, "查询对象不能为空");
		setErrorMsg(ENTITY_OBJ_CANT_NULL, "持久化对象不能为空");
		setErrorMsg(DTO_OBJ_CANT_NULL, "数据传输对象不能为空");
		setErrorMsg(BO_OBJ_CANT_NULL, "业务逻辑对象不能为空");
		setErrorMsg(VO_OBJ_CANT_NULL, "视图对象不能为空");
		setErrorMsg(TYPE_CONVERT_EXCEPTION, "类型转换异常");
		setErrorMsg(ILLICIT_TYPE_EXCEPTION, "非法类型异常常");
	}
	/**
	 * 
	 * <p>
	 * 设置错误码和信息到map集合中
	 * </p>
	 *
	 * @param errorCode : String : 错误码
	 * @param errorMsg : String : 错误信息
	 * @author daiqi
	 * 创建时间    2018年2月22日 上午10:26:42
	 */
	public static void setErrorMsg(String errorCode, String errorMsg){
		ERROR_CODE_AND_MSG_MAP.put(errorCode, errorMsg);
	}
	/**
	 * 
	 * <p>
	 * 根据错误代码获取错误信息
	 * </p>
	 *
	 * @param errorCode
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月2日 下午4:15:56
	 */
	public static String getErrorMsg(String errorCode){
		return ERROR_CODE_AND_MSG_MAP.get(errorCode);
	}
	
}
