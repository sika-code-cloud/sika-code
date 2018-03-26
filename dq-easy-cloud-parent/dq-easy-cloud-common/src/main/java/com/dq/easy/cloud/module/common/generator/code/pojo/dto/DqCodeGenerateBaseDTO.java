package com.dq.easy.cloud.module.common.generator.code.pojo.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dq.easy.cloud.module.common.generator.code.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.module.common.generator.code.constant.DqCodeGenerateConstant.DqCodeProject;
import com.dq.easy.cloud.module.common.generator.code.utils.DqCodeGenerateUtils;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

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
	/** 文件后缀 */
	private String fileSuffix;
	/** 模块所在基础包名 */
	private String modelBasePackageName;
	/** 模板名称 */
	private String templateName;
	/** 项目根路径 */
	private String projectRootPath;
	/** 源文件完整路径 */
	private String sourceFilePathFull;
	/** 源文件相对路径 */
	private String sourceFilePathRelative;
	/** 子模块的相对模块的包名 */
	private String subModuleRelativePackageName;
	/** 包名---完整的 */
	private String packageNameFull;
	/** 类名起始字符串 */
	private String classNameStartWith;
	/** 需要去除的类名起始字符串 */
	private String needSubstrClassNameStartWith;
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
	/** 文件注释 */
	private String fileComment;
	/** 属性数据传输对象列表 */
	private List<?> fieldDTOs;
	/** 导入的class列表 */
	private Set<String> importClazzs;
	/** 继承的父类 */
	private String extendsParentClass;

	public void initClassNameFullDefault() {
		setClassNameFull(DqCodeGenerateUtils.getClassNameFull(this));
	}

	public void initPackageNameFullDefault() {
		setPackageNameFull(DqCodeGenerateUtils.getPackageNameFull(this));
	}

	public void initFilePathFullDefault() {
		setFilePathFull(DqCodeGenerateUtils.getFilePathFull(this));
	}

	public void initPackgePathFullDefault() {
		setPackgePathFull(DqCodeGenerateUtils.getPackagePathFull(this));
	}

	public void initSourceFilePathFullDefault() {
		setSourceFilePathFull(DqCodeGenerateUtils.getSourceFilePathFull(this));
	}

	public void initAuthorDefault() {
		setAuthor(DqCodeGenerateConfig.AUTHOR_DEFAULT);
	}

	public void initCodeTemplateBasePackagePathDefault() {
		setCodeTemplateBasePackagePath(DqCodeGenerateConfig.CODE_TEMPLATE_BASE_PACKAGE_PATH);
	}

	public void initProjectRootPathDefault() {
		setProjectRootPath(DqCodeProject.PROJECT_ROOT_BASE_PATH_DEFAULT);
	}

	public void initClassNameBody() {
		if (DqStringUtils.isNotEmpty(needSubstrClassNameStartWith)) {
			if (DqStringUtils.startsWithIgnoreCase(classNameBody, needSubstrClassNameStartWith)) {
				int subStartIndex = needSubstrClassNameStartWith.length();
				this.classNameBody = DqStringUtils.substring(classNameBody, subStartIndex);
			}
		}
	}

	public void initImportClazzsDefault() {
		Set<String> importClazzs = new HashSet<>();
		setImportClazzs(importClazzs);
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

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
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

	public List<?> getFieldDTOs() {
		return fieldDTOs;
	}

	public void setFieldDTOs(List<?> fieldDTOs) {
		this.fieldDTOs = fieldDTOs;
	}

	public String getSourceFilePathFull() {
		return sourceFilePathFull;
	}

	public void setSourceFilePathFull(String sourceFilePathFull) {
		this.sourceFilePathFull = sourceFilePathFull;
	}

	public String getFileComment() {
		return fileComment;
	}

	public void setFileComment(String fileComment) {
		this.fileComment = fileComment;
	}

	public Set<String> getImportClazzs() {
		return importClazzs;
	}

	public void setImportClazzs(Set<String> importClazzs) {
		this.importClazzs = importClazzs;
	}

	public String getNeedSubstrClassNameStartWith() {
		return needSubstrClassNameStartWith;
	}

	public void setNeedSubstrClassNameStartWith(String needSubstrClassNameStartWith) {
		this.needSubstrClassNameStartWith = needSubstrClassNameStartWith;
	}

	public String getExtendsParentClass() {
		return extendsParentClass;
	}

	public void setExtendsParentClass(String extendsParentClass) {
		this.extendsParentClass = extendsParentClass;
	}

	public String getSourceFilePathRelative() {
		return sourceFilePathRelative;
	}

	public void setSourceFilePathRelative(String sourceFilePathRelative) {
		this.sourceFilePathRelative = sourceFilePathRelative;
	}

	public String getCreateDate() {
		return DqCodeGenerateConfig.CREATE_DATE;
	}

}
