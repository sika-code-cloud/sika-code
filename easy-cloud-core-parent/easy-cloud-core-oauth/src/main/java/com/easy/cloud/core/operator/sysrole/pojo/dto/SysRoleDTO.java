package com.easy.cloud.core.operator.sysrole.pojo.dto;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;

public class SysRoleDTO extends EcBaseDTO {
	/** 角色编号 */
	private Integer roleNo;
	/** 角色描述 */
	private String description;

	public Integer getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(Integer roleNo) {
		this.roleNo = roleNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
