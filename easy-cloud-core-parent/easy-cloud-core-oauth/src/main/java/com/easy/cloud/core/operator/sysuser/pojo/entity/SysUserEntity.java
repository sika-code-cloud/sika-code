package com.easy.cloud.core.operator.sysuser.pojo.entity;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

/**
 * 描述：系统用户表持久化类
 * 
 * @author THINK
 * @date 2018-05-30 16:23:53
 */
public class SysUserEntity extends EcBaseEntity {
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
		
	/** 获取手机号 */
	public String getPhone() {
		return this.phone;
	}

	/** 设置手机号 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** 构建手机号 */
	public SysUserEntity buildPhone(String phone) {
		this.phone = phone;
		return this;
	}

	/** 获取用户名 */
	public String getUsername() {
		return this.username;
	}

	/** 设置用户名 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** 构建用户名 */
	public SysUserEntity buildUsername(String username) {
		this.username = username;
		return this;
	}

	/** 获取用户密码，经过MD5加密后的密码 32位 */
	public String getPassword() {
		return this.password;
	}

	/** 设置用户密码，经过MD5加密后的密码 32位 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 构建用户密码，经过MD5加密后的密码 32位 */
	public SysUserEntity buildPassword(String password) {
		this.password = password;
		return this;
	}

	/** 获取用作加密的盐 */
	public String getSalt() {
		return this.salt;
	}

	/** 设置用作加密的盐 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/** 构建用作加密的盐 */
	public SysUserEntity buildSalt(String salt) {
		this.salt = salt;
		return this;
	}

	/** 获取锁定标志 1 锁定 0 未锁 */
	public Integer getLocked() {
		return this.locked;
	}

	/** 设置锁定标志 1 锁定 0 未锁 */
	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	/** 构建锁定标志 1 锁定 0 未锁 */
	public SysUserEntity buildLocked(Integer locked) {
		this.locked = locked;
		return this;
	}

}
