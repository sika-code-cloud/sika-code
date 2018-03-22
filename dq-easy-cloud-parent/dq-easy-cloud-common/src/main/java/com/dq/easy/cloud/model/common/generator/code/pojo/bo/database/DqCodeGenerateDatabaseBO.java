package com.dq.easy.cloud.model.common.generator.code.pojo.bo.database;

import com.alibaba.fastjson.annotation.JSONField;
import com.dq.easy.cloud.model.common.generator.code.constant.error.DqCodeGenerateErrorCodeEnum;
import com.dq.easy.cloud.model.common.generator.code.pojo.bo.DqCodeGenerateBaseBO;
import com.dq.easy.cloud.model.common.generator.code.pojo.dto.database.DqCodeGenerateDatabaseAbstractDTO;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.model.exception.bo.DqBaseBusinessExceptionEnum;

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

	public DqCodeGenerateDatabaseBO(DqCodeGenerateDatabaseAbstractDTO dqCodeGenerateDatabaseDTO) {
		super(dqCodeGenerateDatabaseDTO);
	}

	@Override
	protected void verifyCodeGenerateSubClassData() {
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabaseBaseUrl())) {
			throw new DqBaseBusinessExceptionEnum(DqCodeGenerateErrorCodeEnum.DATABASE_BASEURL_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabaseDriver())) {
			throw new DqBaseBusinessExceptionEnum(DqCodeGenerateErrorCodeEnum.DATABASE_DRIVER_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabaseName())) {
			throw new DqBaseBusinessExceptionEnum(DqCodeGenerateErrorCodeEnum.DATABASE_NAME_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabasePassword())) {
			throw new DqBaseBusinessExceptionEnum(DqCodeGenerateErrorCodeEnum.DATABASE_PASSWORD_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabasePort())) {
			throw new DqBaseBusinessExceptionEnum(DqCodeGenerateErrorCodeEnum.DATABASE_PORT_CANT_EMPTY);
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabaseUserName())) {
			throw new DqBaseBusinessExceptionEnum(DqCodeGenerateErrorCodeEnum.DATABASE_USER_NAME_CANT_EMPTY);
		}
	}

	@Override
	protected void initCodeGenerateSubClassData() {
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabaseBaseUrl())) {
			this.getDqCodeGenerateDataBaseDTO().getDatabaseBaseUrl();
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabaseName())) {
			this.getDqCodeGenerateDataBaseDTO().initDatabaseNameDefault();
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabasePassword())) {
			this.getDqCodeGenerateDataBaseDTO().initDatabasePasswordDefault();
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabasePort())) {
			this.getDqCodeGenerateDataBaseDTO().initDatabasePortDefault();
		}
		if (DqStringUtils.isEmpty(getDqCodeGenerateDataBaseDTO().getDatabaseUserName())) {
			this.getDqCodeGenerateDataBaseDTO().initDatabaseUserNameDefault();
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
	 * @author daiqi
	 * 创建时间    2018年3月22日 下午7:00:35
	 */
	@JSONField(serialize = false)
	public DqCodeGenerateDatabaseAbstractDTO getDqCodeGenerateDataBaseDTO() {
		return (DqCodeGenerateDatabaseAbstractDTO) super.getDqCodeGenerateBaseDTO();
	}
}
