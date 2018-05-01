package com.easy.cloud.core.common.generator.code.base.pojo.dto.database;

import com.easy.cloud.core.common.generator.code.java.desc.EcJavaContentDesc;

/**
 * 属性数据传输对象---根据数据库生成
 * @author daiqi
 * @date 2018年3月23日 上午12:21:27
 */
public abstract class EcFieldDatabaseAbstractDTO extends EcJavaContentDesc{
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
	public abstract void buildFieldType();
}
