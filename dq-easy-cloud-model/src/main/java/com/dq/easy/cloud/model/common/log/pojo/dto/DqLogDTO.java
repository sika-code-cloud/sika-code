package com.dq.easy.cloud.model.common.log.pojo.dto;

import java.util.Map;

/**
 * 
 * <p>
 *   日志数据传输对象
 * </p>
 *
 * <pre>
 *  说明：所有日志传输对象的基础类
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月8日 上午9:25:57
 */
public class DqLogDTO {
	/** 输入数据信息---参数类名为key---参数的数据是value */
	private Map<String, Object> inputDatas;
	/** 输出数据信息---返回的类名为key---返回的数据是value */
	private Map<String, Object> outData;
	/** 目标方法名称 */
	private String targetMethodName;
	/** 目标类名名称 */
	private String targetClassName;
	/** 请求路径 */
	private String requestPath;
	
	public static DqLogDTO newInstance(){
		return new DqLogDTO();
	}
	
	public DqLogDTO buildInputDatas(Map<String, Object> inputDatas){
		this.inputDatas = inputDatas;
		return this;
	}
	
	public DqLogDTO buildOutData(Map<String, Object> outData){
		this.outData = outData;
		return this;
	}
	
	public DqLogDTO buildTargetMethodName(String targetMethodName){
		this.targetMethodName = targetMethodName;
		return this;
	}
	
	public DqLogDTO buildTargetClassName(String targetClassName){
		this.targetClassName = targetClassName;
		return this;
	}
	
	public DqLogDTO buildRequestPath(String requestPath){
		this.requestPath = requestPath;
		return this;
	}
	
	public Map<String, Object> getInputDatas() {
		return inputDatas;
	}
	public void setInputDatas(Map<String, Object> inputDatas) {
		this.inputDatas = inputDatas;
	}
	public Map<String, Object> getOutData() {
		return outData;
	}
	public void setOutData(Map<String, Object> outData) {
		this.outData = outData;
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
	
}
