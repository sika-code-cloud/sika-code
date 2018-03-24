package com.dq.easy.cloud.module.common.generator.code.java.desc;

import com.dq.easy.cloud.module.common.generator.code.constant.DqCodeGenerateConstant.DqIgnoreField.DqModifierMappingEnum;

public class DqModifierDesc {
	private Integer code;
	private String desc;
	
	public DqModifierDesc() {
	}
	public DqModifierDesc(DqModifierMappingEnum modifierMappingEnum) {
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
	
	
}
