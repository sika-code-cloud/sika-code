package com.dq.easy.cloud.module.common.generator.code.pojo.dto.database;

import com.dq.easy.cloud.module.common.generator.code.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.pojo.dto.DqCodeGenerateBaseDTO;
import com.dq.easy.cloud.module.common.generator.code.utils.DqCodeGenerateUtils;

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
public class DqCodeGenerateDatabaseDTO extends DqCodeGenerateBaseDTO {
	private DqDatabaseAbstactConfig databaseConfig;
	
	/** 该方法只提供给json序列化用 */
	public DqCodeGenerateDatabaseDTO() {
	}

	public DqCodeGenerateDatabaseDTO(DqDatabaseAbstactConfig databaseConfig) {
		this.databaseConfig = databaseConfig;
	}

	/** 表名称---小写 */
	private String tableNameLower;

	public String getTableNameLower() {
		return tableNameLower;
	}

	public void setTableNameLower(String tableNameLower) {
		this.tableNameLower = tableNameLower;
	}

	public DqDatabaseAbstactConfig getDatabaseConfig() {
		return databaseConfig;
	}

	public void setDatabaseConfig(DqDatabaseAbstactConfig databaseConfig) {
		this.databaseConfig = databaseConfig;
	}

	@Override
	public void initClassNameBody() {
		setClassNameBody(DqCodeGenerateUtils.replaceUnderLineAndUpperCase(tableNameLower));
		super.initClassNameBody();
	}
}
