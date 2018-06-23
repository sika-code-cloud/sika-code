package com.easy.cloud.core.operator.sysrole.pojo.dto;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;

/**
 * 描述：角色表数据传输类
 * 
 * @author daiqi
 * @date 2018-05-31 22:20:29
 */
public class SysRoleDTO extends EcBaseDTO {
	private static final long serialVersionUID = -1229049316109023670L;
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
