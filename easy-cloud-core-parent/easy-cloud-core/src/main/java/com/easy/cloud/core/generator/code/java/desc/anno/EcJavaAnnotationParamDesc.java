package com.easy.cloud.core.generator.code.java.desc.anno;

import com.easy.cloud.core.generator.code.java.desc.EcJavaContentBaseDesc;

/**
 * 类的注解属性描述类
 * 
 * @author daiqi
 * @date 2018年3月24日 上午1:48:58
 */
public class EcJavaAnnotationParamDesc extends EcJavaContentBaseDesc {

	private Object value;

	public EcJavaAnnotationParamDesc() {
		
	}

	public EcJavaAnnotationParamDesc(String name, Object value) {
		super.setName(name);
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String getJavaContentSign() {
		return getName();
	}

}
