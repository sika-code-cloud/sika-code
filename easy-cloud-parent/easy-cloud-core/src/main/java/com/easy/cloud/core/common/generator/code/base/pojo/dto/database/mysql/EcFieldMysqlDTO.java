package com.easy.cloud.core.common.generator.code.base.pojo.dto.database.mysql;

import com.easy.cloud.core.common.generator.code.base.pojo.dto.database.EcFieldDatabaseAbstractDTO;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
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
public class EcFieldMysqlDTO extends EcFieldDatabaseAbstractDTO{

	@Override
	public void buildFieldType() {
		if (EcStringUtils.isEmpty(super.getTableColumnType())) {
			return ;
		}
		String tempColumnType = EcStringUtils.lowerCase(getTableColumnType());
		if (tempColumnType.contains("int") && !tempColumnType.contains("bigint")) {
			super.setSimpleClassType("Integer");
		} else if(tempColumnType.contains("float")) {
			super.setSimpleClassType("Float");
		} else if(tempColumnType.contains("double")) {
			super.setSimpleClassType("Double");
		} else if(tempColumnType.contains("bigint")) {
			super.setSimpleClassType("Long");
		} else if(tempColumnType.contains("char") || tempColumnType.contains("text")) {
			super.setSimpleClassType("String");
		} else if(tempColumnType.contains("datetime") || tempColumnType.contains("timestamp")) {
			super.setSimpleClassType("Date");
		} else if(tempColumnType.contains("decimal")) {
			super.setSimpleClassType("BigDecimal");
		} else if(tempColumnType.contains("blob")) {
			super.setSimpleClassType("Byte []");
		}
	}

}
