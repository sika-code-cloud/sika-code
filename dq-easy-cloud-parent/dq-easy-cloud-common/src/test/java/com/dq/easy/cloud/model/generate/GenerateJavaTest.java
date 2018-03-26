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
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqIgnoreField.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.bo.DqGeneratorBO;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.utils.DqCodeGenerateUtils;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaClassContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaImplInterfaceContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationParamDesc;
import com.dq.easy.cloud.module.common.generator.code.java.rule.DqGenerateJavaClassRule;

public class GenerateJavaTest {
	private DqDatabaseAbstactConfig databaseAbstactConfig;
	private String packageStartWith = "com.dq.easy.cloud";
	private String needSubstrClassNameStartWith = "tlcyg";
	@Before
	public void initData() {
		databaseAbstactConfig = new DqDataBaseMysqlConfig();
		databaseAbstactConfig.buildDatabaseBaseUrl("jdbc:mysql://rm-wz9632z95v9v65458o.mysql.rds.aliyuncs.com");
		databaseAbstactConfig.buildDatabasePort("3306");
		databaseAbstactConfig.buildDatabaseName("sea_share_db");
		databaseAbstactConfig.buildDatabaseUserName("seashare");
		databaseAbstactConfig.buildDatabasePassword("Seashare123");
	}
	
	private String moduleName = "goods";
	private String tableName = "tl_cyg_goods_info";
	private String fileComment = "商品";
	
	@Test
	public void testGenerateBO() {
		DqFileDesc fileDesc = new DqFileDesc();
		fileDesc.setFileDirectoryFullPath(DqCodeProject.PROJECT_ROOT_BASE_PATH_DEFAULT + "//src//main//java//com//dq//easy//user//entity//");
		
		fileDesc.setFileName("UserDO");
		fileDesc.setFileSuffix("java");
		
		DqGenerateJavaClassRule generateRule = new DqGenerateJavaClassRule(true, true, true, true);
		DqJavaClassContentDesc javaClassContentDesc = new DqJavaClassContentDesc(generateRule);
//		设置包名		
		javaClassContentDesc.setPackageName("com.dq.easy.user.entity");
//		设置导入的class名称
//		Set<String> importClassNames = new HashSet<>();
//		importClassNames.add(Service.class.getName());
//		importClassNames.add(DqBaseBO.class.getName());
//		importClassNames.add(Serializable.class.getName());
//		javaClassContentDesc.setImportClassNames(importClassNames);
//		设置注释
		javaClassContentDesc.setComment("测试");
		
//		设置类注解列表---begin
		List<DqJavaAnnotationDesc> annotations = new ArrayList<>();
		DqJavaAnnotationDesc annotationDesc1 = new DqJavaAnnotationDesc();
		annotationDesc1.setName("@Service");
		annotationDesc1.setSimpleClassType(Service.class.getSimpleName());
		annotationDesc1.setFullClassType(Service.class.getName());
//		设置类注解
		List<DqJavaAnnotationParamDesc> annotationParamDescs = new ArrayList<>();
		DqJavaAnnotationParamDesc annotationParamDesc1 = new DqJavaAnnotationParamDesc();
		annotationParamDesc1.setName("value");
		annotationParamDesc1.setValue("userService");
		
		annotationParamDescs.add(annotationParamDesc1);
		annotationDesc1.setParams(annotationParamDescs);
		
		annotations.add(annotationDesc1);
		javaClassContentDesc.setAnnotations(annotations);
//		设置类注解列表---end
		
//		设置类的modifier列表---begin
		List<DqJavaModifierDesc> modifiers = new ArrayList<>();
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		modifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.CLASS));
		javaClassContentDesc.setModifiers(modifiers);
//		设置类的modifier列表---end
		
//		设置类名称
		javaClassContentDesc.setName("UserDO");
		javaClassContentDesc.setSimpleClassType("UserDO");
//		设置继承父类---begin
		DqJavaClassContentDesc extendsParentClass = new DqJavaClassContentDesc();
		extendsParentClass.setName("DqBaseDO");
		extendsParentClass.setSimpleClassType(DqBaseBO.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseBO.class.getName());
		javaClassContentDesc.setExtendsParentClass(extendsParentClass);
//		设置继承父类---end
		
//		设置实现的接口--begin
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
//		设置实现的接口---end
		
//		设置类的属性--begin
		List<DqJavaFieldContentDesc> fields = new ArrayList<>();
		DqJavaFieldContentDesc field1 = new DqJavaFieldContentDesc();
		field1.setComment("用户名称");
//		设置注解描述信息
		List<DqJavaAnnotationDesc> fieldAnnotationDescs = new ArrayList<>();
		
//		设置注解描述信息1---begin
		DqJavaAnnotationDesc fieldAnnotationDesc1 = new DqJavaAnnotationDesc();
		fieldAnnotationDesc1.setName("@"+Column.class.getSimpleName());
		fieldAnnotationDesc1.setSimpleClassType(Column.class.getSimpleName());;
		fieldAnnotationDesc1.setFullClassType(Column.class.getName());
		
//		设置属性注解参数描述列表信息
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
//		设置属性注解描述1---end
		
//		设置注解描述信息2---begin
		DqJavaAnnotationDesc fieldAnnotationDesc2 = new DqJavaAnnotationDesc();
		fieldAnnotationDesc2.setName("@"+JSONField.class.getSimpleName());
		fieldAnnotationDesc2.setSimpleClassType(JSONField.class.getSimpleName());;
		fieldAnnotationDesc2.setFullClassType(JSONField.class.getName());
		
//		设置属性注解参数描述列表信息
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
		
//		设置属性注解描述2---end
		
		field1.setAnnotations(fieldAnnotationDescs);
		
		List<DqJavaModifierDesc> fieldsModifiers = new ArrayList<>();
		fieldsModifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.PRIVATE));
		field1.setModifiers(fieldsModifiers);
		
		field1.setSimpleClassType(String.class.getSimpleName());
		field1.setName("name");
		field1.setFullClassType(String.class.getName());

//		根据属性生成get方法---begin
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
//		根据属性生成get方法---end
		
//		根据属性生成set方法---begin
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
//		根据属性生成set方法---end
		
//		根据属性生成build方法---begin
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
//		根据属性生成build方法---end
		fields.add(field1);
		
		javaClassContentDesc.setFields(fields);
//		设置属性---end
		
//		设置方法---begin
		List<DqJavaMethodContentDesc> javaMethodContentDescs = new ArrayList<>();
		javaMethodContentDescs.add(methodContentDesc1);
		javaMethodContentDescs.add(methodContentDesc2);
		javaMethodContentDescs.add(methodContentDesc3);
		javaClassContentDesc.setMethods(javaMethodContentDescs);
//		设置方法---end
		
		DqTemplateDesc templateDesc = new DqTemplateDesc(DqCodeGenerateConfig.CODE_TEMPLATE_BASE_PACKAGE_PATH, "test.ftl");
//		生成文件
		try {
			javaClassContentDesc.addImportFullClassType();
			new DqGeneratorBO(fileDesc, javaClassContentDesc, templateDesc).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void generateJavaByDataBase() {
		DqCodeGenerateUtils.generateDO(databaseAbstactConfig, tableName);
	}
	
}
