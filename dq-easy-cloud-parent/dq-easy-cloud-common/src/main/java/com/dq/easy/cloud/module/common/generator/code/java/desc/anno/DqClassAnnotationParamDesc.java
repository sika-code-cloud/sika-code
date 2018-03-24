package com.dq.easy.cloud.module.common.generator.code.java.desc.anno;

import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;

/**
 * 类的注解属性描述类
 * @author daiqi
 * @date 2018年3月24日 上午1:48:58
 */
public class DqClassAnnotationParamDesc extends DqJavaContentBaseDesc{
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
