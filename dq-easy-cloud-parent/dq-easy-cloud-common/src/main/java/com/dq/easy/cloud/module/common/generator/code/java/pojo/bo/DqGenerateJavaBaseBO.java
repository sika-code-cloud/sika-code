package com.dq.easy.cloud.module.common.generator.code.java.pojo.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.slf4j.LoggerFactory;

import com.dq.easy.cloud.module.basic.constant.DqBaseConstant.DqFileSuffix;
import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqIgnoreField.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqSourceCodeRelativePath;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.bo.DqGenerateBO;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.mysql.DqMysqlDataSources;
import com.dq.easy.cloud.module.common.generator.code.base.utils.DqCodeGenerateUtils;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaClassContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFileDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationParamDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;
import com.dq.easy.cloud.module.common.log.utils.DqLogUtils;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

public abstract class DqGenerateJavaBaseBO extends DqGenerateBO {
	protected DqGenerateJavaBaseDTO generateJavaBaseDTO;
	private DqDatabaseAbstactConfig dataBaseConfig;
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
		dqFileDesc.setFileName(buildClassName());
		dqFileDesc.setSourceCodeRelativePath(DqSourceCodeRelativePath.JAVA);
		dqFileDesc.setFileSuffix(DqFileSuffix.JAVA);
		dqFileDesc.setPackageRelativePath(buildPackageRelativePath());
		DqLogUtils.info("dqFileDesc", dqFileDesc, LoggerFactory.getLogger(this.getClass()));
		super.setFileDesc(dqFileDesc);
	}
	protected abstract String buildClassName ();
	
	/**
	 * 
	 * <p>
	 * 根据基础包名，模块名称，子模块名称获取完整包路径（相对路径）
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 下午4:29:52
	 */
	protected String buildPackageRelativePath() {
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		String basePackagePath = DqCodeGenerateUtils.changePackageNameToPath(generateJavaBaseDTO.getBasePackageName());
		String subModulePackagePath = DqCodeGenerateUtils
				.changePackageNameToPath(generateJavaBaseDTO.getSubModulePackageName());
		sb.append(basePackagePath).append(DqSymbol.BACK_SLASH);
		sb.append(generateJavaBaseDTO.getModuleName()).append(DqSymbol.BACK_SLASH);
		sb.append(subModulePackagePath).append(DqSymbol.BACK_SLASH);
		return sb.toString();
	}

	/**
	 * 
	 * <p>
	 * 根据基础包名，模块名称，子模块名称获取完整包名
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 * 
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 下午4:29:52
	 */
	protected String getFullPackageName() {
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		sb.append(generateJavaBaseDTO.getBasePackageName()).append(DqSymbol.STOP)
				.append(generateJavaBaseDTO.getModuleName()).append(DqSymbol.STOP)
				.append(generateJavaBaseDTO.getSubModulePackageName());
		return sb.toString();
	}

	@Override
	public void generateCode() throws Exception {
		String className = buildClassName();
		DqJavaClassContentDesc javaClassContentDesc = new DqJavaClassContentDesc(generateRule);
		// 设置包名
		javaClassContentDesc.setPackageName(getFullPackageName());
		// 设置注释
		javaClassContentDesc.setComment(generateJavaBaseDTO.getClassComment());

		// 设置类注解列表---begin
		List<DqJavaAnnotationDesc> annotations = new ArrayList<>();

		DqJavaAnnotationDesc tableAnnotationDesc = new DqJavaAnnotationDesc();
		tableAnnotationDesc.setName("@" + Table.class.getSimpleName());
		tableAnnotationDesc.setSimpleClassType(Table.class.getSimpleName());
		tableAnnotationDesc.setFullClassType(Table.class.getName());
		// 设置类注解参数---begin
		List<DqJavaAnnotationParamDesc> tableAnnotationParamDescs = new ArrayList<>();
		DqJavaAnnotationParamDesc tableAnnotationParamDesc = new DqJavaAnnotationParamDesc();
		tableAnnotationParamDesc.setName("name");
		tableAnnotationParamDesc.setValue(dataBaseConfig.getTableName());

		tableAnnotationParamDescs.add(tableAnnotationParamDesc);
		tableAnnotationDesc.setParams(tableAnnotationParamDescs);
		// 设置类注解参数---end
		annotations.add(tableAnnotationDesc);

		DqJavaAnnotationDesc entityAnnotationDesc = new DqJavaAnnotationDesc();
		entityAnnotationDesc.setName("@" + Entity.class.getSimpleName());
		entityAnnotationDesc.setSimpleClassType(Entity.class.getSimpleName());
		entityAnnotationDesc.setFullClassType(Entity.class.getName());

		annotations.add(entityAnnotationDesc);

		javaClassContentDesc.setAnnotations(annotations);
		// 设置类注解列表---end

		// 设置类的modifier列表---begin
		List<DqJavaModifierDesc> modifiers = new ArrayList<>();
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.CLASS));
		javaClassContentDesc.setModifiers(modifiers);
		// 设置类的modifier列表---end

		// 设置类名称
		javaClassContentDesc.setName(className);
		javaClassContentDesc.setSimpleClassType(className);
		// 设置继承父类---begin
		DqJavaClassContentDesc extendsParentClass = new DqJavaClassContentDesc();
		extendsParentClass.setName(DqBaseBO.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseBO.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseBO.class.getName());
		javaClassContentDesc.setExtendsParentClass(extendsParentClass);
		// 设置继承父类---end
		// 生成文件
		DqDatabaseDataSources databaseDataSources = new DqMysqlDataSources(dataBaseConfig,
				dataBaseConfig.getTableName());
		javaClassContentDesc.buildDescByDatabaseSources(databaseDataSources);
		javaClassContentDesc.addImportFullClassType();
		
		setFileContentDesc(javaClassContentDesc);
//			new DqGenerateBO(getFileDesc(), javaClassContentDesc, getTemplateDesc()).generateCode();
		super.generateCode();
	}

}
