package com.dq.easy.cloud.module.common.generator.code.pojo.dto;

import java.util.List;

/**
 * 属性数据传输对象
 * 
 * @author daiqi
 * @date 2018年3月23日 上午12:08:52
 */
public abstract class DqFieldBaseDTO extends DqCodeGeneratorJavaDTO{
	/** 属性类型 */
	private String fieldName;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	
}
