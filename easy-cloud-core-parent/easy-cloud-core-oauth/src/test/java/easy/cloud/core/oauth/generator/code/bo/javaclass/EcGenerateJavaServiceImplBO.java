package easy.cloud.core.oauth.generator.code.bo.javaclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcModifierMappingEnum;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcSubModuleDefaultPackageName;
import com.easy.cloud.core.generator.code.java.desc.EcJavaContentDesc;
import com.easy.cloud.core.generator.code.java.desc.EcJavaFieldContentDesc;
import com.easy.cloud.core.generator.code.java.desc.EcJavaImplInterfaceContentDesc;
import com.easy.cloud.core.generator.code.java.desc.anno.EcJavaAnnotationDesc;
import com.easy.cloud.core.generator.code.java.pojo.bo.EcGenerateJavaClassBO;
import com.easy.cloud.core.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成业务逻辑类
 * </p>
 *
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:54:03
 */
public class EcGenerateJavaServiceImplBO extends EcGenerateJavaClassBO {

	public EcGenerateJavaServiceImplBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);

	}

	@Override
	protected void buildAnnotations() {
		EcJavaAnnotationDesc serviceAnnotation = new EcJavaAnnotationDesc();
		serviceAnnotation.setName(Service.class.getSimpleName());
		serviceAnnotation.setSimpleClassType(Service.class.getSimpleName());
		serviceAnnotation.setFullClassType(Service.class.getName());

		String value = EcStringUtils.uncapitalize(generateJavaBaseDTO.getClassBodyName() + EcClassNameEndWith.SERVICE_INF);
		serviceAnnotation.addParam("value", value);
		javaClassContentDesc.addAnnotation(serviceAnnotation);
	}

	@Override
	protected void buildExtendsParentClass() {
		EcJavaContentDesc extendsParentClass = new EcJavaContentDesc();
		extendsParentClass.setName(EcBaseService.class.getSimpleName());
		extendsParentClass.setSimpleClassType(EcBaseService.class.getSimpleName());
		extendsParentClass.setFullClassType(EcBaseService.class.getName());
		javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected void buildImplementsInterfaces() {
		String nameEndwith = EcClassNameEndWith.SERVICE_INF;
		String subModulePackageName = EcSubModuleDefaultPackageName.SERVICE_INF;
		EcJavaImplInterfaceContentDesc implementsInterface = super.getCustomJavaContentByEndwith(nameEndwith,
				subModulePackageName, EcJavaImplInterfaceContentDesc.class);
		super.javaClassContentDesc.addImplementsInterface(implementsInterface);
	}

	@Override
	protected void buildConstructors() {

	}

	@Override
	protected void buildFields() {
		EcJavaFieldContentDesc fieldContentDesc = new EcJavaFieldContentDesc();
		fieldContentDesc.setComment(super.generateJavaBaseDTO.getClassComment() + EcClassCommentEndWith.DAO_INF);
		// 设置属性注解
		EcJavaAnnotationDesc annotationDesc = new EcJavaAnnotationDesc();
		annotationDesc.setName(Autowired.class.getSimpleName());
		annotationDesc.setSimpleClassType(Autowired.class.getSimpleName());
		annotationDesc.setFullClassType(Autowired.class.getName());
		fieldContentDesc.addAnnotation(annotationDesc);

		// 设置属性的modifiers
		fieldContentDesc.addModifier(EcModifierMappingEnum.PRIVATE);
		// 设置属性名称和类型
		String daoName = super.generateJavaBaseDTO.getClassBodyName() + EcClassNameEndWith.DAO_INF;
		fieldContentDesc.setName(daoName);
		fieldContentDesc.setSimpleClassType(daoName);
		fieldContentDesc.setPackageName(getFullPackageName(EcSubModuleDefaultPackageName.DAO_INF));
		fieldContentDesc.buildFullClassType();

		javaClassContentDesc.addField(fieldContentDesc);
	}

	@Override
	protected void buildMethods() {

	}

	@Override
	protected String getClassNameEndWith() {
		return EcClassNameEndWith.SERVICE_IMPL;
	}

	@Override
	protected String getClassCommentEndWith() {
		return EcClassCommentEndWith.SERVICE_IMPL;
	}
}
