package com.dq.easy.cloud.model.generate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.stream.ImageInputStream;

import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileDesc;
import com.dq.easy.cloud.module.common.generator.code.common.bo.DqGeneratorBO;
import com.dq.easy.cloud.module.common.generator.code.common.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.module.common.generator.code.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.config.database.mysql.DqDataBaseMysqlConfig;
import com.dq.easy.cloud.module.common.generator.code.constant.DqCodeGenerateConstant.DqCodeProject;
import com.dq.easy.cloud.module.common.generator.code.constant.DqCodeGenerateConstant.DqIgnoreField.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaClassContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqModifierDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqClassAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqClassAnnotationParamDesc;
import com.dq.easy.cloud.module.common.generator.code.java.rule.DqGeneratorJavaClassRule;

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
		fileDesc.setFileDirectoryFullPath(DqCodeProject.PROJECT_ROOT_BASE_PATH_DEFAULT + "//src//main//java//com//dq//easy//user//");
		
		fileDesc.setFileName("UserDO");
		fileDesc.setFileSuffix("java");
		
		DqGeneratorJavaClassRule generateRule = new DqGeneratorJavaClassRule(true, true, true);
		DqJavaClassContentDesc javaClassContentDesc = new DqJavaClassContentDesc(generateRule);
//		设置包名		
		javaClassContentDesc.setPackageName("com.dq.easy.user");
//		设置导入的class名称
//		Set<String> importClassNames = new HashSet<>();
//		importClassNames.add(Service.class.getName());
//		importClassNames.add(DqBaseBO.class.getName());
//		importClassNames.add(Serializable.class.getName());
//		javaClassContentDesc.setImportClassNames(importClassNames);
//		设置注释
		javaClassContentDesc.setComment("测试");
		
//		设置注解列表
		List<DqClassAnnotationDesc> annotations = new ArrayList<>();
		DqClassAnnotationDesc annotationDesc1 = new DqClassAnnotationDesc();
		annotationDesc1.setName("@Service");
		annotationDesc1.setSimpleClassType(Service.class.getSimpleName());
		annotationDesc1.setFullClassType(Service.class.getName());
//		设置类注解
		List<DqClassAnnotationParamDesc> annotationParamDescs = new ArrayList<>();
		DqClassAnnotationParamDesc annotationParamDesc1 = new DqClassAnnotationParamDesc();
		annotationParamDesc1.setName("value");
		annotationParamDesc1.setValue("userService");
		
		annotationParamDescs.add(annotationParamDesc1);
		annotationDesc1.setParams(annotationParamDescs);
		
		annotations.add(annotationDesc1);
		javaClassContentDesc.setAnnotations(annotations);
		
//		设置类的modifier列表
		List<DqModifierDesc> modifiers = new ArrayList<>();
		modifiers.add(new DqModifierDesc(DqModifierMappingEnum.PUBLIC));
		modifiers.add(new DqModifierDesc(DqModifierMappingEnum.CLASS));
		javaClassContentDesc.setModifiers(modifiers);
//		设置类名称
		javaClassContentDesc.setName("UserDO");
//		设置继承父类
		DqJavaClassContentDesc extendsParentClass = new DqJavaClassContentDesc();
		extendsParentClass.setName("DqBaseDO");
		extendsParentClass.setSimpleClassType(DqBaseBO.class.getSimpleName());
		extendsParentClass.setFullClassType(DqBaseBO.class.getName());
//		设置实现的接口
		List<DqJavaContentBaseDesc> implementsInterfaces = new ArrayList<>();
		
		DqJavaClassContentDesc implementsClassName1 = new DqJavaClassContentDesc();
		implementsClassName1.setName(Serializable.class.getSimpleName());
		implementsClassName1.setSimpleClassType(Serializable.class.getSimpleName());
		implementsClassName1.setFullClassType(Serializable.class.getName());
		implementsInterfaces.add(implementsClassName1);

		DqJavaClassContentDesc implementsClassName3 = new DqJavaClassContentDesc();
		implementsClassName3.setName(ImageInputStream.class.getSimpleName());
		implementsClassName3.setSimpleClassType(ImageInputStream.class.getSimpleName());
		implementsClassName3.setFullClassType(ImageInputStream.class.getName());
		implementsInterfaces.add(implementsClassName3);
		
		javaClassContentDesc.setImplementsInterfaces(implementsInterfaces);
		
		javaClassContentDesc.setExtendsParentClass(extendsParentClass);
		DqTemplateDesc templateDesc = new DqTemplateDesc(DqCodeGenerateConfig.CODE_TEMPLATE_BASE_PACKAGE_PATH, "test.ftl");
		
//		生成文件
		try {
			javaClassContentDesc.addImportClassName();
			new DqGeneratorBO(fileDesc, javaClassContentDesc, templateDesc).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
