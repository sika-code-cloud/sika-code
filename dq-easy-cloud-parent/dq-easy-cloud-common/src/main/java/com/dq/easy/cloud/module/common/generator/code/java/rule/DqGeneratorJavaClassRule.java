package com.dq.easy.cloud.module.common.generator.code.java.rule;

/**
 * 生成java类规则
 * @author daiqi
 * @date 2018年3月24日 上午10:28:06
 */
public class DqGeneratorJavaClassRule {
	private boolean needGetMethod;
	private boolean needSetMethod;
	private boolean needBuildMethod;
	public boolean isNeedGetMethod() {
		return needGetMethod;
	}
	public void setNeedGetMethod(boolean needGetMethod) {
		this.needGetMethod = needGetMethod;
	}
	public boolean isNeedSetMethod() {
		return needSetMethod;
	}
	public void setNeedSetMethod(boolean needSetMethod) {
		this.needSetMethod = needSetMethod;
	}
	public boolean isNeedBuildMethod() {
		return needBuildMethod;
	}
	public void setNeedBuildMethod(boolean needBuildMethod) {
		this.needBuildMethod = needBuildMethod;
	}
	
}
