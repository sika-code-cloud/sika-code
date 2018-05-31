package com.easy.cloud.core.operator.sysrole.pojo.entity;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

/**
 * 描述：角色表持久化类
 * 
 * @author daiqi
 * @date 2018-05-31 22:19:52
 */
public class SysRoleEntity extends EcBaseEntity {
	private static final long serialVersionUID = -7762535757675558516L;
	/** 角色名称 */
	private String name;
	/** 角色编号 */
	private Integer roleNo;
	/** 角色描述 */
	private String description;
		
	/** 获取角色名称 */
	public String getName() {
		return this.name;
	}

	/** 设置角色名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 构建角色名称 */
	public SysRoleEntity buildName(String name) {
		this.name = name;
		return this;
	}

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
