package com.easy.cloud.core.generate.bo.javainf;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcMethodTypeEnum;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcSubModuleDefaultPackageName;
import com.easy.cloud.core.common.generator.code.java.desc.EcJavaContentDesc;
import com.easy.cloud.core.common.generator.code.java.desc.EcJavaFieldContentDesc;
import com.easy.cloud.core.common.generator.code.java.desc.EcJavaMethodContentDesc;
import com.easy.cloud.core.common.generator.code.java.desc.anno.EcJavaAnnotationDesc;
import com.easy.cloud.core.common.generator.code.java.pojo.bo.EcGenerateJavaInfBO;
import com.easy.cloud.core.common.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;

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
		// DqJavaClassContentDesc extendsParentClass = new
		// DqJavaClassContentDesc();
		//
		// extendsParentClass.setSimpleClassType(JpaRepository.class.getSimpleName());
		// extendsParentClass.setName(JpaRepository.class.getSimpleName());
		// extendsParentClass.setFullClassType(JpaRepository.class.getName());
		//
		// DqJavaGenericityContentDesc entity = new
		// DqJavaGenericityContentDesc();
		// entity.setSimpleClassType(DqBaseEntity.class.getSimpleName());
		// entity.setName(DqBaseEntity.class.getSimpleName());
		// entity.setFullClassType(DqBaseEntity.class.getName());
		// extendsParentClass.addGenericity(entity);
		//
		// DqJavaGenericityContentDesc gene = new DqJavaGenericityContentDesc();
		// gene.setSimpleClassType(String.class.getSimpleName());
		// gene.setName(String.class.getSimpleName());
		// extendsParentClass.addGenericity(gene);

		// javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected void buildAnnotations() {

	}

	@Override
	protected void buildMethods() {

		String nameEndwith = EcClassNameEndWith.POJO_PO;
		String subModulePackageName = EcSubModuleDefaultPackageName.POJO_PO;
		EcJavaContentDesc doClass = super.getCustomJavaContentByEndwith(nameEndwith, subModulePackageName,
				EcJavaContentDesc.class);
		// save
		EcJavaMethodContentDesc methodSave = new EcJavaMethodContentDesc();
		methodSave.setReturnSimpleClassType(Integer.class.getSimpleName());
		methodSave.setReturnFullClassType(Integer.class.getName());
		methodSave.setName("save");
		EcJavaFieldContentDesc methodSaveArg = new EcJavaFieldContentDesc();
		methodSaveArg.setName(doClass.getName());
		methodSaveArg.setSimpleClassType(doClass.getSimpleClassType());
		methodSaveArg.setFullClassType(doClass.getFullClassType());
		methodSave.addArg(methodSaveArg);
		methodSave.setType(EcMethodTypeEnum.ABSTRACT.getType());
		super.javaClassContentDesc.addMethod(methodSave);

		// update
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

		// findById
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

		// listCount
		EcJavaMethodContentDesc methodListCount = new EcJavaMethodContentDesc();
		methodListCount.setReturnSimpleClassType(Integer.class.getSimpleName());
		methodListCount.setName("listCount");
		EcJavaFieldContentDesc methodListCountArg = new EcJavaFieldContentDesc();
		// 形参注解
		EcJavaAnnotationDesc methodListCountArgAnnotation = new EcJavaAnnotationDesc();
		methodListCountArgAnnotation.setName(Param.class.getSimpleName());
		methodListCountArgAnnotation.setSimpleClassType(Param.class.getSimpleName());
		methodListCountArgAnnotation.setFullClassType(Param.class.getName());
		methodListCountArgAnnotation.addParam("value", "maps");

		methodListCountArg.addAnnotation(methodListCountArgAnnotation);
		methodListCountArg.setName("paramsMap");
		methodListCountArg.setSimpleClassType("Map<String, Object>");
		methodListCountArg.setFullClassType(Map.class.getName());
		methodListCount.addArg(methodListCountArg);
		methodListCount.setType(EcMethodTypeEnum.ABSTRACT.getType());
		super.javaClassContentDesc.addMethod(methodListCount);

		// listPage
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
		// /** 查询类型---获取列表数量 */
		// LIST_COUNT(3, "listCount"),
		// /** 查询类型---获取分页数据 */
		// LIST_PAGE(4, "listPage"),
		// /** 查询类型---设置列明的sql */
		// SET_COLUMN_SQL(5, "setColumnSql"),
	}

}
