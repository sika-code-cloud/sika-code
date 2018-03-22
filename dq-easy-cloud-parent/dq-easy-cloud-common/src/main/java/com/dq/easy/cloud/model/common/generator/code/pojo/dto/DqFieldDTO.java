package com.dq.easy.cloud.model.common.generator.code.pojo.dto;

import java.util.List;

/**
 * 属性数据传输对象
 * 
 * @author daiqi
 * @date 2018年3月23日 上午12:08:52
 */
public class DqFieldDTO {
	/** 属性名称 */
	private String fieldName;
	/** 属性类型 */
	private String fieldType;
	/** 属性注解列表 */
	private List<String> fieldAntations;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public List<String> getFieldAntations() {
		return fieldAntations;
	}

	public void setFieldAntations(List<String> fieldAntations) {
		this.fieldAntations = fieldAntations;
	}

}
