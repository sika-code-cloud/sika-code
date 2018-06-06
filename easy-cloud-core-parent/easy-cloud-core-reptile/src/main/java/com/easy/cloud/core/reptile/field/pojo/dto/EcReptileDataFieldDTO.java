package com.easy.cloud.core.reptile.field.pojo.dto;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;

public class EcReptileDataFieldDTO extends EcBaseDTO {
	private static final long serialVersionUID = -4939563600619865667L;
	/** 属性对应的路径 */
	private String path;
	/** 值的来源 1:text 2:html 3:href 4:image 5:attr 6:ajax 7: ... */
	private Integer valueSource;
	/** 值来源类型 1:cssPath 2:jsonPath */
	private Integer valueSourceType;
	/** 若该属性为图片可以设置下载路径将会自动将图片下载到指定路径中 */
	private String downloadPath;
	/** 对应html元素的attr的值等等 */
	private String fieldValue;
	/** 对应相关boolean类型的标志 */
	private Boolean valueFlag;
	/** 生成bean的属性名称 */
	private String fieldName;
	/** 生成bean的属性类型 支持string、int、long、float、double、list类型 */
	private String fieldType;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getValueSource() {
		return valueSource;
	}

	public void setValueSource(Integer valueSource) {
		this.valueSource = valueSource;
	}

	public Integer getValueSourceType() {
		return valueSourceType;
	}

	public void setValueSourceType(Integer valueSourceType) {
		this.valueSourceType = valueSourceType;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public Boolean getValueFlag() {
		return valueFlag;
	}

	public void setValueFlag(Boolean valueFlag) {
		this.valueFlag = valueFlag;
	}

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

}
