package com.easy.cloud.user.pojo.query;

import com.easy.cloud.user.base.pojo.query.UserQuery;

/**
 * 
 * <p>
 * 用户组合查询类
 * </p>
 *
 * <pre>
 *  说明：用于用户组合服务的查询类
 *  约定：继承UserQuery
 *  命名规范：Query为后缀
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月6日 上午11:21:41
 */
public class UserComposeQuery extends UserQuery{
	/**
	 * <p>登录方式</p>
	 * <pre>
	 * 详情@see com.easy.cloud.user.constant.LoginMode
	 * </pre>
	 */
	private Integer loginMode;

	public Integer getLoginMode() {
		return loginMode;
	}

	public void setLoginMode(Integer loginMode) {
		this.loginMode = loginMode;
	} 
	
}
