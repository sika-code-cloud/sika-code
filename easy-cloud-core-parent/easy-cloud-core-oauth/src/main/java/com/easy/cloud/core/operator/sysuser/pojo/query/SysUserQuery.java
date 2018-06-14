package com.easy.cloud.core.operator.sysuser.pojo.query;

import com.easy.cloud.core.basic.pojo.query.EcBaseQuery;

/**
 * 描述：查询类
 * 
 * @author THINK
 * @date 2018-05-30 16:23:53
 */
public class SysUserQuery extends EcBaseQuery {
	private String phone;
	private String username;
	private String password;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
