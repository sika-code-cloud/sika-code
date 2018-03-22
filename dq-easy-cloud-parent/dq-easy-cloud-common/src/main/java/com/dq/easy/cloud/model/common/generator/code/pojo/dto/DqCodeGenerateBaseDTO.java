package com.dq.easy.cloud.model.common.generator.code.pojo.dto;

import com.dq.easy.cloud.model.common.generator.code.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.model.common.generator.code.constant.DqCodeGenerateConstant.DqClassNameEndWith;
import com.dq.easy.cloud.model.common.generator.code.constant.DqCodeGenerateConstant.DqClassNameStartWith;
import com.dq.easy.cloud.model.common.generator.code.utils.DqCodeGenerateUtils;
import com.dq.easy.cloud.model.common.properties.utils.DqPropertiesUtils;

/**
 * 
 * <p>
 * 代码生成基础数据传输对象
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：所有的代码生成数据传输对象子类必须最终继承此类
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年3月22日 上午11:57:00
 */
public class DqCodeGenerateBaseDTO {
	/** 创建者 */
	private String author;
	/** 代码模版基础包路径 */
	private String codeTemplateBasePackagePath;
	/** 模块所在包名 */
	private String modelBasePackageName;
	/** 模板名称 */
	private String templateName;
	/** 项目根路径 */
	private String projectRootPath;
	/** 子模块的相对模块的包名 */
	private String subModuleRelativePackageName;
	/** 包名---完整的 */
	private String packageNameFull;
	/** 类名其实字符串 */
	private String classNameStartWith;
	/** 类名结束字符串 */
	private String classNameEndWith;
	/** 类名主体 */
	private String classNameBody;
	/** 类名完整的类名 */
	private String classNameFull;
	/** 包完整路径 */
	private String packgePathFull;
	/** 文件完整路径 */
	private String filePathFull;

	public DqCodeGenerateBaseDTO initClassNameFullDefault() {
		setClassNameFull(DqCodeGenerateUtils.getClassNameFull(this));
		return this;
	}

	public DqCodeGenerateBaseDTO initPackageNameFullDefault() {
		setPackageNameFull(DqCodeGenerateUtils.getPackageNameFull(this));
		return this;
	}

	public DqCodeGenerateBaseDTO initFilePathFullDefault() {
		setCodeTemplateBasePackagePath(DqCodeGenerateUtils.getPackagePathFull(this));
		return this;
	}

	public DqCodeGenerateBaseDTO initPackgePathFullDefault() {
		setCodeTemplateBasePackagePath(DqCodeGenerateUtils.getFilePathFull(this));
		return this;
	}

	public DqCodeGenerateBaseDTO initAuthorDefault() {
		setCodeTemplateBasePackagePath(DqCodeGenerateConfig.AUTHOR_DEFAULT);
		return this;
	}

	public DqCodeGenerateBaseDTO initCodeTemplateBasePackagePathDefault() {
		setCodeTemplateBasePackagePath(DqCodeGenerateConfig.CODE_TEMPLATE_BASE_PACKAGE_PATH);
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCodeTemplateBasePackagePath() {
		return codeTemplateBasePackagePath;
	}

	public void setCodeTemplateBasePackagePath(String codeTemplateBasePackagePath) {
		this.codeTemplateBasePackagePath = codeTemplateBasePackagePath;
	}

	public String getModelBasePackageName() {
		return modelBasePackageName;
	}

	public void setModelBasePackageName(String modelBasePackageName) {
		this.modelBasePackageName = modelBasePackageName;
	}

	public String getSubModuleRelativePackageName() {
		return subModuleRelativePackageName;
	}

	public void setSubModuleRelativePackageName(String subModuleRelativePackageName) {
		this.subModuleRelativePackageName = subModuleRelativePackageName;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getProjectRootPath() {
		return projectRootPath;
	}

	public String getClassNameStartWith() {
		return classNameStartWith;
	}

	public void setClassNameStartWith(String classNameStartWith) {
		this.classNameStartWith = classNameStartWith;
	}

	public String getClassNameEndWith() {
		return classNameEndWith;
	}

	public void setClassNameEndWith(String classNameEndWith) {
		this.classNameEndWith = classNameEndWith;
	}

	public void setProjectRootPath(String projectRootPath) {
		this.projectRootPath = projectRootPath;
	}


	public String getPackgePathFull() {
		return packgePathFull;
	}

	public void setPackgePathFull(String packgePathFull) {
		this.packgePathFull = packgePathFull;
	}

	public String getFilePathFull() {
		return filePathFull;
	}

	public void setFilePathFull(String filePathFull) {
		this.filePathFull = filePathFull;
	}

	public String getClassNameBody() {
		return classNameBody;
	}

	public void setClassNameBody(String classNameBody) {
		this.classNameBody = classNameBody;
	}

	public String getClassNameFull() {
		return classNameFull;
	}

	public void setClassNameFull(String classNameFull) {
		this.classNameFull = classNameFull;
	}

	public String getPackageNameFull() {
		return packageNameFull;
	}

	public void setPackageNameFull(String packageNameFull) {
		this.packageNameFull = packageNameFull;
	}

	public String getCreateDate() {
		return DqCodeGenerateConfig.CREATE_DATE;
	}

}
