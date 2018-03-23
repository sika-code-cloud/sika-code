package com.dq.easy.cloud.module.common.generator.code.pojo.dto;

import java.util.List;

/**
 * 代码生成器---注解组件
 * @author daiqi
 * @date 2018年3月24日 上午1:48:58
 */
public class DqCodeGeneratorAnnotation {
	/** 注解简称 */
	private String simpleName;
	/** 注解完整名称 */
	private String fullName;
	/** 注解的参数列表 */
	private List<DqCodeGeneratorAnnotationParam> params;
	public String getSimpleName() {
		return simpleName;
	}
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public List<DqCodeGeneratorAnnotationParam> getParams() {
		return params;
	}
	public void setParams(List<DqCodeGeneratorAnnotationParam> params) {
		this.params = params;
	}
	
}
