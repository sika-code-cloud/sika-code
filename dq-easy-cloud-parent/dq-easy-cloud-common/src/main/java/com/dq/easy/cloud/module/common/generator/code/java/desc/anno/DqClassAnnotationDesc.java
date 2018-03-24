package com.dq.easy.cloud.module.common.generator.code.java.desc.anno;

import java.util.List;

import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;

/**
 * 类的注解描述类
 * @author daiqi
 * @date 2018年3月24日 上午1:48:58
 */
public class DqClassAnnotationDesc extends DqJavaContentBaseDesc{
	/** 注解的参数列表 */
	private List<DqClassAnnotationParamDesc> params;
	public List<DqClassAnnotationParamDesc> getParams() {
		return params;
	}
	public void setParams(List<DqClassAnnotationParamDesc> params) {
		this.params = params;
	}
	
}
