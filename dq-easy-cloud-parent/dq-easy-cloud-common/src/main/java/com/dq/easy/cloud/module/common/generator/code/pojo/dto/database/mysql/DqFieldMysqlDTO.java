package com.dq.easy.cloud.module.common.generator.code.pojo.dto.database.mysql;

import com.dq.easy.cloud.module.common.generator.code.pojo.dto.database.DqFieldDatabaseAbstractDTO;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
/**
 * 
 * <p>
 * 属性以mysql为基础的数据传输对象
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年3月23日 上午8:56:04
 */
public class DqFieldMysqlDTO extends DqFieldDatabaseAbstractDTO{

	@Override
	public void buildFieldType() {
		if (DqStringUtils.isEmpty(super.getTableColumnType())) {
			return ;
		}
		String tempColumnType = DqStringUtils.lowerCase(getTableColumnType());
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
