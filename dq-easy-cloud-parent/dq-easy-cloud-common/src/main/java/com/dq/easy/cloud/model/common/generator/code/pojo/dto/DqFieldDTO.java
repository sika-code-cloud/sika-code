package com.dq.easy.cloud.model.common.generator.code.pojo.dto;

import java.util.List;

import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

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
	/** 属性注释 */
	private String fieldComment;
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

	public String getFieldComment() {
		return fieldComment;
	}

	public void setFieldComment(String fieldComment) {
		this.fieldComment = fieldComment;
	}
	
}
