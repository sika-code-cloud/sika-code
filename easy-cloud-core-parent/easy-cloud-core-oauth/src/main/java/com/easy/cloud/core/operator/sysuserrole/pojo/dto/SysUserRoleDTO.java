package com.easy.cloud.core.operator.sysuserrole.pojo.dto;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;

public class SysUserRoleDTO extends EcBaseDTO {
	/** 用户id */
	private String userId;
	/** 角色编号 */
	private String roleNo;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}
}
