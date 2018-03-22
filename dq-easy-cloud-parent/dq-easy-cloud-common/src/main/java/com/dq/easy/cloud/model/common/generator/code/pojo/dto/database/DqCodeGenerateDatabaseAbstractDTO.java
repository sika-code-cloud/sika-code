package com.dq.easy.cloud.model.common.generator.code.pojo.dto.database;

import com.dq.easy.cloud.model.common.generator.code.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.model.common.generator.code.pojo.dto.DqCodeGenerateBaseDTO;

/**
 * 
 * <p>
 * 代码生成数据库数据数据传输对象
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：对于不同的代码生成数据库数据传输对象都需要继承此类
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年3月22日 下午4:43:51
 */
public abstract class DqCodeGenerateDatabaseAbstractDTO extends DqCodeGenerateBaseDTO {
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
	/** 表名称---小写 */
	private String tableNameLower;

	public DqCodeGenerateBaseDTO initDatabaseBaseUrlDefault() {
		setDatabaseBaseUrl(DqCodeGenerateConfig.DATABASE_BASE_URL);
		return this;
	}

	public DqCodeGenerateBaseDTO initDatabasePortDefault() {
		setDatabasePort(DqCodeGenerateConfig.DATABASE_PORT);
		return this;
	}

	public DqCodeGenerateBaseDTO initDatabaseNameDefault() {
		setDatabaseName(DqCodeGenerateConfig.DATABASE_NAME);
		return this;
	}

	public DqCodeGenerateBaseDTO initDatabaseUserNameDefault() {
		setDatabaseUserName(DqCodeGenerateConfig.DATABASE_USER_NAME);
		return this;
	}

	public DqCodeGenerateBaseDTO initDatabasePasswordDefault() {
		setDatabasePassword(DqCodeGenerateConfig.DATABASE_PASSWORD);
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

	public String getTableNameLower() {
		return tableNameLower;
	}

	public void setTableNameLower(String tableNameLower) {
		this.tableNameLower = tableNameLower;
	}

	/** 获取数据库驱动 */
	public abstract String getDatabaseDriver();
}
