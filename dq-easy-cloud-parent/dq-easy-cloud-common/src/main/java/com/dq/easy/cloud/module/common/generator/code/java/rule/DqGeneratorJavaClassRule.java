package com.dq.easy.cloud.module.common.generator.code.java.rule;

import com.dq.easy.cloud.module.common.generator.code.common.rule.DqGenerateRule;

/**
 * 生成java类规则
 * 
 * @author daiqi
 * @date 2018年3月24日 上午10:28:06
 */
public class DqGeneratorJavaClassRule implements DqGenerateRule {
	/** 生成Get方法 */
	private boolean generateField;
	/** 生成Get方法 */
	private boolean generateGetMethod;
	/** 生成Set方法 */
	private boolean generateSetMethod;
	/** 生成Build方法 */
	private boolean generateBuildMethod;

	public DqGeneratorJavaClassRule(boolean generateField, boolean generateGetMethod, boolean generateSetMethod,
			boolean generateBuildMethod) {
		super();
		this.generateField = generateField;
		this.generateGetMethod = generateGetMethod;
		this.generateSetMethod = generateSetMethod;
		this.generateBuildMethod = generateBuildMethod;
	}

	public boolean isGenerateField() {
		return generateField;
	}

	public void setGenerateField(boolean generateField) {
		this.generateField = generateField;
	}

	public boolean isGenerateGetMethod() {
		return generateGetMethod;
	}

	public void setGenerateGetMethod(boolean generateGetMethod) {
		this.generateGetMethod = generateGetMethod;
	}

	public boolean isGenerateSetMethod() {
		return generateSetMethod;
	}

	public void setGenerateSetMethod(boolean generateSetMethod) {
		this.generateSetMethod = generateSetMethod;
	}

	public boolean isGenerateBuildMethod() {
		return generateBuildMethod;
	}

	public void setGenerateBuildMethod(boolean generateBuildMethod) {
		this.generateBuildMethod = generateBuildMethod;
	}
	public DqGeneratorJavaClassRule buildGenerateGetMethod(boolean generateGetMethod) {
		this.generateGetMethod = generateGetMethod;
		return this;
	}
	public DqGeneratorJavaClassRule buildGenerateSetMethod(boolean generateSetMethod) {
		this.generateSetMethod = generateSetMethod;
		return this;
	}
	public DqGeneratorJavaClassRule buildGenerateBuildMethod(boolean generateBuildMethod) {
		this.generateBuildMethod = generateBuildMethod;
		return this;
	}
}
