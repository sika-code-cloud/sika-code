package com.easy.cloud.core.operator.sysresource.pojo.query;

import java.util.List;

import com.easy.cloud.core.basic.pojo.query.EcBaseQuery;

/**
 * 描述：查询类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:17
 */
public class SysResourceQuery extends EcBaseQuery {
	private Integer resourceNo;
	private Integer roleNo;
	private List<Integer> roleNos;
	public Integer getResourceNo() {
		return resourceNo;
	}
	public void setResourceNo(Integer resourceNo) {
		this.resourceNo = resourceNo;
	}
	public Integer getRoleNo() {
		return roleNo;
	}
	public void setRoleNo(Integer roleNo) {
		this.roleNo = roleNo;
	}
	public List<Integer> getRoleNos() {
		return roleNos;
	}
	public void setRoleNos(List<Integer> roleNos) {
		this.roleNos = roleNos;
	}
	
}
