package com.sika.code.basic.pojo.dto;

import lombok.Data;

/**
 * 切面数据传输类
 * 
 * @author daiqi
 * @date 2018年4月20日 下午11:09:16
 */
@Data
public class BaseAspectDTO {
	/** 目标参数值列表 */
	protected Object[] targetParameterValues;
	/** 目标参数类型列表 */
	protected Class<?>[] targetParameterTypes;
	/** 目标方法的返回值 */
	protected Object targetReturnValue;
	/** 目标方法的返回类型 */
	protected Class<?> targetReturnType;
	/** 目标方法名称 */
	protected String targetMethodName;
	/** 目标类名名称 */
	protected String targetClassName;

}
