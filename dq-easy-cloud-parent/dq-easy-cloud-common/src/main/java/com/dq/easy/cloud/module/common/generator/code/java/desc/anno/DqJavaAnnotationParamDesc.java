package com.dq.easy.cloud.module.common.generator.code.java.desc.anno;

import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;

/**
 * 类的注解属性描述类
 * @author daiqi
 * @date 2018年3月24日 上午1:48:58
 */
public class DqJavaAnnotationParamDesc extends DqJavaContentBaseDesc{
	
	private Object value;


	public DqJavaAnnotationParamDesc() {
	}

	public DqJavaAnnotationParamDesc(String name, Object value) {
		super.setName(name);
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}
