package com.easy.cloud.core.operator.sysrole.pojo.entity;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

/**
 * 描述：角色表持久化类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:25
 */
public class SysRoleEntity extends EcBaseEntity {
	/** 角色编号 */
	private Integer roleNo;
	/** 角色描述 */
	private String description;
		
	/** 获取角色编号 */
	public Integer getRoleNo() {
		return this.roleNo;
	}

	/** 设置角色编号 */
	public void setRoleNo(Integer roleNo) {
		this.roleNo = roleNo;
	}

	/** 构建角色编号 */
	public SysRoleEntity buildRoleNo(Integer roleNo) {
		this.roleNo = roleNo;
		return this;
	}

	/** 获取角色描述 */
	public String getDescription() {
		return this.description;
	}

	/** 设置角色描述 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** 构建角色描述 */
	public SysRoleEntity buildDescription(String description) {
		this.description = description;
		return this;
	}

}
