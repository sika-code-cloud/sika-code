package com.dq.easy.cloud.module.common.generator.code.base.pojo.dto;

import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * java属性文件类容基础描述类
 * @author daiqi
 * @date 2018年3月24日 上午9:23:22
 */
public class DqJavaColumnFileContentDesc {

	/** 数据库字段名称 **/
	private String columnName;
	/** 数据库字段类型 **/
	private String columnType;
	/** 数据库字段首字母小写且去掉下划线字符串 **/
	private String changeColumnName;
	/** 数据库字段注释 **/
	private String columnComment;
	/** 属性类型  */
	private String fieldType;
	
	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
		buildFieldType();
	}

	public String getChangeColumnName() {
		return changeColumnName;
	}

	public void setChangeColumnName(String changeColumnName) {
		this.changeColumnName = changeColumnName;
	}
	
	public String getFieldType() {
		return fieldType;
	}
	/**
	 * 
	 * <p>
	 * 根据数据库的字段类型构建属性类型
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param columnType
	 * @author daiqi
	 * 创建时间    2018年3月21日 下午4:44:41
	 */
	public void buildFieldType() {
		if (DqStringUtils.isEmpty(columnType)) {
			return ;
		}
		String tempColumnType = columnType.toLowerCase();
		if (tempColumnType.contains("int") && !tempColumnType.contains("bigint")) {
			this.fieldType = "Integer";
		} else if(tempColumnType.contains("float")) {
			this.fieldType = "Float";
		} else if(tempColumnType.contains("double")) {
			this.fieldType = "Double";
		} else if(tempColumnType.contains("bigint")) {
			this.fieldType = "Long";
		} else if(tempColumnType.contains("char") || tempColumnType.contains("text")) {
			this.fieldType = "String";
		} else if(tempColumnType.contains("datetime") || tempColumnType.contains("timestamp")) {
			this.fieldType = "Date";
		} else if(tempColumnType.contains("decimal")) {
			this.fieldType = "BigDecimal";
		} else if(tempColumnType.contains("blob")) {
			this.fieldType = "Byte []";
		}
	}
}
