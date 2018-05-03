package com.easy.cloud.core.generator.code.bo.javainf;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import com.easy.cloud.core.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcMethodTypeEnum;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcSubModuleDefaultPackageName;
import com.easy.cloud.core.generator.code.java.desc.EcJavaClassContentDesc;
import com.easy.cloud.core.generator.code.java.desc.EcJavaContentDesc;
import com.easy.cloud.core.generator.code.java.desc.EcJavaFieldContentDesc;
import com.easy.cloud.core.generator.code.java.desc.EcJavaGenericityContentDesc;
import com.easy.cloud.core.generator.code.java.desc.EcJavaMethodContentDesc;
import com.easy.cloud.core.generator.code.java.desc.anno.EcJavaAnnotationDesc;
import com.easy.cloud.core.generator.code.java.pojo.bo.EcGenerateJavaInfBO;
import com.easy.cloud.core.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;

public class EcGenerateJavaJpaDAOBO extends EcGenerateJavaInfBO {
	public EcGenerateJavaJpaDAOBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected String getClassNameEndWith() {
		return EcClassNameEndWith.DAO_INF;
	}

	@Override
	protected String getClassCommentEndWith() {
		return EcClassCommentEndWith.DAO_INF;
	}

	@Override
	protected void buildExtendsParentClass() {
		// 构建集成的父类
		EcJavaClassContentDesc extendsParentClass = new EcJavaClassContentDesc();
//		extendsParentClass.setSimpleClassType(JpaRepository.class.getSimpleName());
//		extendsParentClass.setName(JpaRepository.class.getSimpleName());
//		extendsParentClass.setFullClassType(JpaRepository.class.getName());
		// 构建继承父类的泛型数据
		EcJavaGenericityContentDesc entity = new EcJavaGenericityContentDesc();
//		entity.setSimpleClassType(EcBaseEntity.class.getSimpleName());
//		entity.setName(EcBaseEntity.class.getSimpleName());
//		entity.setFullClassType(EcBaseEntity.class.getName());
		extendsParentClass.addGenericity(entity);
		// 构建父类的第二个泛型对象
		EcJavaGenericityContentDesc stringGen = new EcJavaGenericityContentDesc();
		stringGen.setSimpleClassType(String.class.getSimpleName());
		stringGen.setName(String.class.getSimpleName());
		extendsParentClass.addGenericity(stringGen);
		// 将父类add到javaClassContentDesc
		javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	/** 构建自定义的方法列表 */
	@Override
	protected void buildMethods() {
		// PO对象名称后缀
		String nameEndwith = EcClassNameEndWith.POJO_PO;
		// PO对象的子模块包名
		String subModulePackageName = EcSubModuleDefaultPackageName.POJO_PO;
		// 获取自定义的描述对象
		EcJavaContentDesc doClass = super.getCustomJavaContentByEndwith(nameEndwith, subModulePackageName,
				EcJavaContentDesc.class);
		// 创建save抽象方法
		EcJavaMethodContentDesc methodSave = new EcJavaMethodContentDesc();
		// 设置返回类类型简称
		methodSave.setReturnSimpleClassType(Integer.class.getSimpleName());
		// 设置完整类类型
		methodSave.setReturnFullClassType(Integer.class.getName());
		// 设置方法名称
		methodSave.setName("save");
		// 设置方法的形参
		EcJavaFieldContentDesc methodSaveArg = new EcJavaFieldContentDesc();
		// 设置形参名称
		methodSaveArg.setName(doClass.getName());
		// 设置形参类类型简称
		methodSaveArg.setSimpleClassType(doClass.getSimpleClassType());
		// 设置形参完整类类型
		methodSaveArg.setFullClassType(doClass.getFullClassType());
		// 将该形参add到methodSave中
		methodSave.addArg(methodSaveArg);
		// 设置方法类型（表示为抽象方法）
		methodSave.setType(EcMethodTypeEnum.ABSTRACT.getType());
		// 将该方法add到javaClassContentDesc对象中
		super.javaClassContentDesc.addMethod(methodSave);

		// update 与保存方法步骤一样
		EcJavaMethodContentDesc methodUpdate = new EcJavaMethodContentDesc();
		methodUpdate.setReturnSimpleClassType(Integer.class.getSimpleName());
		methodUpdate.setReturnFullClassType(Integer.class.getName());
		methodUpdate.setName("update");
		EcJavaFieldContentDesc methodUpdateArg = new EcJavaFieldContentDesc();
		methodUpdateArg.setName(doClass.getName());
		methodUpdateArg.setSimpleClassType(doClass.getSimpleClassType());
		methodUpdateArg.setFullClassType(doClass.getFullClassType());
		methodUpdate.addArg(methodSaveArg);
		methodUpdate.setType(EcMethodTypeEnum.ABSTRACT.getType());
		super.javaClassContentDesc.addMethod(methodUpdate);

		// findById 与保存方法步骤一样
		EcJavaMethodContentDesc methodFindById = new EcJavaMethodContentDesc();
		methodFindById.setReturnSimpleClassType(doClass.getSimpleClassType());
		methodFindById.setReturnFullClassType(doClass.getFullClassType());
		methodFindById.setName("findById");
		EcJavaFieldContentDesc methodFindByIdArg = new EcJavaFieldContentDesc();
		methodFindByIdArg.setName("id");
		methodFindByIdArg.setSimpleClassType(Long.class.getSimpleName());
		methodFindById.addArg(methodFindByIdArg);
		methodFindById.setType(EcMethodTypeEnum.ABSTRACT.getType());
		super.javaClassContentDesc.addMethod(methodFindById);

		// listCount 与保存方法步骤一样
		EcJavaMethodContentDesc methodListCount = new EcJavaMethodContentDesc();
		methodListCount.setReturnSimpleClassType(Integer.class.getSimpleName());
		methodListCount.setName("listCount");
		EcJavaFieldContentDesc methodListCountArg = new EcJavaFieldContentDesc();
		// 形参注解
		EcJavaAnnotationDesc methodListCountArgAnnotation = new EcJavaAnnotationDesc();
		// 设置形参注解名称
		methodListCountArgAnnotation.setName(Param.class.getSimpleName());
		// 设置形参注解类类型简称
		methodListCountArgAnnotation.setSimpleClassType(Param.class.getSimpleName());
		// 设置形参注解的完整类类型
		methodListCountArgAnnotation.setFullClassType(Param.class.getName());
		// 设置形参注解的参数值
		methodListCountArgAnnotation.addParam("value", "maps");
		// 将形参注解add到对应的形参对象中
		methodListCountArg.addAnnotation(methodListCountArgAnnotation);
		methodListCountArg.setName("paramsMap");
		methodListCountArg.setSimpleClassType("Map<String, Object>");
		methodListCountArg.setFullClassType(Map.class.getName());
		methodListCount.addArg(methodListCountArg);
		methodListCount.setType(EcMethodTypeEnum.ABSTRACT.getType());
		super.javaClassContentDesc.addMethod(methodListCount);

		// listPage 与保存方法步骤一样
		EcJavaMethodContentDesc methodListPage = new EcJavaMethodContentDesc();
		methodListPage.setReturnSimpleClassType("List<" + doClass.getSimpleClassType() + ">");
		methodListPage.setReturnFullClassType(List.class.getName());
		methodListPage.setName("listPage");
		EcJavaFieldContentDesc methodListPageArg = new EcJavaFieldContentDesc();
		// 形参注解
		EcJavaAnnotationDesc methodListPageArgAnnotation = new EcJavaAnnotationDesc();
		methodListPageArgAnnotation.setName(Param.class.getSimpleName());
		methodListPageArgAnnotation.setSimpleClassType(Param.class.getSimpleName());
		methodListPageArgAnnotation.setFullClassType(Param.class.getName());
		methodListPageArgAnnotation.addParam("value", "maps");

		methodListPageArg.addAnnotation(methodListPageArgAnnotation);
		methodListPageArg.setName("paramsMap");
		methodListPageArg.setSimpleClassType("Map<String, Object>");
		methodListPageArg.setFullClassType(Map.class.getName());
		methodListPage.addArg(methodListPageArg);
		methodListPage.setType(EcMethodTypeEnum.ABSTRACT.getType());
		super.javaClassContentDesc.addMethod(methodListPage);
	}
}
