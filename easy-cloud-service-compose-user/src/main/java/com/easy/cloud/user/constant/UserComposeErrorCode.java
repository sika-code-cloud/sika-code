package com.easy.cloud.user.constant;

import org.springframework.stereotype.Component;

import com.easy.cloud.user.base.constant.UserErrorCode;

/**
 * 
 * <p>
 * 用户组合服务错误码
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月6日 上午11:40:43
 */
@Component
public class UserComposeErrorCode extends UserErrorCode{
	/** 用户邮箱不能为空  */
	public static final String LOGIN_MODE_WRONG = "UC_000001";

	static{
		setErrorMsg(LOGIN_MODE_WRONG, "登录类型有误");
	}
}
