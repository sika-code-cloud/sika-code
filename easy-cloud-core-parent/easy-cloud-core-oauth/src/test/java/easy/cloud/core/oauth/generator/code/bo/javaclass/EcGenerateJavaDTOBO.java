package easy.cloud.core.oauth.generator.code.bo.javaclass;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;
import com.easy.cloud.core.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
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
		EcJavaContentDesc extendsParentClass = new EcJavaContentDesc();
		extendsParentClass.setSimpleClassType(EcBaseDTO.class.getSimpleName());
		extendsParentClass.setName(EcBaseDTO.class.getSimpleName());
		extendsParentClass.setFullClassType(EcBaseDTO.class.getName());
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
		super.javaClassContentDesc.setFields(getFieldsByDatabaseDataSources());
	}

	@Override
	protected void buildMethods() {
		super.javaClassContentDesc.setMethods(getMethodsByFields());
	}

	@Override
	protected String getClassCommentEndWith() {
		return EcClassCommentEndWith.POJO_DTO;
	}

}
