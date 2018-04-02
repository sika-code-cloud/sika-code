package com.dq.easy.cloud.model.generate.bo.javainf;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassCommentEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqMethodTypeEnum;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqSubModuleDefaultPackageName;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaFieldContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.DqJavaMethodContentDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.bo.DqGenerateJavaInfBO;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;

public class DqGenerateJavaJpaDAOBO extends DqGenerateJavaInfBO {

	public DqGenerateJavaJpaDAOBO(DqGenerateJavaBaseDTO generateJavaBaseDTO, DqGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected String getClassNameEndWith() {
		return DqClassNameEndWith.DAO_INF;
	}

	@Override
	protected String getClassCommentEndWith() {
		return DqClassCommentEndWith.DAO_INF;
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

		String nameEndwith = DqClassNameEndWith.POJO_DO;
		String subModulePackageName = DqSubModuleDefaultPackageName.POJO_DO;
		DqJavaContentDesc doClass = super.getCustomJavaContentByEndwith(nameEndwith, subModulePackageName,
				DqJavaContentDesc.class);
		// save
		DqJavaMethodContentDesc methodSave = new DqJavaMethodContentDesc();
		methodSave.setReturnSimpleClassType(Integer.class.getSimpleName());
		methodSave.setReturnFullClassType(Integer.class.getName());
		methodSave.setName("save");
		DqJavaFieldContentDesc methodSaveArg = new DqJavaFieldContentDesc();
		methodSaveArg.setName(doClass.getName());
		methodSaveArg.setSimpleClassType(doClass.getSimpleClassType());
		methodSaveArg.setFullClassType(doClass.getFullClassType());
		methodSave.addArg(methodSaveArg);
		methodSave.setType(DqMethodTypeEnum.ABSTRACT.getType());
		super.javaClassContentDesc.addMethod(methodSave);

		// update
		DqJavaMethodContentDesc methodUpdate = new DqJavaMethodContentDesc();
		methodUpdate.setReturnSimpleClassType(Integer.class.getSimpleName());
		methodUpdate.setReturnFullClassType(Integer.class.getName());
		methodUpdate.setName("update");
		DqJavaFieldContentDesc methodUpdateArg = new DqJavaFieldContentDesc();
		methodUpdateArg.setName(doClass.getName());
		methodUpdateArg.setSimpleClassType(doClass.getSimpleClassType());
		methodUpdateArg.setFullClassType(doClass.getFullClassType());
		methodUpdate.addArg(methodSaveArg);
		methodUpdate.setType(DqMethodTypeEnum.ABSTRACT.getType());
		super.javaClassContentDesc.addMethod(methodUpdate);

		// findById
		DqJavaMethodContentDesc methodFindById = new DqJavaMethodContentDesc();
		methodFindById.setReturnSimpleClassType(doClass.getSimpleClassType());
		methodFindById.setReturnFullClassType(doClass.getFullClassType());
		methodFindById.setName("findById");
		DqJavaFieldContentDesc methodFindByIdArg = new DqJavaFieldContentDesc();
		methodFindByIdArg.setName("id");
		methodFindByIdArg.setSimpleClassType(Long.class.getSimpleName());
		methodFindById.addArg(methodFindByIdArg);
		methodFindById.setType(DqMethodTypeEnum.ABSTRACT.getType());
		super.javaClassContentDesc.addMethod(methodFindById);

		// listCount
		DqJavaMethodContentDesc methodListCount = new DqJavaMethodContentDesc();
		methodListCount.setReturnSimpleClassType(Integer.class.getSimpleName());
		methodListCount.setName("listCount");
		DqJavaFieldContentDesc methodListCountArg = new DqJavaFieldContentDesc();
		// 形参注解
		DqJavaAnnotationDesc methodListCountArgAnnotation = new DqJavaAnnotationDesc();
		methodListCountArgAnnotation.setName(Param.class.getSimpleName());
		methodListCountArgAnnotation.setSimpleClassType(Param.class.getSimpleName());
		methodListCountArgAnnotation.setFullClassType(Param.class.getName());
		methodListCountArgAnnotation.addParam("value", "maps");

		methodListCountArg.addAnnotation(methodListCountArgAnnotation);
		methodListCountArg.setName("paramsMap");
		methodListCountArg.setSimpleClassType("Map<String, Object>");
		methodListCountArg.setFullClassType(Map.class.getName());
		methodListCount.addArg(methodListCountArg);
		methodListCount.setType(DqMethodTypeEnum.ABSTRACT.getType());
		super.javaClassContentDesc.addMethod(methodListCount);

		// listPage
		DqJavaMethodContentDesc methodListPage = new DqJavaMethodContentDesc();
		methodListPage.setReturnSimpleClassType("List<" + doClass.getSimpleClassType() + ">");
		methodListPage.setReturnFullClassType(List.class.getName());
		methodListPage.setName("listPage");
		DqJavaFieldContentDesc methodListPageArg = new DqJavaFieldContentDesc();
		// 形参注解
		DqJavaAnnotationDesc methodListPageArgAnnotation = new DqJavaAnnotationDesc();
		methodListPageArgAnnotation.setName(Param.class.getSimpleName());
		methodListPageArgAnnotation.setSimpleClassType(Param.class.getSimpleName());
		methodListPageArgAnnotation.setFullClassType(Param.class.getName());
		methodListPageArgAnnotation.addParam("value", "maps");

		methodListPageArg.addAnnotation(methodListPageArgAnnotation);
		methodListPageArg.setName("paramsMap");
		methodListPageArg.setSimpleClassType("Map<String, Object>");
		methodListPageArg.setFullClassType(Map.class.getName());
		methodListPage.addArg(methodListPageArg);
		methodListPage.setType(DqMethodTypeEnum.ABSTRACT.getType());
		super.javaClassContentDesc.addMethod(methodListPage);
		// /** 查询类型---获取列表数量 */
		// LIST_COUNT(3, "listCount"),
		// /** 查询类型---获取分页数据 */
		// LIST_PAGE(4, "listPage"),
		// /** 查询类型---设置列明的sql */
		// SET_COLUMN_SQL(5, "setColumnSql"),
	}

}
