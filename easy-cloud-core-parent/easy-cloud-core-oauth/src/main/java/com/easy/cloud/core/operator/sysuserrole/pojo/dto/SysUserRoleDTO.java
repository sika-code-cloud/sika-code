package com.easy.cloud.core.operator.sysuserrole.pojo.dto;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;

public class SysUserRoleDTO extends EcBaseDTO {
	private static final long serialVersionUID = 6688866261634799073L;
	/** 用户id */
	private Long userId;
	/** 角色编号 */
	private String roleNo;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}
}
