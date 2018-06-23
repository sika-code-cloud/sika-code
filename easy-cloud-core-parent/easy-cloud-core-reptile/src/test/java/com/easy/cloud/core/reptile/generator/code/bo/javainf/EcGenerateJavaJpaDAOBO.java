package com.easy.cloud.core.reptile.generator.code.bo.javainf;

import com.easy.cloud.core.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcSubModuleDefaultPackageName;
import com.easy.cloud.core.generator.code.java.desc.EcJavaClassContentDesc;
import com.easy.cloud.core.generator.code.java.desc.EcJavaContentDesc;
import com.easy.cloud.core.generator.code.java.desc.EcJavaGenericityContentDesc;
import com.easy.cloud.core.generator.code.java.pojo.bo.EcGenerateJavaInfBO;
import com.easy.cloud.core.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;
import com.easy.cloud.core.jdbc.base.dao.EcBaseDAO;

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
		extendsParentClass.setSimpleClassType(EcBaseDAO.class.getSimpleName());
		extendsParentClass.setName(EcBaseDAO.class.getSimpleName());
		extendsParentClass.setFullClassType(EcBaseDAO.class.getName());
		// 构建继承父类的泛型数据
		EcJavaGenericityContentDesc entity = new EcJavaGenericityContentDesc();
		// PO对象名称后缀
		String nameEndwith = EcClassNameEndWith.POJO_DO;
		// PO对象的子模块包名
		String subModulePackageName = EcSubModuleDefaultPackageName.POJO_DO;
		// 获取自定义的描述对象
		EcJavaContentDesc doClass = super.getCustomJavaContentByEndwith(nameEndwith, subModulePackageName,
				EcJavaContentDesc.class);
		entity.setSimpleClassType(doClass.getSimpleClassType());
		entity.setName(doClass.getName());
		entity.setFullClassType(doClass.getFullClassType());
		extendsParentClass.addGenericity(entity);
		// 将父类add到javaClassContentDesc
		javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	/** 构建自定义的方法列表 */
	@Override
	protected void buildMethods() {
	}
}
