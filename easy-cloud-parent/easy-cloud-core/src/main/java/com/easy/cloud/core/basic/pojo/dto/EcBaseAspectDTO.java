package com.easy.cloud.core.basic.pojo.dto;

/**
 * 切面数据传输类
 * 
 * @author daiqi
 * @date 2018年4月20日 下午11:09:16
 */
public class EcBaseAspectDTO {
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

	public Object[] getTargetParameterValues() {
		return targetParameterValues;
	}

	public void setTargetParameterValues(Object[] targetParameterValues) {
		this.targetParameterValues = targetParameterValues;
	}

	public Class<?>[] getTargetParameterTypes() {
		return targetParameterTypes;
	}

	public void setTargetParameterTypes(Class<?>[] targetParameterTypes) {
		this.targetParameterTypes = targetParameterTypes;
	}

	public String getTargetMethodName() {
		return targetMethodName;
	}

	public void setTargetMethodName(String targetMethodName) {
		this.targetMethodName = targetMethodName;
	}

	public String getTargetClassName() {
		return targetClassName;
	}

	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}

	public Object getTargetReturnValue() {
		return targetReturnValue;
	}

	public void setTargetReturnValue(Object targetReturnValue) {
		this.targetReturnValue = targetReturnValue;
	}

	public Class<?> getTargetReturnType() {
		return targetReturnType;
	}

	public void setTargetReturnType(Class<?> targetReturnType) {
		this.targetReturnType = targetReturnType;
	}

}
