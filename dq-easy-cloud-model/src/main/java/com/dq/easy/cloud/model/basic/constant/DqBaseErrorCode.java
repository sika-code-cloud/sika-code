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
 * 所有的服务错误代码应该继承该常量类
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月2日 下午4:07:03
 */
@Component
public class DqBaseErrorCode {
	/** 成功---A000000 */
	public static final String SUCCESS = "A000000";
	/** 系统错误---A000001 */
	public static final String SYS_ERROR = "A000001";
	/** 系统异常---A000002 */
	public static final String SYS_EXCEPTION = "A000002";
	/** 运行时异常---A000003 */
	public static final String RUNTIME_EXCEPTION = "A000003";
	/** 错误代码和消息容器 */
	protected static final Map<String, String> ERROR_CODE_AND_MSG_MAP = new HashMap<>();
	static{
		ERROR_CODE_AND_MSG_MAP.put(SUCCESS, "成功");
		ERROR_CODE_AND_MSG_MAP.put(SYS_ERROR, "系统错误");
		ERROR_CODE_AND_MSG_MAP.put(SYS_EXCEPTION, "系统异常");
		ERROR_CODE_AND_MSG_MAP.put(RUNTIME_EXCEPTION, "运行时异常");
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
