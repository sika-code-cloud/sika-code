package com.easy.cloud.core.operator.sysuserrole.pojo.entity;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

/**
 * 描述：系统用户角色关系表持久化类
 * 
 * @author THINK
 * @date 2018-05-30 16:23:59
 */
public class SysUserRoleEntity extends EcBaseEntity {
	/** 用户id */
	private Long userId;
	/** 角色编号 */
	private String roleNo;
		
	/** 获取用户id */
	public Long getUserId() {
		return this.userId;
	}

	/** 设置用户id */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/** 构建用户id */
	public SysUserRoleEntity buildUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	/** 获取角色编号 */
	public String getRoleNo() {
		return this.roleNo;
	}

	/** 设置角色编号 */
	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	/** 构建角色编号 */
	public SysUserRoleEntity buildRoleNo(String roleNo) {
		this.roleNo = roleNo;
		return this;
	}

}
