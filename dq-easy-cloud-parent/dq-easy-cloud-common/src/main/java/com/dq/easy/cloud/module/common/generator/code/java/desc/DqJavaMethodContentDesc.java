package com.dq.easy.cloud.module.common.generator.code.java.desc;

/**
 * 方法描述
 * @author daiqi
 * @date 2018年3月25日 下午11:17:05
 */
public class DqJavaMethodContentDesc extends DqJavaContentDesc{
	/** 方法类型 */
	private Integer type;
	/** 方法类型描述 */
	private String typeDesc;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	
}
