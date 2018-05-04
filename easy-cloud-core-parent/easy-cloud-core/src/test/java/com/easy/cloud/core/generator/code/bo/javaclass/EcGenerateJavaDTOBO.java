package com.easy.cloud.core.generator.code.bo.javaclass;

import com.easy.cloud.core.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcSubModuleDefaultPackageName;
import com.easy.cloud.core.generator.code.java.desc.EcJavaContentDesc;
import com.easy.cloud.core.generator.code.java.pojo.bo.EcGenerateJavaClassBO;
import com.easy.cloud.core.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成数据传输对象
 * </p>
 *
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:54:03
 */
public class EcGenerateJavaDTOBO extends EcGenerateJavaClassBO {


	public EcGenerateJavaDTOBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildAnnotations() {
		
	}

	@Override
	protected void buildExtendsParentClass() {
		String nameEndwith = EcClassNameEndWith.POJO_PO;
		String subModulePackageName = EcSubModuleDefaultPackageName.POJO_PO;
		EcJavaContentDesc extendsParentClass = super.getCustomJavaContentByEndwith(nameEndwith, subModulePackageName, EcJavaContentDesc.class);
		super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected String getClassNameEndWith() {
		return EcClassNameEndWith.POJO_DTO;
	}

	@Override
	protected void buildImplementsInterfaces() {
		
	}

	@Override
	protected void buildConstructors() {

	}

	@Override
	protected void buildFields() {

	}

	@Override
	protected void buildMethods() {

	}

	@Override
	protected String getClassCommentEndWith() {
		return EcClassCommentEndWith.POJO_DTO;
	}

}
