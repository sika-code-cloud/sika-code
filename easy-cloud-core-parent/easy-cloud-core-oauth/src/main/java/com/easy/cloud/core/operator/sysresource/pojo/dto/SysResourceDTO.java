package com.easy.cloud.core.operator.sysresource.pojo.dto;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;

import java.util.ArrayList;
import java.util.List;

public class SysResourceDTO extends EcBaseDTO {
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
	/** 权限字符串 */
	private String permission;

	private List<SysResourceDTO> children;

	public SysResourceDTO() {
		children = new ArrayList<>();
	}

	public Integer getResourceNo() {
		return resourceNo;
	}

	public void setResourceNo(Integer resourceNo) {
		this.resourceNo = resourceNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getParentNo() {
		return parentNo;
	}

	public void setParentNo(Integer parentNo) {
		this.parentNo = parentNo;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public List<SysResourceDTO> getChildren() {
		return children;
	}

	public void setChildren(List<SysResourceDTO> children) {
		this.children = children;
	}
}
