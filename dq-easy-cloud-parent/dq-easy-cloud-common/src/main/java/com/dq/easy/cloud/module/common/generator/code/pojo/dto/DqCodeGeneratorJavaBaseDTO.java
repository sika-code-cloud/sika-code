package com.dq.easy.cloud.module.common.generator.code.pojo.dto;

public class DqCodeGeneratorJavaBaseDTO {
	/** java文件所在包名 */
	private String packageName;
	/** 类名简称 */
	private String simpleName;
	/** 完整类名 */
	private String fullName;
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getSimpleName() {
		return simpleName;
	}
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
