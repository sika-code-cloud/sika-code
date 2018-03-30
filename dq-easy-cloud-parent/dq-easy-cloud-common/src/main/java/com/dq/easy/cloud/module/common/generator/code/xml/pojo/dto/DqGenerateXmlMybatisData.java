package com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto;

import com.dq.easy.cloud.module.common.generator.code.base.utils.DqCodeGenerateUtils;

public class DqGenerateXmlMybatisData {
	/** 列名 */
	private String colunmName;
	/** 列的类型 */
	private String colunmType;
	/** 属性名称 */
	private String propertyName;
	/** 列名key如PRI:主键 UNI:唯一索引 等等 */
	private String columnKey;

	public String getColunmName() {
		return colunmName;
	}

	public void setColunmName(String colunmName) {
		this.colunmName = colunmName;
	}

	public String getColunmType() {
		return colunmType;
	}

	public void setColunmType(String colunmType) {
		this.colunmType = colunmType;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getPropertyFullType() {
		return DqCodeGenerateUtils.getJavaFullClassTypeOfMysql(colunmType);
	}
}
