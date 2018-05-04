package com.easy.cloud.core.generator.code.java.pojo.dto;

import java.util.Map;

import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.generator.code.base.pojo.dto.EcGenerateBaseDTO;

public class EcGenerateJavaBaseDTO extends EcGenerateBaseDTO{

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
	/** 忽略的属性集合 */
	private Map<String, Boolean> ignoreFields;
	
	public EcGenerateJavaBaseDTO(String projectName, String basePackageName, String moduleName,
			String subModulePackageName, String classBodyName, String classComment) {
		setProjectName(projectName);
		this.basePackageName = basePackageName;
		this.moduleName = moduleName;
		this.subModulePackageName = subModulePackageName;
		this.classBodyName = classBodyName;
		this.classComment = classComment;
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
	
	public Map<String, Boolean> getIgnoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(Map<String, Boolean> ignoreFields) {
		this.ignoreFields = ignoreFields;
	}
	/** 增加忽略属性 */
	public void addIgnoreField(String ignoreFieldName, boolean value) {
		if (this.ignoreFields == null) {
			ignoreFields = EcMapUtils.newHashMap();
		}
		ignoreFields.put(ignoreFieldName, value);
	}
	/**
	 * 
	 * <p>
	 * 根据基础包名，模块名称，子模块名称构建完整包名
	 * </p>
	 *
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 下午4:29:52
	 */
	public String buildFullPackageName(String subModulePackageName) {
		StringBuilder sb = EcStringUtils.newStringBuilder();
		if (EcStringUtils.isNotEmpty(getBasePackageName())) {
			sb.append(getBasePackageName()).append(EcSymbol.STOP);
		}
		if (EcStringUtils.isNotEmpty(getModuleName())) {
			sb.append(getModuleName()).append(EcSymbol.STOP);
		}
		if (EcStringUtils.isNotEmpty(subModulePackageName)) {
			sb.append(subModulePackageName);
		}
		return sb.toString();
	}
	/**
	 * 
	 * <p>
	 * 根据基础包名，模块名称，子模块名称构建完整包名
	 * </p>
	 *
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 下午4:29:52
	 */
	public String buildFullPackageName() {
		return buildFullPackageName(this.subModulePackageName);
	}
}
