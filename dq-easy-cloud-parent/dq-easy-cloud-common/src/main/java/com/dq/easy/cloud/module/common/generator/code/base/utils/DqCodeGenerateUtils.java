package com.dq.easy.cloud.module.common.generator.code.base.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.slf4j.LoggerFactory;

import com.dq.easy.cloud.module.basic.constant.DqBaseConstant.DqFileSuffix;
import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileDesc;
import com.dq.easy.cloud.module.common.generator.code.base.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqIgnoreField.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqSourceCodeRelativePath;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.bo.DqGenerateBO;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.mysql.DqMysqlDataSources;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaClassContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFileDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationParamDesc;
import com.dq.easy.cloud.module.common.generator.code.java.rule.DqGenerateJavaClassRule;
import com.dq.easy.cloud.module.common.log.utils.DqLogUtils;
import com.dq.easy.cloud.module.common.map.utils.DqMapUtils;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * 描述：代码生成器 Created by Ay on 2017/5/1.
 */
public class DqCodeGenerateUtils {


	/**
	 * 
	 * <p>
	 * 将包名转换为路径字符串
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param packegeName
	 * @return
	 * @author daiqi 创建时间 2018年3月22日 下午7:32:59
	 */
	public static String changePackageNameToPath(String packegeName) {
		String[] packageNameArr = packegeName.split("\\.");
		StringBuilder packagePathBuild = DqStringUtils.newStringBuilderDefault();
		for (int i = 0 ; i < packageNameArr.length; ++i) {
			String tempModelBasePackageName = packageNameArr[i];
			packagePathBuild.append(tempModelBasePackageName);
			if (i < packageNameArr.length - 1) {
				packagePathBuild.append(DqSymbol.BACK_SLASH);
			}
		}
		return packagePathBuild.toString();
	}

	/**
	 * 
	 * <p>
	 * 获取数据库完整url
	 * </p>
	 *
	 * @param baseUrl : String : 数据库基础url
	 * @param port ： String : 数据库端口号
	 * @param databaseName : String : 数据库名称
	 * @return 数据库完整url
	 * @author daiqi
	 * 创建时间    2018年3月22日 下午2:14:51
	 */
	public static String getDatabaseFullUrl(String baseUrl, String port, String databaseName) {
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		return sb.append(baseUrl).append(DqSymbol.SPLIT_COLON).append(port).append(DqSymbol.FORWARD_SLASH).append(databaseName).toString();
	}
	
	/**
	 * 
	 * <p>
	 * 根据类名简称获取完整的类名信息
	 * </p>
	 *
	 * @param simpleClassName : String : 类名简称
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月23日 下午2:05:23
	 */
	public static String getCompleteClassName(String simpleClassName) {
		return DqMapUtils.getString(DqCodeGenerateConfig.getClassNameSimpleMapping(), simpleClassName);
	}
	
	public static void generateDO(DqDatabaseAbstactConfig dataBaseConfig,String tableName) {
		String projectName = "dq-easy-cloud-common";
		String basePackageName = "com.dq.easy";
		String moduleName = "goods";
		String subModulePackageName = "pojo.entity" ;
		String classBodyName = "Goods";
		String classComment = "商品";
		String className = DqStringUtils.capitalize(classBodyName) + DqClassNameEndWith.POJO_DO;
		
		String sourceCodeRelativePath = DqSourceCodeRelativePath.JAVA;
		String fileDicPath = getFullPackagePath(basePackageName, moduleName, subModulePackageName);
		DqFileDesc fileDesc = new DqJavaFileDesc(projectName, sourceCodeRelativePath, fileDicPath, className, DqFileSuffix.JAVA);
		
		DqGenerateJavaClassRule generateRule = new DqGenerateJavaClassRule(true, true, true, true);
		DqJavaClassContentDesc javaClassContentDesc = new DqJavaClassContentDesc(generateRule);
//		设置包名		
		javaClassContentDesc.setPackageName(getFullPackageName(basePackageName, moduleName, subModulePackageName));
//		设置注释
		javaClassContentDesc.setComment(classComment);
		
//		设置类注解列表---begin
		List<DqJavaAnnotationDesc> annotations = new ArrayList<>();
		
		DqJavaAnnotationDesc tableAnnotationDesc = new DqJavaAnnotationDesc();
		tableAnnotationDesc.setName("@" + Table.class.getSimpleName());
		tableAnnotationDesc.setSimpleClassType(Table.class.getSimpleName());
		tableAnnotationDesc.setFullClassType(Table.class.getName());
//		设置类注解参数---begin
		List<DqJavaAnnotationParamDesc> tableAnnotationParamDescs = new ArrayList<>();
		DqJavaAnnotationParamDesc tableAnnotationParamDesc = new DqJavaAnnotationParamDesc();
		tableAnnotationParamDesc.setName("name");
		tableAnnotationParamDesc.setValue(tableName);
		
		tableAnnotationParamDescs.add(tableAnnotationParamDesc);
		tableAnnotationDesc.setParams(tableAnnotationParamDescs);
//		设置类注解参数---end
		annotations.add(tableAnnotationDesc);
		
		DqJavaAnnotationDesc entityAnnotationDesc = new DqJavaAnnotationDesc();
		entityAnnotationDesc.setName("@"+Entity.class.getSimpleName());
		entityAnnotationDesc.setSimpleClassType(Entity.class.getSimpleName());
		entityAnnotationDesc.setFullClassType(Entity.class.getName());
		
		annotations.add(entityAnnotationDesc);
		
		javaClassContentDesc.setAnnotations(annotations);
//		设置类注解列表---end
		
//		设置类的modifier列表---begin
		List<DqJavaModifierDesc> modifiers = new ArrayList<>();
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.CLASS));
		javaClassContentDesc.setModifiers(modifiers);
//		设置类的modifier列表---end
		
//		设置类名称
		javaClassContentDesc.setName(className);
		javaClassContentDesc.setSimpleClassType(className);
//		设置继承父类---begin
		DqJavaClassContentDesc extendsParentClass = new DqJavaClassContentDesc();
		extendsParentClass.setName(DqBaseBO.class.getSimpleName());
		extendsParentClass.setSimpleClassType(DqBaseBO.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseBO.class.getName());
		javaClassContentDesc.setExtendsParentClass(extendsParentClass);
//		设置继承父类---end
		DqTemplateDesc templateDesc = new DqTemplateDesc(DqCodeGenerateConfig.CODE_TEMPLATE_BASE_PACKAGE_PATH, "test.ftl");
//		生成文件
		try {
			DqLogUtils.info("fileDesc", fileDesc, LoggerFactory.getLogger(DqCodeGenerateUtils.class));
			DqDatabaseDataSources databaseDataSources = new DqMysqlDataSources(dataBaseConfig, tableName);
			javaClassContentDesc.buildDescByDatabaseSources(databaseDataSources);
			javaClassContentDesc.addImportFullClassType();
			new DqGenerateBO(fileDesc, javaClassContentDesc, templateDesc).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	 * @param basePackage : String : 基础包名
	 * @param moduleName : String : 模块名称
	 * @param subModulPackage : String : 子模块包名
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月26日 下午4:29:52
	 */
	private static String getFullPackagePath(String basePackage, String moduleName, String subModulPackage) {
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		String basePackagePath = DqCodeGenerateUtils.changePackageNameToPath(basePackage);
		String subModulePackagePath = DqCodeGenerateUtils.changePackageNameToPath(subModulPackage);
		sb.append(basePackagePath).append(DqSymbol.BACK_SLASH);
		sb.append(moduleName).append(DqSymbol.BACK_SLASH);
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
	 * @param basePackage : String : 基础包名
	 * @param moduleName : String : 模块名称
	 * @param subModulPackage : String : 子模块包名
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月26日 下午4:29:52
	 */
	private static String getFullPackageName(String basePackage, String moduleName, String subModulPackage) {
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		sb.append(basePackage).append(DqSymbol.STOP).append(moduleName).append(DqSymbol.STOP).append(subModulPackage);
		return sb.toString();
	}
}
