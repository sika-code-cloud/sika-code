package com.easy.cloud.core.generate.bo.javaclass;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.easy.cloud.core.basic.pojo.entity.EcBaseEntity;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.common.generator.code.java.desc.EcJavaClassContentDesc;
import com.easy.cloud.core.common.generator.code.java.desc.anno.EcJavaAnnotationDesc;
import com.easy.cloud.core.common.generator.code.java.pojo.bo.EcGenerateJavaClassBO;
import com.easy.cloud.core.common.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成持久化对象
 * </p>
 * 
 * <pre>
 * 生成java持久化类的业务逻辑处理类步骤
 * 1、继承EcGenerateJavaClassBO抽象类（该抽象类为生成普通的java类需要继承的）
 * 2、重写getClassNameEndWith方法（必须重写，若无返回空字符串）
 * 3、重写getClassCommentEndWith方法（必须重写，若无返回空字符串）
 * 4、使用EcGenerateJavaDOBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule)构造函数  : 必选
 * 5、重写构建类注解列表:buildAnnotations():方法,（若无注解，可以不重写） : 可选
 * 6、重写构建类继承的父类列表:buildExtendsParentClass():方法（若无继承的父类 ，可以不重写） : 可选
 * 7、重写构建类实现接口的列表:buildImplementsInterfaces():方法（若无实现的接口，可以不重写） : 可选
 * 8、重写构建类构造函数列表:buildConstructors():方法（若只是需要默认构造 ，可以不重写） : 可选
 * 9、重写构建类属性列表:buildFields():方法（若不需要属性，可以不重写） : 可选
 * 10、重写构建类方法列表:buildMethods():方法（若不需要方法 ，可以不重写） : 可选
 * 11、重写构建类泛型列表:buildGenericitys():方法（若不需要泛型 空实现即可） : 可选
 * </pre>
 * @author daiqi 创建时间 2018年3月27日 上午9:49:06
 */
public class EcGenerateJavaDOBO extends EcGenerateJavaClassBO {
	/** 
	 * 
	 * @param generateJavaBaseDTO : EcGenerateJavaBaseDTO : 生成java的数据传输对象
	 * @param generateRule : EcGenerateRule : 生成规则
	 */
	public EcGenerateJavaDOBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildAnnotations() {
		if (EcBaseUtils.isNull(dataBaseConfig)) {
			return;
		}

		EcJavaAnnotationDesc tableAnnotationDesc = new EcJavaAnnotationDesc();
		tableAnnotationDesc.setName(Table.class.getSimpleName());
		tableAnnotationDesc.setSimpleClassType(Table.class.getSimpleName());
		tableAnnotationDesc.setFullClassType(Table.class.getName());
		// 设置类注解参数---begin
		tableAnnotationDesc.addParam("name", dataBaseConfig.getTableName());
		// 设置类注解参数---end
		super.javaClassContentDesc.addAnnotation(tableAnnotationDesc);

		EcJavaAnnotationDesc entityAnnotationDesc = new EcJavaAnnotationDesc();
		entityAnnotationDesc.setName(Entity.class.getSimpleName());
		entityAnnotationDesc.setSimpleClassType(Entity.class.getSimpleName());
		entityAnnotationDesc.setFullClassType(Entity.class.getName());
		super.javaClassContentDesc.addAnnotation(entityAnnotationDesc);

	}

	@Override
	protected void buildExtendsParentClass() {
		EcJavaClassContentDesc extendsParentClass = new EcJavaClassContentDesc();
		extendsParentClass.setName(EcBaseEntity.class.getSimpleName());
		extendsParentClass.setSimpleClassType(EcBaseEntity.class.getSimpleName());
		extendsParentClass.setFullClassType(EcBaseEntity.class.getName());
		super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected void buildFields() {
		super.javaClassContentDesc.addFields(getFieldsByDatabaseDataSources());
	}

	@Override
	protected void buildMethods() {
		super.javaClassContentDesc.addMethods(super.getMethodsByFields());
	}

	@Override
	protected String getClassCommentEndWith() {
		// 设置注释的endWith（如若传入的注释主体为User，则完整注释即为 commonBody+commentEndWith）	
		return EcClassCommentEndWith.POJO_PO;
	}

	@Override
	protected String getClassNameEndWith() {
		// 类名的endWith（如传入的ClassNameBody为User，则类名即为（classNameBody+classNameEndWith）
		// 如classNameBody = User ，则用户持久化实体类为UserEntity或者 UserPO或者UserDO
		return EcClassNameEndWith.POJO_PO;
	}

}
