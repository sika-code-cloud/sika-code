package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.dq.easy.cloud.module.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.module.common.file.constant.DqFileConstant.DqFileSuffix;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
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
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;
import com.dq.easy.cloud.module.common.log.utils.DqLogUtils;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

public abstract class DqGenerateJavaBaseBO extends DqGenerateBO {
	protected DqGenerateJavaBaseDTO generateJavaBaseDTO;
	protected DqDatabaseAbstactConfig dataBaseConfig;
	protected DqJavaClassContentDesc javaClassContentDesc;
	protected DqDatabaseDataSources databaseDataSources;

	public DqGenerateJavaBaseBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqTemplateDesc templateDesc,
			DqGenerateRule generateRule) {
		this.generateJavaBaseDTO = generateJavaBaseDTO;
		super.setTemplateDesc(templateDesc);
		javaClassContentDesc = new DqJavaClassContentDesc(generateRule);

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

	}

	@Override
	public void generateCode() throws Exception {
		String className = getClassName();
		// 设置包名
		javaClassContentDesc.setPackageName(getFullPackageName(generateJavaBaseDTO.getSubModulePackageName()));
		// 设置注释
		javaClassContentDesc.setComment(getClassComment());
		// 构建类注解列表---begin
		buildAnnotations();
		// 构建类注解列表---end
		// 设置类的modifier列表---begin
		buildModifiers();
		// 设置类的modifier列表---end
		// 设置类名称
		javaClassContentDesc.setName(className);
		javaClassContentDesc.setSimpleClassType(className);
		// 设置类的泛型参数
		buildGenericitys();
		// 设置继承父类---begin
		buildExtendsParentClass();
		// 设置继承父类---end
		// 设置实现的接口---begin
		buildImplementsInterfaces();
		// 设置实现的接口---end
		// 设置构造函数列表---begin
		buildConstructors();
		// 设置构造函数列表---end
		// 设置属性列表---begin
		buildFields();
		// 设置属性列表---end
		// 设置方法列表---begin
		buildMethods();
		// 设置方法列表---end
		javaClassContentDesc.addImportFullClassType();
		// 调用真正的生成代码方法
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
	 * @author daiqi 创建时间 2018年3月27日 上午11:38:51
	 */
	public DqGenerateJavaBaseBO buildDatabaseDataSources(DqDatabaseDataSources databaseDataSources) {
		this.databaseDataSources = databaseDataSources;
		return this;
	}

	/** 获取类名endWith */
	protected abstract String getClassNameEndWith();

	/** 获取注释endWith */
	protected abstract String getClassCommentEndWith();

	/** 获取类的注解列表 */
	protected abstract void buildAnnotations();

	/** 获取类的Modifier列表 */
	protected abstract void buildModifiers();

	/** 获取继承父类的class */
	protected abstract void buildExtendsParentClass();

	/** 获取实现的接口列表 */
	protected abstract void buildImplementsInterfaces();

	/** 获取构造函数列表 */
	protected abstract void buildConstructors();

	/** 获取属性列表 */
	protected abstract void buildFields();

	/** 获取方法列表 */
	protected abstract void buildMethods();

	/** 获取类泛型参数列表 */
	protected void buildGenericitys() {

	}

	/** 根据数据源构建属性列表 */
	protected List<DqJavaFieldContentDesc> getFieldsByDatabaseDataSources() {
		javaClassContentDesc.buildDataByDatabaseSources(databaseDataSources);
		return javaClassContentDesc.getFields();
	}

	/** 根据属性列表获取方法列表 */
	protected List<DqJavaMethodContentDesc> getMethodsByFields() {
		if (DqCollectionsUtils.isEmpty(javaClassContentDesc.getFields())) {
			buildFields();
		}
		return javaClassContentDesc.buildMethodsByFields().getMethods();
	}

	/** 根据属性列表获取构造函数列表 */
	protected List<DqJavaMethodContentDesc> getConstructorsByFields() {
		if (DqCollectionsUtils.isEmpty(javaClassContentDesc.getFields())) {
			buildFields();
		}
		return javaClassContentDesc.buildConstructorsDataByFields().getConstructors();
	}

	/** 获取类的注释 */
	protected String getClassComment() {
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		if (DqStringUtils.isNotEmpty(generateJavaBaseDTO.getClassComment())) {
			sb.append(generateJavaBaseDTO.getClassComment());
		}
		if (DqStringUtils.isNotEmpty(getClassCommentEndWith())) {
			sb.append(getClassCommentEndWith());
		}
		return sb.toString();
	}

	/** 获取类的名称 */
	protected String getClassName() {
		StringBuilder classNameBuild = DqStringUtils.newStringBuilderDefault();
		classNameBuild.append(DqStringUtils.capitalize(generateJavaBaseDTO.getClassBodyName()));
		if (DqStringUtils.isNotEmpty(getClassNameEndWith())) {
			classNameBuild.append(getClassNameEndWith());
		}
		return classNameBuild.toString();
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
			String basePackagePath = DqCodeGenerateUtils
					.changePackageNameToPath(generateJavaBaseDTO.getBasePackageName());
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
		return generateJavaBaseDTO.buildFullPackageName(subModulePackageName);
	}

	/** 获取自定义的javaContent对象 */
	protected <T extends DqJavaContentBaseDesc> T getCustomJavaContentByEndwith(String nameEndwith,
			String subPackegeName, Class<T> clazz) {
		try {
			T customJavaClassObj = clazz.newInstance();
			String customJavaClassName = generateJavaBaseDTO.getClassBodyName() + nameEndwith;
			customJavaClassObj.setName(customJavaClassName);
			customJavaClassObj.setSimpleClassType(customJavaClassName);
			customJavaClassObj.setPackageName(getFullPackageName(subPackegeName));
			customJavaClassObj.buildFullClassType();
			return customJavaClassObj;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DqFileContentBaseDesc getFileContentDesc() {
		return javaClassContentDesc;
	}

}
