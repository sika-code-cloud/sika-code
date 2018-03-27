package com.easy.cloud.user.base.pojo.query;

import com.dq.easy.cloud.module.basic.pojo.query.DqBaseQuery;

/**
 * 用户查询条件类
 * 
 * @author daiqi
 * @date 2018年1月8日 下午6:32:51
 */
public class UserQuery extends DqBaseQuery{
	/** 用户Id */
	private Long userId;
	/** 用户名 */
	private String UserName;
	/** 密码 */
	private String password;
	/** 手机号 */
	private String phoneNumber;
	/** 邮箱 */
	private String email;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
