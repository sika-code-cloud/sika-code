package com.dq.easy.cloud.module.common.generator.code.java.pojo.dto;

public class DqGenerateJavaBaseDTO {
	/** 项目名称 */
	private String projectName;
	/** 基础包名称 */
	private String basePackageName;
	/** 模块名称 */
	private String moduleName;
	/** 子模块包名称 */
	private String subModulePackageName;
	/** 类主体名称 */
	private String classBodyName;
	/** 类注释 */
	private String classComment;
	public DqGenerateJavaBaseDTO(String projectName, String basePackageName, String moduleName,
			String subModulePackageName, String classBodyName, String classComment) {
		this.projectName = projectName;
		this.basePackageName = basePackageName;
		this.moduleName = moduleName;
		this.subModulePackageName = subModulePackageName;
		this.classBodyName = classBodyName;
		this.classComment = classComment;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getBasePackageName() {
		return basePackageName;
	}

	public void setBasePackageName(String basePackageName) {
		this.basePackageName = basePackageName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSubModulePackageName() {
		return subModulePackageName;
	}

	public void setSubModulePackageName(String subModulePackageName) {
		this.subModulePackageName = subModulePackageName;
	}

	public String getClassBodyName() {
		return classBodyName;
	}

	public void setClassBodyName(String classBodyName) {
		this.classBodyName = classBodyName;
	}

	public String getClassComment() {
		return classComment;
	}

	public void setClassComment(String classComment) {
		this.classComment = classComment;
	}

}
