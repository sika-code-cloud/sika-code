package com.dq.easy.cloud.module.common.generator.code.base.pojo.dto;

public class DqGenerateBaseDTO {
	/** 文件覆盖开关---true---覆盖---false存在不覆盖 */
	private boolean coverSwith;
	/** 项目名称 */
	private String projectName;

	public boolean isCoverSwith() {
		return coverSwith;
	}

	public void setCoverSwith(boolean coverSwith) {
		this.coverSwith = coverSwith;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
