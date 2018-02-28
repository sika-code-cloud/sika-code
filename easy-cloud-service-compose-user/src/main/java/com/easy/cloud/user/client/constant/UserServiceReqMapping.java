package com.easy.cloud.user.client.constant;

import com.dq.easy.cloud.model.basic.constant.DqBaseServiceReqMapping;

/**
 * 
 * <p>
 * 用户服务接口映射类
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月6日 上午9:48:32
 */
public class UserServiceReqMapping extends DqBaseServiceReqMapping{
	/** 用户服务请求映射---用户注册---user/register */
	public static final String REGISTER = "user/register";
	/** 用户服务请求映射---通过用户邮箱获取用户信息---user/findByEmail */
	public static final String FIND_BY_EMAIL = "user/findByEmail";
	/** 用户服务请求映射---通过用户名和密码登录---user/loginByUserNameAndPassword */
	public static final String LOGIN_BY_USERNAME_AND_PASSWORD = "user/loginByUserNameAndPassword";
	/** 用户服务请求映射---通过邮箱和密码登录---user/loginByEmailAndPassword */
	public static final String LOGIN_BY_EMAIL_AND_PASSWORD = "user/loginByEmailAndPassword";
}
