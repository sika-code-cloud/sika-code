package com.dq.easy.cloud.model.common.generator.code.pojo.dto.database;

import com.dq.easy.cloud.model.common.generator.code.pojo.dto.DqFieldDTO;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

/**
 * 属性数据传输对象---根据数据库生成
 * @author daiqi
 * @date 2018年3月23日 上午12:21:27
 */
public class DqFieldDatabaseDTO extends DqFieldDTO{
	/** 表的列名 */
	private String tableColumnName;
	/** 表的列类型 */
	private String tableColumnType;
	public String getTableColumnName() {
		return tableColumnName;
	}
	public void setTableColumnName(String tableColumnName) {
		this.tableColumnName = tableColumnName;
	}
	public String getTableColumnType() {
		return tableColumnType;
	}
	public void setTableColumnType(String tableColumnType) {
		this.tableColumnType = tableColumnType;
		buildFieldType();
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
		if (DqStringUtils.isEmpty(tableColumnName)) {
			return ;
		}
		String tempColumnType = tableColumnName.toLowerCase();
		if (tempColumnType.contains("int") && !tempColumnType.contains("bigint")) {
			super.setFieldType("Integer");
		} else if(tempColumnType.contains("float")) {
			super.setFieldType("Float");
		} else if(tempColumnType.contains("double")) {
			super.setFieldType("Double");
		} else if(tempColumnType.contains("bigint")) {
			super.setFieldType("Long");
		} else if(tempColumnType.contains("char") || tempColumnType.contains("text")) {
			super.setFieldType("String");
		} else if(tempColumnType.contains("datetime") || tempColumnType.contains("timestamp")) {
			super.setFieldType("Date");
		} else if(tempColumnType.contains("decimal")) {
			super.setFieldType("BigDecimal");
		} else if(tempColumnType.contains("blob")) {
			super.setFieldType("Byte []");
		}
	}
}
