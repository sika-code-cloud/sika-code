package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.dq.easy.cloud.module.basic.constant.DqBaseConstant.DqFileSuffix;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqIgnoreField.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqSourceCodeRelativePath;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.bo.DqGenerateBO;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;
import com.dq.easy.cloud.module.common.generator.code.base.utils.DqCodeGenerateUtils;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaClassContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFileDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaImplInterfaceContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;
import com.dq.easy.cloud.module.common.log.utils.DqLogUtils;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

public abstract class DqGenerateJavaBaseBO extends DqGenerateBO {
	protected DqGenerateJavaBaseDTO generateJavaBaseDTO;
	protected DqDatabaseAbstactConfig dataBaseConfig;
	protected DqJavaClassContentDesc javaClassContentDesc;
	private DqDatabaseDataSources databaseDataSources;
	private DqGenerateRule generateRule;

	public DqGenerateJavaBaseBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqDatabaseAbstactConfig dataBaseConfig,
			DqTemplateDesc templateDesc, DqGenerateRule generateRule) {
		this.generateJavaBaseDTO = generateJavaBaseDTO;
		this.dataBaseConfig = dataBaseConfig;
		super.setTemplateDesc(templateDesc);
		this.generateRule = generateRule;

		initData();
	}
	
	private void initData() {
		DqJavaFileDesc dqFileDesc = new DqJavaFileDesc();
		dqFileDesc.setProjectName(generateJavaBaseDTO.getProjectName());
		dqFileDesc.setFileName(getClassName());
		dqFileDesc.setSourceCodeRelativePath(DqSourceCodeRelativePath.JAVA);
		dqFileDesc.setFileSuffix(DqFileSuffix.JAVA);
		dqFileDesc.setPackageRelativePath(getPackageRelativePath());
		dqFileDesc.setCoverSwitch(generateJavaBaseDTO.isCoverSwith());
		
		DqLogUtils.info("dqFileDesc", dqFileDesc, LoggerFactory.getLogger(this.getClass()));
		super.setFileDesc(dqFileDesc);
		
		super.setFileContentDesc(new DqJavaClassContentDesc(generateRule));
		javaClassContentDesc = (DqJavaClassContentDesc) super.getFileContentDesc();
	}
	
	@Override
	public void generateCode() throws Exception {
		String className = getClassName();
		// 设置包名
		javaClassContentDesc.setPackageName(getFullPackageName(generateJavaBaseDTO.getSubModulePackageName()));
		// 设置注释
		javaClassContentDesc.setComment(getClassComment());
		// 设置类注解列表---begin
		javaClassContentDesc.setAnnotations(getClassAnnotations());
		// 设置类注解列表---end
		// 设置类的modifier列表---begin
		javaClassContentDesc.setModifiers(getClassModifiers());
		// 设置类的modifier列表---end
		// 设置类名称
		javaClassContentDesc.setName(className);
		javaClassContentDesc.setSimpleClassType(className);
		// 设置继承父类---begin
		javaClassContentDesc.setExtendsParentClass(getExtendsParentClass());
		// 设置继承父类---end
		javaClassContentDesc.buildDataByDatabaseSources(databaseDataSources);
		this.buildJavaClassContentOtherData();
//		根据属性构建方法列表
		javaClassContentDesc.buildJavaMethodsByFields();
		javaClassContentDesc.addImportFullClassType();
//		调用真正的生成代码方法
		super.generateCode();
	}
	
	/**
	 * 
	 * <p>
	 * 构建数据源
	 * </p>
	 *
	 * @param databaseDataSources
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月27日 上午11:38:51
	 */
	public DqGenerateJavaBaseBO buildDatabaseDataSources(DqDatabaseDataSources databaseDataSources) {
		this.databaseDataSources = databaseDataSources;
		return this;
	}
	/**
	 * 
	 * <p>
	 * 构建方法列表
	 * </p>
	 *
	 * @param databaseDataSources
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月27日 上午11:38:51
	 */
	public DqGenerateJavaBaseBO buildMethods(List<DqJavaMethodContentDesc> methods) {
		this.javaClassContentDesc.setMethods(methods);
		return this;
	}
	/**
	 * 
	 * <p>
	 * 构建属性列表
	 * </p>
	 *
	 * @param fileds
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月27日 上午11:38:51
	 */
	public DqGenerateJavaBaseBO buildFields(List<DqJavaFieldContentDesc> fields) {
		this.javaClassContentDesc.setFields(fields);
		return this;
	}
	/**
	 * 
	 * <p>
	 * 构建类modifier列表
	 * </p>
	 *
	 * @param fileds
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月27日 上午11:38:51
	 */
	public DqGenerateJavaBaseBO buildModifiers(List<DqJavaModifierDesc> modifiers) {
		this.javaClassContentDesc.setModifiers(modifiers);
		return this;
	}
	/**
	 * 
	 * <p>
	 * 构建类implementsInterfaces列表
	 * </p>
	 *
	 * @param fileds
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月27日 上午11:38:51
	 */
	public DqGenerateJavaBaseBO buildImplementsInterfaces(List<DqJavaImplInterfaceContentDesc> implementsInterfaces) {
		this.javaClassContentDesc.setImplementsInterfaces(implementsInterfaces);
		return this;
	}
	protected abstract DqGenerateJavaBaseBO buildJavaClassContentOtherData();
	/** 获取类的名称 */
	protected String getClassName () {
		StringBuilder classNameBuild = DqStringUtils.newStringBuilderDefault();
		classNameBuild.append(DqStringUtils.capitalize(generateJavaBaseDTO.getClassBodyName()));
		if (DqStringUtils.isNotEmpty(getClassNameEndWith())) {
			classNameBuild.append(getClassNameEndWith());
		}
		return classNameBuild.toString();
	}
	/** 获取类名结束 */
	protected abstract String getClassNameEndWith();
	
	/**
	 * 
	 * <p>
	 * 获取类的注解列表
	 * </p>
	 *
	 * @author daiqi
	 * 创建时间    2018年3月27日 上午9:14:14
	 */
	protected abstract List<DqJavaAnnotationDesc> getClassAnnotations();
	/** 获取集成父类的class */
	protected abstract DqJavaContentBaseDesc getExtendsParentClass();
	
	/** 获取类的注释 */
	protected String getClassComment() {
		return generateJavaBaseDTO.getClassComment();
	}
	
	/** 获取类的modifier列表 */
	protected List<DqJavaModifierDesc> getClassModifiers() {
		// 设置类的modifier列表---begin
		List<DqJavaModifierDesc> modifiers = new ArrayList<>();
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.CLASS));
		return modifiers;
		// 设置类的modifier列表---end
	}
	

	/**
	 * 
	 * <p>
	 * 根据基础包名，模块名称，子模块名称获取完整包路径（相对路径）
	 * </p>
	 *
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 下午4:29:52
	 */
	private String getPackageRelativePath() {
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		if (DqStringUtils.isNotEmpty(generateJavaBaseDTO.getBasePackageName())) {
			String basePackagePath = DqCodeGenerateUtils.changePackageNameToPath(generateJavaBaseDTO.getBasePackageName());
			sb.append(basePackagePath).append(DqSymbol.BACK_SLASH);
		}
		if (DqStringUtils.isNotEmpty(generateJavaBaseDTO.getModuleName())) {
			sb.append(generateJavaBaseDTO.getModuleName()).append(DqSymbol.BACK_SLASH);
		}
		if (DqStringUtils.isNotEmpty(generateJavaBaseDTO.getSubModulePackageName())) {
			String subModulePackagePath = DqCodeGenerateUtils
					.changePackageNameToPath(generateJavaBaseDTO.getSubModulePackageName());
			sb.append(subModulePackagePath).append(DqSymbol.BACK_SLASH);
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * <p>
	 * 根据基础包名，模块名称，子模块名称获取完整包名
	 * </p>
	 *
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 下午4:29:52
	 */
	protected String getFullPackageName(String subModulePackageName) {
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		if (DqStringUtils.isNotEmpty(generateJavaBaseDTO.getBasePackageName())) {
			sb.append(generateJavaBaseDTO.getBasePackageName()).append(DqSymbol.STOP);
		}
		if (DqStringUtils.isNotEmpty(generateJavaBaseDTO.getModuleName())) {
			sb.append(generateJavaBaseDTO.getModuleName()).append(DqSymbol.STOP);
		}
		if (DqStringUtils.isNotEmpty(subModulePackageName)) {
			sb.append(subModulePackageName);
		}
		return sb.toString();
	}
}
