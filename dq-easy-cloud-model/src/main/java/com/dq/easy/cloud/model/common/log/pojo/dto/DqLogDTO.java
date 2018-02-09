package com.dq.easy.cloud.model.common.log.pojo.dto;

import java.util.Map;

import org.slf4j.Logger;

/**
 * 
 * <p>
 * 日志数据传输对象
 * </p>
 *
 * <pre>
 *  说明：所有日志传输对象的基础类
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月8日 上午9:25:57
 */
public class DqLogDTO {
	/** 目标参数值列表 */
	private Object[] targetParameterValues;
	/** 目标参数类型列表 */
	private Class<?>[] targetParameterTypes;
	/** 目标方法的返回值 */
	private Object targetReturnValue;
	/** 目标方法的返回类型 */
	private Class<?> targetReturnType;
	/** 目标方法名称 */
	private String targetMethodName;
	/** 目标类名名称 */
	private String targetClassName;
	/** 请求路径 */
	private String requestPath;
	/** 开始时间毫秒数 */
	private Long beginTimeMillis;
	/** 结束时间毫秒数 */
	private Long endTimeMillis;
	/** 方法执行时间毫秒数 */
	private Long executTimeMinllis;
	/** 日志记录对象 */
	private Logger logger;

	public DqLogDTO() {

	}

	private DqLogDTO(Long beginTimeMillis, Long endTimeMillis) {
		this.beginTimeMillis = beginTimeMillis;
		this.endTimeMillis = endTimeMillis;
	}

	public static DqLogDTO newInstance() {
		return new DqLogDTO();
	}

	public static DqLogDTO newInstance(Long beginTimeMillis, Long endTimeMillis) {
		return new DqLogDTO(beginTimeMillis, endTimeMillis).buildExecutTimeMillis();
	}

	public DqLogDTO buildExecutTimeMillis(Long beginTimeMillis, Long endTimeMillis) {
		return buildExecutTimeMillis(endTimeMillis - beginTimeMillis);
	}

	public DqLogDTO buildExecutTimeMillis(Long executTimeMinllis) {
		this.executTimeMinllis = executTimeMinllis;
		return this;
	}

	private DqLogDTO buildExecutTimeMillis() {
		this.executTimeMinllis = this.endTimeMillis - this.beginTimeMillis;
		return this;
	}

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

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	public Long getBeginTimeMillis() {
		return beginTimeMillis;
	}

	public void setBeginTimeMillis(Long beginTimeMillis) {
		this.beginTimeMillis = beginTimeMillis;
	}

	public Long getEndTimeMillis() {
		return endTimeMillis;
	}

	public void setEndTimeMillis(Long endTimeMillis) {
		this.endTimeMillis = endTimeMillis;
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

	public Long getExecutTimeMinllis() {
		return executTimeMinllis;
	}

	public void setExecutTimeMinllis(Long executTimeMinllis) {
		this.executTimeMinllis = executTimeMinllis;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}
