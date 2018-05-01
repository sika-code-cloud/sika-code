package com.easy.cloud.core.common.generator.code.java.desc;

import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcModifierMappingEnum;

public class EcJavaModifierDesc extends EcJavaContentBaseDesc{
	private Integer code;
	private String desc;
	
	public EcJavaModifierDesc() {
	}
	public EcJavaModifierDesc(EcModifierMappingEnum modifierMappingEnum) {
		this.code = modifierMappingEnum.getModifier();
		this.desc = modifierMappingEnum.getModifierDesc();
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String getJavaContentSign() {
		return getDesc();
	}
	
	
}
