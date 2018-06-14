package com.easy.cloud.core.operator.sysrole.pojo.query;

import com.easy.cloud.core.basic.pojo.query.EcBaseQuery;

/**
 * 描述：查询类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:25
 */
public class SysRoleQuery extends EcBaseQuery {
	private Integer roleNo;
	private Long userId;

	public Integer getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(Integer roleNo) {
		this.roleNo = roleNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
