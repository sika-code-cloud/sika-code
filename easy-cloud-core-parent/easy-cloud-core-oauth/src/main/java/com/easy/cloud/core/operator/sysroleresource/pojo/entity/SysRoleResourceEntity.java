package com.easy.cloud.core.operator.sysroleresource.pojo.entity;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

/**
 * 描述：角色资源关系表持久化类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:33
 */
public class SysRoleResourceEntity extends EcBaseEntity {
	/** 角色编号 关联角色表的角色编号 */
	private Long roleNo;
	/** 资源编号 关联资源表的资源编号 */
	private Long resourceNo;
		
	/** 获取角色编号 关联角色表的角色编号 */
	public Long getRoleNo() {
		return this.roleNo;
	}

	/** 设置角色编号 关联角色表的角色编号 */
	public void setRoleNo(Long roleNo) {
		this.roleNo = roleNo;
	}

	/** 构建角色编号 关联角色表的角色编号 */
	public SysRoleResourceEntity buildRoleNo(Long roleNo) {
		this.roleNo = roleNo;
		return this;
	}

	/** 获取资源编号 关联资源表的资源编号 */
	public Long getResourceNo() {
		return this.resourceNo;
	}

	/** 设置资源编号 关联资源表的资源编号 */
	public void setResourceNo(Long resourceNo) {
		this.resourceNo = resourceNo;
	}

	/** 构建资源编号 关联资源表的资源编号 */
	public SysRoleResourceEntity buildResourceNo(Long resourceNo) {
		this.resourceNo = resourceNo;
		return this;
	}

}
