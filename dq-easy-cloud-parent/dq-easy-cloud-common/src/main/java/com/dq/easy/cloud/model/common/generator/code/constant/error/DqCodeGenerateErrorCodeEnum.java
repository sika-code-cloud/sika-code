package com.dq.easy.cloud.model.common.generator.code.constant.error;

import com.dq.easy.cloud.model.basic.constant.error.DqBaseErrorCodeInf;

/**
 * 
 * <p>
 * 代码生成错代码枚举类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月22日 下午4:05:35
 */
public enum DqCodeGenerateErrorCodeEnum implements DqBaseErrorCodeInf{
	AUTHOR_CANT_EMPTY("CODEGEN_000001", "作者不能为空"),
	DATABASE_BASEURL_CANT_EMPTY("CODEGEN_000002", "代码模版基础包路径不能为空"),
	CODE_TEMPLATE_BASE_PACKAGE_PATH_CANT_EMPTY("CODEGEN_000003", "数据库基础url不能为空"),
	DATABASE_PORT_CANT_EMPTY("CODEGEN_000004", "数据库端口不能为空"),
	DATABASE_NAME_CANT_EMPTY("CODEGEN_000005", "数据库名称不能为空"),
	DATABASE_USER_NAME_CANT_EMPTY("CODEGEN_000006", "数据库用户名不能为空"),
	DATABASE_PASSWORD_CANT_EMPTY("CODEGEN_000007", "数据库密码不能为空"),
	DATABASE_DRIVER_CANT_EMPTY("CODEGEN_000008", "数据库驱动不能为空"),
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
