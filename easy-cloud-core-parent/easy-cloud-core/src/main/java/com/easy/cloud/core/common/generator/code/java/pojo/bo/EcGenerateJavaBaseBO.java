package com.easy.cloud.core.common.generator.code.java.pojo.bo;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.file.constant.EcFileConstant.EcFileSuffix;
import com.easy.cloud.core.common.file.pojo.desc.EcFileContentBaseDesc;
import com.easy.cloud.core.common.generator.code.base.config.EcCodeGenerateConfig;
import com.easy.cloud.core.common.generator.code.base.config.database.EcDatabaseAbstactConfig;
import com.easy.cloud.core.common.generator.code.base.constant.EcCodeGenerateConstant.EcSourceCodeRelativePath;
import com.easy.cloud.core.common.generator.code.base.constant.EcCodeGenerateConstant.EcTemplateName;
import com.easy.cloud.core.common.generator.code.base.pojo.bo.EcGenerateBO;
import com.easy.cloud.core.common.generator.code.base.pojo.desc.EcTemplateDesc;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.base.sources.database.EcDatabaseDataSources;
import com.easy.cloud.core.common.generator.code.base.utils.EcCodeGenerateUtils;
import com.easy.cloud.core.common.generator.code.java.desc.EcJavaClassContentDesc;
import com.easy.cloud.core.common.generator.code.java.desc.EcJavaContentBaseDesc;
import com.easy.cloud.core.common.generator.code.java.desc.EcJavaFieldContentDesc;
import com.easy.cloud.core.common.generator.code.java.desc.EcJavaFileDesc;
import com.easy.cloud.core.common.generator.code.java.desc.EcJavaMethodContentDesc;
import com.easy.cloud.core.common.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;
import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

public abstract class EcGenerateJavaBaseBO extends EcGenerateBO {
	protected EcGenerateJavaBaseDTO generateJavaBaseDTO;
	protected EcDatabaseAbstactConfig dataBaseConfig;
	protected EcJavaClassContentDesc javaClassContentDesc;
	protected EcDatabaseDataSources databaseDataSources;

	public EcGenerateJavaBaseBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		this(generateJavaBaseDTO, null, generateRule);
		super.setTemplateDesc(new EcTemplateDesc(EcCodeGenerateConfig.CODE_TEMPLATE_BASE_PACKAGE_PATH, EcTemplateName.JAVA));
	}

	public EcGenerateJavaBaseBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcTemplateDesc templateDesc,
			EcGenerateRule generateRule) {
		this.generateJavaBaseDTO = generateJavaBaseDTO;
		super.setTemplateDesc(templateDesc);
		javaClassContentDesc = new EcJavaClassContentDesc(generateRule);
		
		initData();
	}
	
	public void buildEcJavaClassContentDesc(EcJavaClassContentDesc javaClassContentDesc) {
		if (EcBaseUtils.isNotNull(this.javaClassContentDesc)) {
			javaClassContentDesc.setGenerateRule(this.javaClassContentDesc.getGenerateRule());
		}
		this.javaClassContentDesc = javaClassContentDesc;
	}

	private void initData() {
		javaClassContentDesc.setIgnoreFields(this.generateJavaBaseDTO.getIgnoreFields());
		
		EcJavaFileDesc dqFileDesc = new EcJavaFileDesc();
		dqFileDesc.setProjectName(generateJavaBaseDTO.getProjectName());
		dqFileDesc.setFileName(getClassName());
		dqFileDesc.setSourceCodeRelativePath(EcSourceCodeRelativePath.JAVA);
		dqFileDesc.setFileSuffix(EcFileSuffix.JAVA);
		dqFileDesc.setPackageRelativePath(getPackageRelativePath());
		dqFileDesc.setCoverSwitch(generateJavaBaseDTO.isCoverSwith());

		EcLogUtils.info("dqFileDesc", dqFileDesc, LoggerFactory.getLogger(this.getClass()));
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
	public EcGenerateJavaBaseBO buildDatabaseDataSources(EcDatabaseDataSources databaseDataSources) {
		this.databaseDataSources = databaseDataSources;
		return this;
	}

	/** 获取类名endWith */
	protected abstract String getClassNameEndWith();

	/** 获取注释endWith */
	protected abstract String getClassCommentEndWith();

	/** 获取类的Modifier列表 */
	protected abstract void buildModifiers();

	/** 获取继承父类的class */
	protected void buildExtendsParentClass() {
		
	}

	/** 获取实现的接口列表 */
	protected void buildImplementsInterfaces() {
		
	}

	/** 获取构造函数列表 */
	protected void buildConstructors() {
		
	}

	/** 获取属性列表 */
	protected void buildFields() {
		
	}

	/** 获取方法列表 */
	protected void buildMethods() {

	}

	
	/** 获取类的注解列表 */
	protected void buildAnnotations() {
		
	}
	/** 获取类泛型参数列表 */
	protected void buildGenericitys() {

	}

	/** 根据数据源构建属性列表 */
	protected List<EcJavaFieldContentDesc> getFieldsByDatabaseDataSources() {
		javaClassContentDesc.buildDataByDatabaseSources(databaseDataSources);
		return javaClassContentDesc.getFields();
	}

	/** 根据属性列表获取方法列表 */
	protected List<EcJavaMethodContentDesc> getMethodsByFields() {
		if (EcCollectionsUtils.isEmpty(javaClassContentDesc.getFields())) {
			buildFields();
		}
		return javaClassContentDesc.buildMethodsByFields().getMethods();
	}

	/** 根据属性列表获取构造函数列表 */
	protected List<EcJavaMethodContentDesc> getConstructorsByFields() {
		if (EcCollectionsUtils.isEmpty(javaClassContentDesc.getFields())) {
			buildFields();
		}
		return javaClassContentDesc.buildConstructorsDataByFields().getConstructors();
	}

	/** 获取类的注释 */
	protected String getClassComment() {
		StringBuilder sb = EcStringUtils.newStringBuilder();
		if (EcStringUtils.isNotEmpty(generateJavaBaseDTO.getClassComment())) {
			sb.append(generateJavaBaseDTO.getClassComment());
		} else if (EcBaseUtils.isNotNull(databaseDataSources)){
			sb.append(databaseDataSources.getTableComment());
		}
		if (EcStringUtils.isNotEmpty(getClassCommentEndWith())) {
			sb.append(getClassCommentEndWith());
		}
		return sb.toString();
	}

	/** 获取类的名称 */
	protected String getClassName() {
		StringBuilder classNameBuild = EcStringUtils.newStringBuilder();
		classNameBuild.append(EcStringUtils.capitalize(generateJavaBaseDTO.getClassBodyName()));
		if (EcStringUtils.isNotEmpty(getClassNameEndWith())) {
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
		StringBuilder sb = EcStringUtils.newStringBuilder();
		if (EcStringUtils.isNotEmpty(generateJavaBaseDTO.getBasePackageName())) {
			String basePackagePath = EcCodeGenerateUtils
					.changePackageNameToPath(generateJavaBaseDTO.getBasePackageName());
			sb.append(basePackagePath).append(EcSymbol.BACK_SLASH);
		}
		if (EcStringUtils.isNotEmpty(generateJavaBaseDTO.getModuleName())) {
			sb.append(generateJavaBaseDTO.getModuleName()).append(EcSymbol.BACK_SLASH);
		}
		if (EcStringUtils.isNotEmpty(generateJavaBaseDTO.getSubModulePackageName())) {
			String subModulePackagePath = EcCodeGenerateUtils
					.changePackageNameToPath(generateJavaBaseDTO.getSubModulePackageName());
			sb.append(subModulePackagePath).append(EcSymbol.BACK_SLASH);
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
	protected <T extends EcJavaContentBaseDesc> T getCustomJavaContentByEndwith(String nameEndwith,
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
	public EcFileContentBaseDesc getFileContentDesc() {
		return javaClassContentDesc;
	}

}
