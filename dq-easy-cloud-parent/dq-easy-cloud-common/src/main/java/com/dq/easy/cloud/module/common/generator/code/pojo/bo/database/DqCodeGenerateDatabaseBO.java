package com.dq.easy.cloud.module.common.generator.code.pojo.bo.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.generator.code.constant.DqCodeGenerateConstant.DqIgnoreField;
import com.dq.easy.cloud.module.common.generator.code.constant.error.DqCodeGenerateErrorCodeEnum;
import com.dq.easy.cloud.module.common.generator.code.pojo.bo.DqCodeGenerateBaseBO;
import com.dq.easy.cloud.module.common.generator.code.pojo.dto.DqFieldBaseDTO;
import com.dq.easy.cloud.module.common.generator.code.pojo.dto.database.DqCodeGenerateDatabaseDTO;
import com.dq.easy.cloud.module.common.generator.code.pojo.dto.database.DqFieldDatabaseAbstractDTO;
import com.dq.easy.cloud.module.common.generator.code.pojo.dto.database.mysql.DqFieldMysqlDTO;
import com.dq.easy.cloud.module.common.generator.code.utils.DqCodeGenerateUtils;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.module.exception.bo.DqBaseBusinessException;

/**
 * 
 * <p>
 * 代码生成数据库基础逻辑对象
 * </p>
 *
 * <pre>
 *  说明：处理代码生成的相关业务逻辑
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年3月22日 上午11:48:05
 */
public class DqCodeGenerateDatabaseBO extends DqCodeGenerateBaseBO {

	public DqCodeGenerateDatabaseBO(DqCodeGenerateDatabaseDTO dqCodeGenerateDatabaseDTO) {
		super(dqCodeGenerateDatabaseDTO);
	}

	public Connection getConnection() throws Exception {
		String databaseUrl = getDqCodeGenerateDataBaseDTO().getDatabaseConfig().getDatabaseBaseUrl();
		String databasePort = getDqCodeGenerateDataBaseDTO().getDatabaseConfig().getDatabasePort();
		String databaseName = getDqCodeGenerateDataBaseDTO().getDatabaseConfig().getDatabaseName();
		String databaseUrlFull = DqCodeGenerateUtils.getDatabaseFullUrl(databaseUrl, databasePort, databaseName);
		String databaseUserName = getDqCodeGenerateDataBaseDTO().getDatabaseConfig().getDatabaseUserName();
		String databasePassword = getDqCodeGenerateDataBaseDTO().getDatabaseConfig().getDatabasePassword();
		Class.forName(getDqCodeGenerateDataBaseDTO().getDatabaseConfig().getDatabaseDriver());
		return DriverManager.getConnection(databaseUrlFull, databaseUserName, databasePassword);
	}

	public ResultSet getResultSet() {
		String tableName = getDqCodeGenerateDataBaseDTO().getTableNameLower();
		DatabaseMetaData databaseMetaData = null;
		try (
				Connection connection = getConnection()
		) {
			databaseMetaData = connection.getMetaData();
			ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void initCodeGenerateSubClassData() {
		
	}

	@Override
	protected void verifyCodeGenerateSubClassData() {
		if (DqBaseUtils.isNull(getDqCodeGenerateDataBaseDTO().getDatabaseConfig())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_CONFIG_CANT_NULL);
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabaseConfig().getDatabaseBaseUrl())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_BASEURL_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabaseConfig().getDatabaseDriver())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_DRIVER_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabaseConfig().getDatabaseName())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_NAME_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabaseConfig().getDatabasePassword())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_PASSWORD_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabaseConfig().getDatabasePort())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_PORT_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabaseConfig().getDatabaseUserName())) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.DATABASE_USER_NAME_CANT_EMPTY);
		}
	}

	/**
	 * 
	 * <p>
	 * 获取生成代码数据库数据传输对象
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @return
	 * @author daiqi 创建时间 2018年3月22日 下午7:00:35
	 */
	@JSONField(serialize = false)
	public DqCodeGenerateDatabaseDTO getDqCodeGenerateDataBaseDTO() {
		return (DqCodeGenerateDatabaseDTO) super.getDqCodeGenerateBaseDTO();
	}

	@Override
	protected List<?> buildFieldDTOs() {
		List<DqFieldBaseDTO> dqFieldBaseDTOs = new ArrayList<>();
		ResultSet resultSet = getResultSet();
		try {
			while (resultSet.next()) {
				// id字段略过
				if (DqIgnoreField.isIgnoreField(resultSet.getString("COLUMN_NAME"))) {
					continue;
				}
				DqFieldDatabaseAbstractDTO dqFieldDTO = new DqFieldMysqlDTO();
				// 获取字段名称
				dqFieldDTO.setTableColumnName(resultSet.getString("COLUMN_NAME"));
				// 获取字段类型
				dqFieldDTO.setTableColumnType(resultSet.getString("TYPE_NAME"));
				// 字段在数据库的注释
				dqFieldDTO.setFieldComment(resultSet.getString("REMARKS"));
				dqFieldDTO.setFieldName(DqCodeGenerateUtils.replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
				dqFieldBaseDTOs.add(dqFieldDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dqFieldBaseDTOs;
	}

}
