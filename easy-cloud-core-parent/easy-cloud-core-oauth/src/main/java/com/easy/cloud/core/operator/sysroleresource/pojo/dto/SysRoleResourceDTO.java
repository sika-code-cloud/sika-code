package com.easy.cloud.core.operator.sysroleresource.pojo.dto;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;

/**
 * 描述：角色资源关系表数据传输类
 * 
 * @author THINK
 * @date 2018-05-31 09:23:51
 */
public class SysRoleResourceDTO extends EcBaseDTO {
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

	/** 获取资源编号 关联资源表的资源编号 */
	public Long getResourceNo() {
		return this.resourceNo;
	}

	/** 设置资源编号 关联资源表的资源编号 */
	public void setResourceNo(Long resourceNo) {
		this.resourceNo = resourceNo;
	}

}
