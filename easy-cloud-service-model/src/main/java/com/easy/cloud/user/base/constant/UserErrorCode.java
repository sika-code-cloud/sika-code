package com.easy.cloud.user.base.constant;

import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.basic.constant.DqBaseErrorCode;

/**
 * 
 * <p>
 * 用户服务错误码
 * </p>
 *
 * <pre>
 * 详细描述
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月2日 下午4:25:50
 */
@Component
public class UserErrorCode extends DqBaseErrorCode{
	/** 用户不存在 */
	public static final String USER_NOT_EXIST = "U_000001";
	/** 用户id不能为空 */
	public static final String USER_ID_CANT_NULL = "U_000002";
	/** 用户对象不能为空 */
	public static final String USER_CANT_NULL = "U_000003";
	/** 用户名不能为空 */
	public static final String USER_NAME_CANT_EMPTY = "U_000004";
	/** 用户密码不能为空 */
	public static final String USER_PASSWOR_CANT_EMPTY = "U_000005";
	/** 用户邮箱不能为空  */
	public static final String USER_EMAIL_CANT_EMPTY = "U_000006";

	static{
		setErrorMsg(USER_NOT_EXIST, "用户不存在");
		setErrorMsg(USER_ID_CANT_NULL, "用户id不能为空");
		setErrorMsg(USER_CANT_NULL, "用户对象不能为空");
		setErrorMsg(USER_NAME_CANT_EMPTY, "用户名不能为空");
		setErrorMsg(USER_PASSWOR_CANT_EMPTY, "用户密码不能为空");
		setErrorMsg(USER_EMAIL_CANT_EMPTY, "用户邮箱不能为空");
	}
}

