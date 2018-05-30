package com.easy.cloud.core.operator.sysresource.pojo.vo;

import com.easy.cloud.core.basic.pojo.vo.EcBaseVO;

/**
 * 描述：资源表视图类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:17
 */
public class SysResourceVO extends EcBaseVO {
	/** 资源编号 */
	private Integer resourceNo;
	/** 资源名称 */
	private String name;
	/** 资源类型 */
	private String type;
	/** 资源url */
	private String url;
	/** 直接父编号 */
	private Integer parentNo;
	/** 父编号列表 json数组 从左到右 辈分依次减小 */
	private String parentNos;
	/** 权限字符串 */
	private String permission;
		
	/** 获取资源编号 */
	public Integer getResourceNo() {
		return this.resourceNo;
	}

	/** 设置资源编号 */
	public void setResourceNo(Integer resourceNo) {
		this.resourceNo = resourceNo;
	}

	/** 获取资源名称 */
	public String getName() {
		return this.name;
	}

	/** 设置资源名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 获取资源类型 */
	public String getType() {
		return this.type;
	}

	/** 设置资源类型 */
	public void setType(String type) {
		this.type = type;
	}

	/** 获取资源url */
	public String getUrl() {
		return this.url;
	}

	/** 设置资源url */
	public void setUrl(String url) {
		this.url = url;
	}

	/** 获取直接父编号 */
	public Integer getParentNo() {
		return this.parentNo;
	}

	/** 设置直接父编号 */
	public void setParentNo(Integer parentNo) {
		this.parentNo = parentNo;
	}

	/** 获取父编号列表 json数组 从左到右 辈分依次减小 */
	public String getParentNos() {
		return this.parentNos;
	}

	/** 设置父编号列表 json数组 从左到右 辈分依次减小 */
	public void setParentNos(String parentNos) {
		this.parentNos = parentNos;
	}

	/** 获取权限字符串 */
	public String getPermission() {
		return this.permission;
	}

	/** 设置权限字符串 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

}
