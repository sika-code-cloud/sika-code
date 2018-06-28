package com.easy.cloud.core.operator.sysuser.pojo.dto;

import org.crazycake.shiro.AuthCachePrincipal;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;

public class SysUserDTO extends EcBaseDTO implements AuthCachePrincipal{
	
	private static final long serialVersionUID = -4233594150543768075L;
	/** 手机号 */
	private String phone;
	/** 用户名 */
	private String username;
	/** 用户密码，经过MD5加密后的密码 32位 */
	private String password;
	/** 用作加密的盐 */
	private String salt;
	/** 锁定标志 1 锁定 0 未锁 */
	private Integer locked;
	/** 登录名称 */
	private String loginName;

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public String getAuthCacheKey() {
		return username;
	}
}
