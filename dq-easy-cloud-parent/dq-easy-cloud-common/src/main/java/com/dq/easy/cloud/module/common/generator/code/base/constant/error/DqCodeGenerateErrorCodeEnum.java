package com.dq.easy.cloud.module.common.generator.code.base.constant.error;

import com.dq.easy.cloud.module.basic.constant.error.DqBaseErrorCodeInf;

/**
 * 
 * <p>
 * 代码生成错误代码枚举类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月22日 下午4:05:35
 */
public enum DqCodeGenerateErrorCodeEnum implements DqBaseErrorCodeInf{
	AUTHOR_CANT_EMPTY("CODEGEN_000001", "作者不能为空"),
	DATABASE_BASEURL_CANT_EMPTY("CODEGEN_000002", "数据库基础url不能为空"),
	CODE_TEMPLATE_BASE_PACKAGE_PATH_CANT_EMPTY("CODEGEN_000003", "代码模版基础包路径不能为空"),
	DATABASE_PORT_CANT_EMPTY("CODEGEN_000004", "数据库端口不能为空"),
	DATABASE_NAME_CANT_EMPTY("CODEGEN_000005", "数据库名称不能为空"),
	DATABASE_USER_NAME_CANT_EMPTY("CODEGEN_000006", "数据库用户名不能为空"),
	DATABASE_PASSWORD_CANT_EMPTY("CODEGEN_000007", "数据库密码不能为空"),
	DATABASE_DRIVER_CANT_EMPTY("CODEGEN_000008", "数据库驱动不能为空"),
	PACKGE_PATH_FULL_CANT_EMPTY("CODEGEN_000009", "包完整路径不能为空"),
	FILE_PATH_FULL_CANT_EMPTY("CODEGEN_000010", "文件完整路径不能为空"),
	DATABASE_CONFIG_CANT_NULL("CODEGEN_000011", "数据库配置对象不能为空"),
	RESULTSET_CANT_NULL("CODEGEN_000012", "resultSet对象不能为空"),
	GET_PROJECT_PATH_FAIL("CODEGEN_000013", "获取project路径失败"),
	TABLE_NAME_CANT_NULL("CODEGEN_000013", "tableName不能为空"),
	;
	private String errorCode;
	private String errorMsg;

	private DqCodeGenerateErrorCodeEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getErrorMsg() {
		return errorMsg;
	}

}
