package com.easy.cloud.core.common.generator.code.base.pojo.desc;

/**
 * 模版描述类
 * 
 * @author daiqi
 * @date 2018年3月24日 上午11:00:52
 */
public class EcTemplateDesc {
	/** 模版基础包路径 */
	private String basePackagePath;
	/** 模版名称 */
	private String name;

	public EcTemplateDesc(String basePackagePath, String name) {
		this.basePackagePath = basePackagePath;
		this.name = name;
	}

	public String getBasePackagePath() {
		return basePackagePath;
	}

	public void setBasePackagePath(String basePackagePath) {
		this.basePackagePath = basePackagePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
