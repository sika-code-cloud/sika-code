package com.dq.easy.cloud.module.common.generator.code.base.sources.database.mysql;

import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * 数据来源---mysql
 * @author daiqi
 * @date 2018年3月24日 上午10:55:35
 */
public class DqMysqlDataSources extends DqDatabaseDataSources{

	public DqMysqlDataSources(DqDatabaseAbstactConfig databaseConfig, String tableName) {
		super(databaseConfig, tableName);
	}

	@Override
	public String getFieldTypeByColumnType(String columnType) {
		if (DqStringUtils.isEmpty(columnType)) {
			return null;
		}
		String tempColumnType = DqStringUtils.lowerCase(columnType);
		if (tempColumnType.contains("int") && !tempColumnType.contains("bigint")) {
			return "Integer";
		} else if(tempColumnType.contains("float")) {
			return "Float";
		} else if(tempColumnType.contains("double")) {
			return "Double";
		} else if(tempColumnType.contains("bigint")) {
			return "Long";
		} else if(tempColumnType.contains("char") || tempColumnType.contains("text")) {
			return "String";
		} else if(tempColumnType.contains("datetime") || tempColumnType.contains("timestamp")) {
			return "Date";
		} else if(tempColumnType.contains("decimal")) {
			return "BigDecimal";
		} else if(tempColumnType.contains("blob")) {
			return "Byte []";
		}
		return null;
	}

}
