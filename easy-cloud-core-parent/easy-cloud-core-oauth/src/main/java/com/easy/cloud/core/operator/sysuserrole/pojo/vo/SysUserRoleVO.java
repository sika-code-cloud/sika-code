package com.easy.cloud.core.operator.sysuserrole.pojo.vo;

import com.easy.cloud.core.basic.pojo.vo.EcBaseVO;

/**
 * 描述：系统用户角色关系表视图类
 * 
 * @author THINK
 * @date 2018-05-30 16:23:59
 */
public class SysUserRoleVO extends EcBaseVO {
	/** 用户id */
	private String userId;
	/** 角色编号 */
	private String roleNo;
		
	/** 获取用户id */
	public String getUserId() {
		return this.userId;
	}

	/** 设置用户id */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/** 获取角色编号 */
	public String getRoleNo() {
		return this.roleNo;
	}

	/** 设置角色编号 */
	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

}
