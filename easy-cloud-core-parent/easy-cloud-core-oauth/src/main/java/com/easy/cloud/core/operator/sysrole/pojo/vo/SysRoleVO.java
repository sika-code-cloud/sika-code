package com.easy.cloud.core.operator.sysrole.pojo.vo;

import com.easy.cloud.core.basic.pojo.vo.EcBaseVO;

/**
 * 描述：角色表视图类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:25
 */
public class SysRoleVO extends EcBaseVO {
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

	/** 获取角色描述 */
	public String getDescription() {
		return this.description;
	}

	/** 设置角色描述 */
	public void setDescription(String description) {
		this.description = description;
	}

}
