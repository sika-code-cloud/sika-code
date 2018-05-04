package com.easy.cloud.core.generator.code.base.sources.database.mysql;

import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.generator.code.base.config.database.EcDatabaseAbstactConfig;
import com.easy.cloud.core.generator.code.base.sources.database.EcDatabaseDataSources;
import com.easy.cloud.core.generator.code.base.utils.EcCodeGenerateUtils;

/**
 * 数据来源---mysql
 * @author daiqi
 * @date 2018年3月24日 上午10:55:35
 */
public class EcMysqlDataSources extends EcDatabaseDataSources{

	public EcMysqlDataSources(EcDatabaseAbstactConfig databaseConfig) {
		super(databaseConfig);
	}

	@Override
	public String getFieldTypeByColumnType(String columnType) {
		if (EcStringUtils.isEmpty(columnType)) {
			return null;
		}
		return EcCodeGenerateUtils.getJavaSimpleClassTypeOfMysql(columnType);
	}

}
