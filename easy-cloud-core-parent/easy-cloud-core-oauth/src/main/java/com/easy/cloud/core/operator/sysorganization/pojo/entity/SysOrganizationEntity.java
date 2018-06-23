package com.easy.cloud.core.operator.sysorganization.pojo.entity;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

/**
 * 描述：组织架构表持久化类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:08
 */
public class SysOrganizationEntity extends EcBaseEntity {
	/** 组织编号 */
	private Integer organizationNo;
	/** 组织结构名称 */
	private String name;
	/** 直接父编号 */
	private Integer parentNo;
	/** 父编号列表 json数组 从左到右 辈分依次减小 */
	private String parentNos;
		
	/** 获取组织编号 */
	public Integer getOrganizationNo() {
		return this.organizationNo;
	}

	/** 设置组织编号 */
	public void setOrganizationNo(Integer organizationNo) {
		this.organizationNo = organizationNo;
	}

	/** 构建组织编号 */
	public SysOrganizationEntity buildOrganizationNo(Integer organizationNo) {
		this.organizationNo = organizationNo;
		return this;
	}

	/** 获取组织结构名称 */
	public String getName() {
		return this.name;
	}

	/** 设置组织结构名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 构建组织结构名称 */
	public SysOrganizationEntity buildName(String name) {
		this.name = name;
		return this;
	}

	/** 获取直接父编号 */
	public Integer getParentNo() {
		return this.parentNo;
	}

	/** 设置直接父编号 */
	public void setParentNo(Integer parentNo) {
		this.parentNo = parentNo;
	}

	/** 构建直接父编号 */
	public SysOrganizationEntity buildParentNo(Integer parentNo) {
		this.parentNo = parentNo;
		return this;
	}

	/** 获取父编号列表 json数组 从左到右 辈分依次减小 */
	public String getParentNos() {
		return this.parentNos;
	}

	/** 设置父编号列表 json数组 从左到右 辈分依次减小 */
	public void setParentNos(String parentNos) {
		this.parentNos = parentNos;
	}

	/** 构建父编号列表 json数组 从左到右 辈分依次减小 */
	public SysOrganizationEntity buildParentNos(String parentNos) {
		this.parentNos = parentNos;
		return this;
	}

}
