package com.easy.cloud.core.generator.code.base.config.database;

import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.generator.code.base.constant.error.EcCodeGenerateErrorCodeEnum;

/**
 * 
 * <p>
 * 数据库配置类
 * </p>
 *
 * @author daiqi 创建时间 2018年3月23日 下午3:13:26
 */
public abstract class EcDatabaseAbstactConfig {
	/** 数据库基础url */
	private String databaseBaseUrl;
	/** 数据库端口 */
	private String databasePort;
	/** 数据库名称 */
	private String databaseName;
	/** 数据库用户名 */
	private String databaseUserName;
	/** 数据库密码 */
	private String databasePassword;
	/** 表名 */
	private String tableName;

	public EcDatabaseAbstactConfig buildDatabaseBaseUrl(String databaseBaseUrl) {
		this.databaseBaseUrl = databaseBaseUrl;
		return this;
	}

	public EcDatabaseAbstactConfig buildDatabasePort(String databasePort) {
		this.databasePort = databasePort;
		return this;
	}

	public EcDatabaseAbstactConfig buildDatabaseName(String databaseName) {
		this.databaseName = databaseName;
		return this;
	}

	public EcDatabaseAbstactConfig buildDatabaseUserName(String databaseUserName) {
		this.databaseUserName = databaseUserName;
		return this;
	}

	public EcDatabaseAbstactConfig buildDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
		return this;
	}
	public EcDatabaseAbstactConfig buildTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}

	public String getDatabaseBaseUrl() {
		return databaseBaseUrl;
	}

	public void setDatabaseBaseUrl(String databaseBaseUrl) {
		this.databaseBaseUrl = databaseBaseUrl;
	}

	public String getDatabasePort() {
		return databasePort;
	}

	public void setDatabasePort(String databasePort) {
		this.databasePort = databasePort;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatabaseUserName() {
		return databaseUserName;
	}

	public void setDatabaseUserName(String databaseUserName) {
		this.databaseUserName = databaseUserName;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void verifyDatabaseConfigData() {
		if (EcStringUtils.isEmpty(getDatabaseBaseUrl())) {
			throw new EcBaseBusinessException(EcCodeGenerateErrorCodeEnum.DATABASE_BASEURL_CANT_EMPTY);
		}
		if (EcStringUtils.isEmpty(getDatabaseDriver())) {
			throw new EcBaseBusinessException(EcCodeGenerateErrorCodeEnum.DATABASE_DRIVER_CANT_EMPTY);
		}
		if (EcStringUtils.isEmpty(getDatabaseName())) {
			throw new EcBaseBusinessException(EcCodeGenerateErrorCodeEnum.DATABASE_NAME_CANT_EMPTY);
		}
		if (EcStringUtils.isEmpty(getDatabasePort())) {
			throw new EcBaseBusinessException(EcCodeGenerateErrorCodeEnum.DATABASE_PORT_CANT_EMPTY);
		}
		if (EcStringUtils.isEmpty(getDatabaseUserName())) {
			throw new EcBaseBusinessException(EcCodeGenerateErrorCodeEnum.DATABASE_USER_NAME_CANT_EMPTY);
		}
		
		if (EcStringUtils.isEmpty(getTableName())) {
			throw new EcBaseBusinessException(EcCodeGenerateErrorCodeEnum.TABLE_NAME_CANT_NULL);
		}
	}
	/** 获取数据库驱动 */
	public abstract String getDatabaseDriver();
}
