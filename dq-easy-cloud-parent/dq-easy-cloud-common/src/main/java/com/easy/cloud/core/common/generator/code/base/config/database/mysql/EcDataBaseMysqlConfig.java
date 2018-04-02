package com.dq.easy.cloud.module.common.generator.code.base.config.database.mysql;

import com.dq.easy.cloud.module.common.generator.code.base.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;

public class DqDataBaseMysqlConfig extends DqDatabaseAbstactConfig{

	@Override
	public String getDatabaseDriver() {
		return DqCodeGenerateConfig.DATABASE_MYSQL_DRIVER;
	}

}
