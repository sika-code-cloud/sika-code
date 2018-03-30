package com.dq.easy.cloud.model.generate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.ImageInputStream;
import javax.persistence.Column;

import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.annotation.JSONField;
import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileDesc;
import com.dq.easy.cloud.module.common.generator.code.base.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.mysql.DqDataBaseMysqlConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqCodeProject;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.mysql.DqMysqlDataSources;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqSubModuleDefaultPackageName;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaClassContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaImplInterfaceContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationParamDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass.DqGenerateJavaBOBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass.DqGenerateJavaControllerBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass.DqGenerateJavaDAOImplBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass.DqGenerateJavaDOBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass.DqGenerateJavaDTOBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass.DqGenerateJavaLogicImplBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass.DqGenerateJavaQueryBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass.DqGenerateJavaServiceImplBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaclass.DqGenerateJavaVOBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javaenum.DqGenerateJavaErrorCodeBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javainf.DqGenerateJavaDAOBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javainf.DqGenerateJavaLogicBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.example.javainf.DqGenerateJavaServiceBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;
import com.dq.easy.cloud.module.common.generator.code.java.rule.DqGenerateJavaClassRule;

/** 生成java代码测试类 */
public class GenerateJavaTest {
	// 数据库配置信息
	private DqDatabaseAbstactConfig databaseAbstactConfig;
	// 模版描述对象
	private DqTemplateDesc templateDesc = new DqTemplateDesc(DqCodeGenerateConfig.CODE_TEMPLATE_BASE_PACKAGE_PATH,
			"JAVA.ftl");;
	// pojo类所在的项目名称
	private String projectNamePojo = "dq-easy-cloud-common";
	// dao类所在的项目名称
	private String projectNameDao = "dq-easy-cloud-common";
	// service类所在的项目名称
	private String projectNameService = "dq-easy-cloud-common";
	// controller类所在的项目名称
	private String projectNameController = "dq-easy-cloud-common";
	// 基础包名称
	private String basePackageName = "com.dq.easy";

	// 表名
	private String tableName = "p_user";
	// 模块包名
	private String moduleName = "user";
	// 类主体名称
	private String classBodyName = "User";
	// 类的注释
	private String classComment = "用户";

	@Before
	public void initData() {
		databaseAbstactConfig = new DqDataBaseMysqlConfig();
		databaseAbstactConfig.buildDatabaseBaseUrl("jdbc:mysql://rm-wz9632z95v9v65458o.mysql.rds.aliyuncs.com");
		databaseAbstactConfig.buildDatabasePort("3306");
		databaseAbstactConfig.buildDatabaseName("dq_easy_cloud");
		databaseAbstactConfig.buildDatabaseUserName("dq_easy_cloud");
		databaseAbstactConfig.buildDatabasePassword("dq_easy_cloud123");
		databaseAbstactConfig.buildTableName(tableName);
	}

	/** 代码生成组建测试 */
	@Test
	public void testGenerateJavaCode() {
		// 生成数据持久化类
		generateJavaDOByDataBase();
		// 生成数据传输类
		generateJavaDTOByDataBase();
		// 生成业务逻辑类
		generateJavaBOByDataBase();
		// 生成查询类
		generateJavaQueryByDataBase();
		// 生成视图类
		generateJavaVOByDataBase();
		// 生成DAO类
		generateJavaDAOByDataBase();
		// 生成DAO实现类
		generateJavaDAOImplByDataBase();
		// 生成Service类
		generateJavaServiceByDataBase();
		// 生成Service实现类
		generateJavaServiceImplByDataBase();
		// 生成Logic接口类
		generateJavaLogicByDataBase();
		// 生成Logic实现类
		generateJavaLogicImplByDataBase();
		// 生成Controller类
		generateJavaControllerByDataBase();
		// 生成错误代码类
		generateJavaErrorCodeByDataBase();
	}

	@Test
	public void generateJavaDOByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.POJO_DO;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, true);
		generateJavaBaseDTO.setCoverSwith(true);
		try {
			new DqGenerateJavaDOBO(generateJavaBaseDTO, templateDesc, generateRule)
					.buildDatabaseDataSources(new DqMysqlDataSources(databaseAbstactConfig)).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaDTOByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.POJO_DTO;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, false, false, false);
		try {
			new DqGenerateJavaDTOBO(generateJavaBaseDTO, templateDesc, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaBOByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.POJO_BO;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		generateJavaBaseDTO.setCoverSwith(true);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, false, false, true);
		try {
			new DqGenerateJavaBOBO(generateJavaBaseDTO, templateDesc, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaQueryByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.POJO_QUERY;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, false);
		try {
			new DqGenerateJavaQueryBO(generateJavaBaseDTO, templateDesc, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaVOByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.POJO_VO;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		generateJavaBaseDTO.setCoverSwith(true);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, false);
		try {
			DqGenerateJavaVOBO generateJavaVOBO = new DqGenerateJavaVOBO(generateJavaBaseDTO, templateDesc, generateRule);
			generateJavaVOBO.buildDatabaseDataSources(new DqMysqlDataSources(databaseAbstactConfig)).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaDAOByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.DAO_INF;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameDao, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, false);
		try {
			new DqGenerateJavaDAOBO(generateJavaBaseDTO, templateDesc, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaDAOImplByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.DAO_IMPL;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameDao, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, false);
		try {
			new DqGenerateJavaDAOImplBO(generateJavaBaseDTO, templateDesc, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaServiceByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.SERVICE_INF;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameService, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, false);
		try {
			new DqGenerateJavaServiceBO(generateJavaBaseDTO, templateDesc, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaServiceImplByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.SERVICE_IMPL;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameService, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, false, false, false);
		try {
			new DqGenerateJavaServiceImplBO(generateJavaBaseDTO, templateDesc, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaLogicByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.LOGIC_INF;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameService, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, false, false, false);
		try {
			new DqGenerateJavaLogicBO(generateJavaBaseDTO, templateDesc, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaLogicImplByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.LOGIC_IMPL;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameService, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, false, false, false);
		try {
			new DqGenerateJavaLogicImplBO(generateJavaBaseDTO, templateDesc, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaControllerByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.CONTROLLER;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameController, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, false, false, false);
		try {
			new DqGenerateJavaControllerBO(generateJavaBaseDTO, templateDesc, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaErrorCodeByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.ERROR_CODE;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameController, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		generateJavaBaseDTO.setCoverSwith(true);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, false);
		try {
			new DqGenerateJavaErrorCodeBO(generateJavaBaseDTO, templateDesc, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGenerateBO() {
		DqFileDesc fileDesc = new DqFileDesc();
		fileDesc.setFileDirectoryFullPath(
				DqCodeProject.PROJECT_ROOT_BASE_PATH_DEFAULT + "//src//main//java//com//dq//easy//user//entity//");

		fileDesc.setFileName("UserDO");
		fileDesc.setFileSuffix("java");

		DqGenerateJavaClassRule generateRule = new DqGenerateJavaClassRule(true, true, true, true);
		DqJavaClassContentDesc javaClassContentDesc = new DqJavaClassContentDesc(generateRule);
		// 设置包名
		javaClassContentDesc.setPackageName("com.dq.easy.user.entity");
		// 设置导入的class名称
		// Set<String> importClassNames = new HashSet<>();
		// importClassNames.add(Service.class.getName());
		// importClassNames.add(DqBaseBO.class.getName());
		// importClassNames.add(Serializable.class.getName());
		// javaClassContentDesc.setImportClassNames(importClassNames);
		// 设置注释
		javaClassContentDesc.setComment("测试");

		// 设置类注解列表---begin
		List<DqJavaAnnotationDesc> annotations = new ArrayList<>();
		DqJavaAnnotationDesc annotationDesc1 = new DqJavaAnnotationDesc();
		annotationDesc1.setName("@Service");
		annotationDesc1.setSimpleClassType(Service.class.getSimpleName());
		annotationDesc1.setFullClassType(Service.class.getName());
		// 设置类注解
		List<DqJavaAnnotationParamDesc> annotationParamDescs = new ArrayList<>();
		DqJavaAnnotationParamDesc annotationParamDesc1 = new DqJavaAnnotationParamDesc();
		annotationParamDesc1.setName("value");
		annotationParamDesc1.setValue("userService");

		annotationParamDescs.add(annotationParamDesc1);
		annotationDesc1.setParams(annotationParamDescs);

		annotations.add(annotationDesc1);
		javaClassContentDesc.setAnnotations(annotations);
		// 设置类注解列表---end

		// 设置类的modifier列表---begin
		List<DqJavaModifierDesc> modifiers = new ArrayList<>();
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.CLASS));
		javaClassContentDesc.setModifiers(modifiers);
		// 设置类的modifier列表---end

		// 设置类名称
		javaClassContentDesc.setName("UserDO");
		javaClassContentDesc.setSimpleClassType("UserDO");
		// 设置继承父类---begin
		DqJavaClassContentDesc extendsParentClass = new DqJavaClassContentDesc();
		extendsParentClass.setName("DqBaseDO");
		extendsParentClass.setSimpleClassType(DqBaseBO.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseBO.class.getName());
		javaClassContentDesc.setExtendsParentClass(extendsParentClass);
		// 设置继承父类---end

		// 设置实现的接口--begin
		List<DqJavaImplInterfaceContentDesc> implementsInterfaces = new ArrayList<>();

		DqJavaImplInterfaceContentDesc implementsClassName1 = new DqJavaImplInterfaceContentDesc();
		implementsClassName1.setName(Serializable.class.getSimpleName());
		implementsClassName1.setSimpleClassType(Serializable.class.getSimpleName());
		implementsClassName1.setFullClassType(Serializable.class.getName());
		implementsInterfaces.add(implementsClassName1);

		DqJavaImplInterfaceContentDesc implementsClassName3 = new DqJavaImplInterfaceContentDesc();
		implementsClassName3.setName(ImageInputStream.class.getSimpleName());
		implementsClassName3.setSimpleClassType(ImageInputStream.class.getSimpleName());
		implementsClassName3.setFullClassType(ImageInputStream.class.getName());
		implementsInterfaces.add(implementsClassName3);
		javaClassContentDesc.setImplementsInterfaces(implementsInterfaces);
		// 设置实现的接口---end

		// 设置类的属性--begin
		List<DqJavaFieldContentDesc> fields = new ArrayList<>();
		DqJavaFieldContentDesc field1 = new DqJavaFieldContentDesc();
		field1.setComment("用户名称");
		// 设置注解描述信息
		List<DqJavaAnnotationDesc> fieldAnnotationDescs = new ArrayList<>();

		// 设置注解描述信息1---begin
		DqJavaAnnotationDesc fieldAnnotationDesc1 = new DqJavaAnnotationDesc();
		fieldAnnotationDesc1.setName("@" + Column.class.getSimpleName());
		fieldAnnotationDesc1.setSimpleClassType(Column.class.getSimpleName());
		;
		fieldAnnotationDesc1.setFullClassType(Column.class.getName());

		// 设置属性注解参数描述列表信息
		List<DqJavaAnnotationParamDesc> fieldAnnotationParamDescs = new ArrayList<>();
		DqJavaAnnotationParamDesc fieldAnnotationParamDesc1 = new DqJavaAnnotationParamDesc();
		fieldAnnotationParamDesc1.setName("name");
		fieldAnnotationParamDesc1.setValue("name");
		fieldAnnotationParamDescs.add(fieldAnnotationParamDesc1);

		DqJavaAnnotationParamDesc fieldAnnotationParamDesc2 = new DqJavaAnnotationParamDesc();
		fieldAnnotationParamDesc2.setName("columnDefinition");
		fieldAnnotationParamDesc2.setValue("VARCHAR");
		fieldAnnotationParamDescs.add(fieldAnnotationParamDesc2);

		fieldAnnotationDesc1.setParams(fieldAnnotationParamDescs);
		fieldAnnotationDescs.add(fieldAnnotationDesc1);
		// 设置属性注解描述1---end

		// 设置注解描述信息2---begin
		DqJavaAnnotationDesc fieldAnnotationDesc2 = new DqJavaAnnotationDesc();
		fieldAnnotationDesc2.setName("@" + JSONField.class.getSimpleName());
		fieldAnnotationDesc2.setSimpleClassType(JSONField.class.getSimpleName());
		;
		fieldAnnotationDesc2.setFullClassType(JSONField.class.getName());

		// 设置属性注解参数描述列表信息
		List<DqJavaAnnotationParamDesc> fieldAnnotationParamDescs2 = new ArrayList<>();
		DqJavaAnnotationParamDesc fieldAnnotationParamDesc21 = new DqJavaAnnotationParamDesc();
		fieldAnnotationParamDesc21.setName("deserialize");
		fieldAnnotationParamDesc21.setValue(true);
		fieldAnnotationParamDescs2.add(fieldAnnotationParamDesc21);

		DqJavaAnnotationParamDesc fieldAnnotationParamDesc22 = new DqJavaAnnotationParamDesc();
		fieldAnnotationParamDesc22.setName("name");
		fieldAnnotationParamDesc22.setValue("name");
		fieldAnnotationParamDescs2.add(fieldAnnotationParamDesc22);

		fieldAnnotationDesc2.setParams(fieldAnnotationParamDescs2);
		fieldAnnotationDescs.add(fieldAnnotationDesc2);

		// 设置属性注解描述2---end

		field1.setAnnotations(fieldAnnotationDescs);

		List<DqJavaModifierDesc> fieldsModifiers = new ArrayList<>();
		fieldsModifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.PRIVATE));
		field1.setModifiers(fieldsModifiers);

		field1.setSimpleClassType(String.class.getSimpleName());
		field1.setName("name");
		field1.setFullClassType(String.class.getName());

		// 根据属性生成get方法---begin
		DqJavaMethodContentDesc methodContentDesc1 = new DqJavaMethodContentDesc();
		methodContentDesc1.setComment("获取名称");
		List<DqJavaModifierDesc> methodModifierDescs = new ArrayList<>();
		methodModifierDescs.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		methodContentDesc1.setModifiers(methodModifierDescs);

		methodContentDesc1.setSimpleClassType(field1.getSimpleClassType());
		methodContentDesc1.setFullClassType(field1.getFullClassType());
		methodContentDesc1.setName("name");
		methodContentDesc1.setType(1);
		methodContentDesc1.setReturnSimpleClassType(field1.getSimpleClassType());
		methodContentDesc1.setReturnFullClassType(field1.getFullClassType());
		// 根据属性生成get方法---end

		// 根据属性生成set方法---begin
		DqJavaMethodContentDesc methodContentDesc2 = new DqJavaMethodContentDesc();
		methodContentDesc2.setComment("获取名称");
		List<DqJavaModifierDesc> methodModifierDescs2 = new ArrayList<>();
		methodModifierDescs2.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		methodContentDesc2.setModifiers(methodModifierDescs2);

		methodContentDesc2.setSimpleClassType(field1.getSimpleClassType());
		methodContentDesc2.setFullClassType(field1.getFullClassType());
		methodContentDesc2.setName("name");
		methodContentDesc2.setType(2);
		methodContentDesc2.setReturnSimpleClassType(void.class.getSimpleName());
		methodContentDesc2.setReturnFullClassType(void.class.getName());
		// 根据属性生成set方法---end

		// 根据属性生成build方法---begin
		DqJavaMethodContentDesc methodContentDesc3 = new DqJavaMethodContentDesc();
		methodContentDesc3.setComment("构建名称");
		List<DqJavaModifierDesc> methodModifierDescs31 = new ArrayList<>();
		methodModifierDescs31.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		methodContentDesc3.setModifiers(methodModifierDescs31);

		methodContentDesc3.setSimpleClassType(field1.getSimpleClassType());
		methodContentDesc3.setFullClassType(field1.getFullClassType());
		methodContentDesc3.setName("name");
		methodContentDesc3.setType(3);
		methodContentDesc3.setReturnSimpleClassType(javaClassContentDesc.getSimpleClassType());
		methodContentDesc3.setReturnFullClassType(javaClassContentDesc.getFullClassType());
		// 根据属性生成build方法---end
		fields.add(field1);

		javaClassContentDesc.setFields(fields);
		// 设置属性---end

		// 设置方法---begin
		List<DqJavaMethodContentDesc> javaMethodContentDescs = new ArrayList<>();
		javaMethodContentDescs.add(methodContentDesc1);
		javaMethodContentDescs.add(methodContentDesc2);
		javaMethodContentDescs.add(methodContentDesc3);
		javaClassContentDesc.setMethods(javaMethodContentDescs);
		// 设置方法---end

		DqTemplateDesc templateDesc = new DqTemplateDesc(DqCodeGenerateConfig.CODE_TEMPLATE_BASE_PACKAGE_PATH,
				"test.ftl");
		// 生成文件
		try {
			javaClassContentDesc.addImportFullClassType();
//			new DqGenerateBO(fileDesc, javaClassContentDesc, templateDesc).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
