package com.easy.cloud.core.common.generator.code.java.desc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.file.pojo.desc.EcFileContentBaseDesc;
import com.easy.cloud.core.common.generator.code.base.config.EcCodeGenerateConfig;
import com.easy.cloud.core.common.generator.code.base.constant.EcCodeGenerateConstant.EcColumnLabel;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.base.sources.database.EcDatabaseDataSources;
import com.easy.cloud.core.common.generator.code.base.utils.EcCodeGenerateUtils;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcMethodTypeEnum;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcModifierMappingEnum;
import com.easy.cloud.core.common.generator.code.java.desc.anno.EcJavaAnnotationDesc;
import com.easy.cloud.core.common.generator.code.java.desc.anno.EcJavaAnnotationParamDesc;
import com.easy.cloud.core.common.generator.code.java.rule.EcGenerateJavaClassRule;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * java类的内容描述类
 * 
 * @author daiqi
 * @date 2018年3月24日 上午9:45:43
 */
@SuppressWarnings({ "unchecked" })
public class EcJavaClassContentDesc extends EcJavaContentDesc {
	/** 作者 */
	private String author;
	/** 继承的父类 */
	private List<EcJavaContentDesc> extendsParentClasss;
	/** 实现的接口列表 */
	private List<EcJavaImplInterfaceContentDesc> implementsInterfaces;
	/** 构造函数列表 */
	private List<EcJavaMethodContentDesc> constructors;
	/** 属性列表 */
	private List<EcJavaFieldContentDesc> fields;
	/** 方法列表 */
	private List<EcJavaMethodContentDesc> methods;
	/** 导入的完整的类类型 */
	private Set<String> importFullClassTypes = new HashSet<>();
	/** 忽略的属性集合 */
	private Map<String, Boolean> ignoreFields;
	public EcJavaClassContentDesc() {
		super();
	}

	public EcJavaClassContentDesc(EcGenerateRule generateRule) {
		super(generateRule);
	}

	@Override
	public String getJavaContentSign() {
		return getName();
	}
	
	public void addExtendsParentClass(EcJavaContentDesc extendsParentClass) {
		List<EcJavaContentDesc> extendsParentClasss = new ArrayList<>();
		extendsParentClasss.add(extendsParentClass);
		addExtendsParentClasss(extendsParentClasss);
	}

	public void addExtendsParentClasss(List<EcJavaContentDesc> extendsParentClasss) {
		// 增加过滤后的desc---过滤name重复的desc
		this.extendsParentClasss = getFilterDesc(extendsParentClasss, this.extendsParentClasss);

	}
	
	public void addImplementsInterface(EcJavaImplInterfaceContentDesc implementsInterface) {
		List<EcJavaImplInterfaceContentDesc> implementsInterfaces = new ArrayList<>();
		implementsInterfaces.add(implementsInterface);
		addImplementsInterfaces(implementsInterfaces);
	}

	public void addImplementsInterfaces(List<EcJavaImplInterfaceContentDesc> implementsInterfaces) {
		// 增加过滤后的desc---过滤name重复的desc
		this.implementsInterfaces = getFilterDesc(implementsInterfaces, this.implementsInterfaces);

	}

	public void addConstructor(EcJavaMethodContentDesc constructor) {
		List<EcJavaMethodContentDesc> constructors = new ArrayList<>();
		constructors.add(constructor);
		addConstructors(constructors);
	}

	public void addConstructors(List<EcJavaMethodContentDesc> constructors) {
		// 增加过滤后的desc---过滤name重复的desc
		this.constructors = getFilterDesc(constructors, this.constructors);
	}

	public void addField(EcJavaFieldContentDesc field) {
		List<EcJavaFieldContentDesc> fields = new ArrayList<>();
		fields.add(field);
		addFields(fields);
	}

	public void addFields(List<EcJavaFieldContentDesc> fields) {
		// 增加过滤后的desc---过滤name重复的desc
		this.fields = getFilterDesc(fields, this.fields);
	}

	public void addMethod(EcJavaMethodContentDesc method) {
		List<EcJavaMethodContentDesc> methods = new ArrayList<>();
		methods.add(method);
		addMethods(methods);
	}

	public void addMethods(List<EcJavaMethodContentDesc> methods) {
		// 增加过滤后的desc---过滤name重复的desc
		this.methods = getFilterDesc(methods, this.methods);
	}

	/**
	 * 
	 * <p>
	 * 遍历类中所有需要导入的完整的class类型
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年3月26日 下午2:05:45
	 */
	public void addImportFullClassType() {

		// 新增自身注解需要导入的完整类类型
		if (EcCollectionsUtils.isNotEmpty(getAnnotations())) {
			for (EcJavaContentBaseDesc annotation : getAnnotations()) {
				doAddImportFullClassTypes(annotation.getFullClassType());
			}
		}
		// 新增继承的类需要导入的完整类类型
		addImportExtendsParentClasssFullClassType();
		// 新增实现的接口需要导入的完整类类型
		addImportImplementsInterfacesFullClassType();
		// 新增属性需要导入的完整类类型
		addImportFieldsFullClassType();
		// 新增方法需要导入的完整类类型
		addImportMethodsFullClassType();
	}

	/**
	 * 
	 * <p>
	 * 获取类的头部字符串
	 * </p>
	 *
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 下午2:11:31
	 */
	public String getClassHeaderStr() {
		StringBuilder classHeaderBuild = EcStringUtils.newStringBuilderDefault();
		String modifersStr = getModifiersStr();
		if (EcStringUtils.isNotEmpty(modifersStr)) {
			classHeaderBuild.append(modifersStr);
		}
		if (EcStringUtils.isNotEmpty(getName())) {
			classHeaderBuild.append(EcSymbol.EMPTY).append(getSimpleClassTypeFullStr());
		}
		// 设置继承的父类字符串		
		String extendsParentClasssStr = getExtendsParentClasssStr();
		if (EcStringUtils.isNotEmpty(extendsParentClasssStr)) {
			classHeaderBuild.append(EcSymbol.EMPTY).append("extends").append(EcSymbol.EMPTY).append(extendsParentClasssStr);
		}
		String implementsInterfacesStr = getImplementsInterfacesStr();
		if (EcStringUtils.isNotEmpty(implementsInterfacesStr)) {
			classHeaderBuild.append(EcSymbol.EMPTY).append("implements").append(EcSymbol.EMPTY).append(implementsInterfacesStr);
		}
		return classHeaderBuild.toString();
	}

	/** 获取继承父类的字符串 */
	private String getExtendsParentClasssStr() {
		if (EcCollectionsUtils.isEmpty(extendsParentClasss)) {
			return null;
		}
		StringBuilder extendsParentClassBuild = EcStringUtils.newStringBuilderDefault();
		for (int i = 0; i < extendsParentClasss.size(); ++i) {
			EcJavaContentDesc extendsParentClass = extendsParentClasss.get(i);
			extendsParentClassBuild.append(extendsParentClass.getSimpleClassTypeFullStr());
			if (i < extendsParentClasss.size() - 1) {
				extendsParentClassBuild.append(EcSymbol.COMMA);
			}
		}
		return extendsParentClassBuild.toString();
	}
	/** 获取实现接口的字符串 */
	private String getImplementsInterfacesStr() {
		if (EcCollectionsUtils.isEmpty(implementsInterfaces)) {
			return null;
		}
		StringBuilder implementsInterfaceBuild = EcStringUtils.newStringBuilderDefault();
		for (int i = 0; i < implementsInterfaces.size(); ++i) {
			EcJavaImplInterfaceContentDesc implementsInterface = implementsInterfaces.get(i);
			implementsInterfaceBuild.append(implementsInterface.getSimpleClassTypeFullStr());
			if (i < implementsInterfaces.size() - 1) {
				implementsInterfaceBuild.append(EcSymbol.COMMA);
			}
		}
		return implementsInterfaceBuild.toString();
	}

	/** 根据属性列表构建构造函数 */
	public EcJavaClassContentDesc buildConstructorsDataByFields() {
		List<EcJavaMethodContentDesc> constructors = new ArrayList<>();
		EcJavaMethodContentDesc constructor = new EcJavaMethodContentDesc();
		// 设置名称
		constructor.setName(this.getName());
		constructor.setSimpleClassType(this.getSimpleClassType());
		// 设置形参列表
		constructor.setArgs(getFields());

		constructors.add(constructor);
		this.constructors = constructors;
		return this;
	}

	@Override
	public EcFileContentBaseDesc buildDataByDatabaseSources(EcDatabaseDataSources databaseDataSources) {
		if (EcBaseUtils.isNull(databaseDataSources)) {
			return this;
		}
		// 初始化属性列表信息
		buildFieldsByDatabaseSources(databaseDataSources);
		return this;
	}

	/**
	 * 
	 * <p>
	 * 根据属性构建方法
	 * </p>
	 *
	 * @return
	 * @author daiqi 创建时间 2018年3月27日 下午1:51:42
	 */
	public EcJavaClassContentDesc buildMethodsByFields() {
		buildJavaMethodsByFields(this.fields);
		return this;
	}

	/**
	 * 
	 * <p>
	 * 根据属性构建方法
	 * </p>
	 *
	 * @param fields
	 * @return
	 * @author daiqi 创建时间 2018年3月27日 下午1:51:28
	 */
	public EcFileContentBaseDesc buildJavaMethodsByFields(List<EcJavaFieldContentDesc> fields) {
		if (EcCollectionsUtils.isEmpty(fields)) {
			return this;
		}
		if (EcCollectionsUtils.isEmpty(this.methods)) {
			this.methods = new ArrayList<>();
		}
		for (EcJavaFieldContentDesc fieldContentDesc : fields) {
			addJavaMethodDescOfDefault(fieldContentDesc);
		}
		return this;
	}

	/**
	 * 
	 * <p>
	 * 判断当前class是否是枚举类
	 * </p>
	 *
	 * @param fields
	 * @return
	 * @author daiqi 创建时间 2018年3月27日 下午1:51:28
	 */
	public boolean isEnum() {
		if (EcCollectionsUtils.isEmpty(getModifiers())) {
			return false;
		}
		for (EcJavaModifierDesc modifierDesc : getModifiers()) {
			if (modifierDesc.getCode() != null && EcModifierMappingEnum.ENUM.getModifier() == modifierDesc.getCode()) {
				return true;
			}
		}
		return false;
	}

	/** add导入继承父类的完整类类型 */
	private void addImportExtendsParentClasssFullClassType() {
		// 新增继承的类需要导入的完整类类型
		if (EcCollectionsUtils.isNotEmpty(extendsParentClasss)) {
			for (EcJavaContentDesc extendsParentClass : extendsParentClasss) {
				// 导入泛型完整类类型
				addImportGenericityFullClassTypeByDesc(extendsParentClass);
				doAddImportFullClassTypes(extendsParentClass.getFullClassType());
			}
		}

	}

	/** add导入实现接口的完整类类型 */
	private void addImportImplementsInterfacesFullClassType() {
		// 新增实现的接口需要导入的完整类类型
		if (EcCollectionsUtils.isNotEmpty(implementsInterfaces)) {
			for (EcJavaContentBaseDesc implementsInterface : implementsInterfaces) {
				// 导入泛型完整类类型
				addImportGenericityFullClassTypeByDesc(implementsInterface);
				doAddImportFullClassTypes(implementsInterface.getFullClassType());
			}
		}
	}

	/** add导入属性列表的完整类类型 */
	private void addImportFieldsFullClassType() {
		EcGenerateJavaClassRule javaClassRule = (EcGenerateJavaClassRule) getGenerateRule();
		boolean returnCondition = EcCollectionsUtils.isEmpty(fields) || EcBaseUtils.isNull(javaClassRule)
				|| !javaClassRule.isGenerateField();
		if (returnCondition) {
			return;
		}
		for (EcJavaFieldContentDesc field : fields) {
			doAddImportFullClassTypes(field.getFullClassType());
			if (EcCollectionsUtils.isEmpty(field.getAnnotations())) {
				continue;
			}
			// 导入注解完整类类型
			addImportAnnotationFullClassTypeByDesc(field);
			// 导入泛型完整类类型
			addImportGenericityFullClassTypeByDesc(field);
		}
	}

	/** 根据desc将该desc对应的注解类添加到类的importFullClassTypes中 */
	private void addImportAnnotationFullClassTypeByDesc(EcJavaContentDesc desc) {
		if (EcBaseUtils.isNull(desc) || EcCollectionsUtils.isEmpty(desc.getAnnotations())) {
			return;
		}
		for (EcJavaAnnotationDesc fileAnnotationDesc : desc.getAnnotations()) {
			doAddImportFullClassTypes(fileAnnotationDesc.getFullClassType());
		}
	}

	/** 根据desc将该desc对应的泛型类添加到类的importFullClassTypes中 */
	private void addImportGenericityFullClassTypeByDesc(EcJavaContentBaseDesc desc) {
		if (EcBaseUtils.isNull(desc) || EcCollectionsUtils.isEmpty(desc.getGenericitys())) {
			return;
		}
		for (EcJavaGenericityContentDesc genericity : desc.getGenericitys()) {
			doAddImportFullClassTypes(genericity.getFullClassType());
		}
	}

	/** 增加导入方法列表的完整类类型 */
	private void addImportMethodsFullClassType() {
		EcGenerateJavaClassRule javaClassRule = (EcGenerateJavaClassRule) getGenerateRule();
		boolean returnCondition = EcCollectionsUtils.isEmpty(methods) || EcBaseUtils.isNull(javaClassRule);
		if (returnCondition) {
			return;
		}
		for (EcJavaMethodContentDesc method : methods) {
			// 需要跳出当前循环的条件
			boolean getContinue = EcMethodTypeEnum.isGet(method.getType()) && !javaClassRule.isGenerateGetMethod();
			boolean setContinue = EcMethodTypeEnum.isSet(method.getType()) && !javaClassRule.isGenerateSetMethod();
			boolean buildContinue = EcMethodTypeEnum.isBuild(method.getType())
					&& !javaClassRule.isGenerateBuildMethod();
			if (getContinue || setContinue || buildContinue) {
				continue;
			}
			doAddImportFullClassTypes(method.getReturnFullClassType());
			if (EcCollectionsUtils.isNotEmpty(method.getAnnotations())) {
				for (EcJavaAnnotationDesc methodAnnotationDesc : method.getAnnotations()) {
					doAddImportFullClassTypes(methodAnnotationDesc.getFullClassType());
				}
			}
			if (EcCollectionsUtils.isNotEmpty(method.getGenericitys())) {
				for (EcJavaGenericityContentDesc genericity : method.getGenericitys()) {
					doAddImportFullClassTypes(genericity.getFullClassType());
				}
			}
			if (EcCollectionsUtils.isNotEmpty(method.getArgs())) {
				for (EcJavaFieldContentDesc arg : method.getArgs()) {
					doAddImportFullClassTypes(arg.getFullClassType());
					if (EcCollectionsUtils.isNotEmpty(arg.getAnnotations())) {
						for (EcJavaAnnotationDesc argAnnotation : arg.getAnnotations()) {
							doAddImportFullClassTypes(argAnnotation.getFullClassType());
						}
					}
				}
			}
		}
	}

	private void doAddImportFullClassTypes(String fullClassType) {
		if (EcBaseUtils.isNull(importFullClassTypes)) {
			importFullClassTypes = new HashSet<>();
		}
		if (EcStringUtils.isEmpty(fullClassType)) {
			return;
		}

		if (EcStringUtils.equals(fullClassType, void.class.getName())) {
			return;
		}
		importFullClassTypes.add(fullClassType);
	}

	/**
	 * 
	 * <p>
	 * 根据field描述生成其默认的方法对象添加到methods
	 * </p>
	 *
	 * @param fieldContentDesc
	 * @author daiqi 创建时间 2018年3月26日 上午11:30:05
	 */
	private void addJavaMethodDescOfDefault(EcJavaFieldContentDesc fieldContentDesc) {
		this.methods.add(buildJavaMethodDescOfGetByField(fieldContentDesc, EcMethodTypeEnum.GET));
		this.methods.add(buildJavaMethodDescOfSetByField(fieldContentDesc, EcMethodTypeEnum.SET));
		this.methods.add(buildJavaMethodDescOfBuildByField(fieldContentDesc, EcMethodTypeEnum.BUILD));
	}

	/**
	 * 
	 * <p>
	 * 根据属性构建java方法描述---get方法
	 * </p>
	 *
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 上午11:18:13
	 */
	private EcJavaMethodContentDesc buildJavaMethodDescOfGetByField(EcJavaFieldContentDesc fieldContentDesc,
			EcMethodTypeEnum methodTypeEnum) {
		EcJavaMethodContentDesc methodContentDesc = getJavaDefaultMethodDesc(fieldContentDesc, methodTypeEnum);

		methodContentDesc.setReturnSimpleClassType(fieldContentDesc.getSimpleClassType());
		methodContentDesc.setReturnFullClassType(fieldContentDesc.getFullClassType());
		return methodContentDesc;
	}

	/**
	 * 
	 * <p>
	 * 根据属性构建java方法描述---set方法
	 * </p>
	 *
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 上午11:18:13
	 */
	private EcJavaMethodContentDesc buildJavaMethodDescOfSetByField(EcJavaFieldContentDesc fieldContentDesc,
			EcMethodTypeEnum methodTypeEnum) {
		EcJavaMethodContentDesc methodContentDesc = getJavaDefaultMethodDesc(fieldContentDesc, methodTypeEnum);

		methodContentDesc.setReturnSimpleClassType(void.class.getSimpleName());
		methodContentDesc.setReturnFullClassType(void.class.getName());

		return methodContentDesc;
	}

	/**
	 * 
	 * <p>
	 * 根据属性构建java方法描述---build方法
	 * </p>
	 *
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 上午11:18:13
	 */
	private EcJavaMethodContentDesc buildJavaMethodDescOfBuildByField(EcJavaFieldContentDesc fieldContentDesc,
			EcMethodTypeEnum methodTypeEnum) {
		EcJavaMethodContentDesc methodContentDesc = getJavaDefaultMethodDesc(fieldContentDesc, methodTypeEnum);

		methodContentDesc.setReturnSimpleClassType(this.getSimpleClassType());
		methodContentDesc.setReturnFullClassType(this.getFullClassType());
		return methodContentDesc;
	}

	/** 获取默认的方法描述 */
	private EcJavaMethodContentDesc getJavaDefaultMethodDesc(EcJavaFieldContentDesc fieldContentDesc,
			EcMethodTypeEnum methodTypeEnum) {
		EcJavaMethodContentDesc methodContentDesc = new EcJavaMethodContentDesc(methodTypeEnum);
		methodContentDesc.setComment(fieldContentDesc.getComment());
		// 设置方法modifiers
		List<EcJavaModifierDesc> methodModifiers = new ArrayList<>();
		methodModifiers.add(new EcJavaModifierDesc(EcModifierMappingEnum.PUBLIC));
		methodContentDesc.setModifiers(methodModifiers);
		methodContentDesc.setName(fieldContentDesc.getName());
		methodContentDesc.setSimpleClassType(fieldContentDesc.getSimpleClassType());
		methodContentDesc.setFullClassType(fieldContentDesc.getFullClassType());
		return methodContentDesc;
	}

	/** 根据数据源构建属性列表 */
	private EcJavaClassContentDesc buildFieldsByDatabaseSources(EcDatabaseDataSources databaseDataSources) {
		ResultSet resultSet = databaseDataSources.getResultSet();
		if (EcBaseUtils.isNull(resultSet)) {
			return this;
		}
		if (EcCollectionsUtils.isEmpty(this.fields)) {
			this.fields = new ArrayList<>();
		}
		try {
			while (resultSet.next()) {
				String name = EcStringUtils
						.replaceUnderLineAndUpperCase(resultSet.getString(EcColumnLabel.COLUMN_NAME));
				// id字段略过
				Boolean ignoreField = EcMapUtils.getBoolean(ignoreFields, name);
				if (EcBaseUtils.isNotNull(ignoreField) && ignoreField) {
					continue;
				}
				EcJavaFieldContentDesc fieldContentDesc = new EcJavaFieldContentDesc();
				// 字段在数据库的注释
				fieldContentDesc.setComment(resultSet.getString(EcColumnLabel.REMARKS));
				// 设置属性注解列表信息
				List<EcJavaAnnotationDesc> fieldAnnotationDescs = getFieldsAnnotationsByResultSet(resultSet);
				fieldContentDesc.setAnnotations(fieldAnnotationDescs);

				// 设置modifer列表信息
				List<EcJavaModifierDesc> fieldModifierDescs = new ArrayList<>();
				fieldModifierDescs.add(new EcJavaModifierDesc(EcModifierMappingEnum.PUBLIC));
				fieldContentDesc.setModifiers(fieldModifierDescs);

				// 设置属性类型
				fieldContentDesc.setSimpleClassType(
						databaseDataSources.getFieldTypeByColumnType(resultSet.getString(EcColumnLabel.TYPE_NAME)));
				// 设置属性名称
				fieldContentDesc.setName(name);
				// 设置属性完整类型
				fieldContentDesc.setFullClassType(
						EcCodeGenerateUtils.getCompleteClassName(fieldContentDesc.getSimpleClassType()));
				this.fields.add(fieldContentDesc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 
	 * <p>
	 * 根据dataSource构建属性注解列表
	 * </p>
	 *
	 * @param databaseDataSources
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 上午10:18:57
	 * @throws SQLException
	 */
	private List<EcJavaAnnotationDesc> getFieldsAnnotationsByResultSet(ResultSet resultSet) throws SQLException {
		// 设置属性注解
		List<EcJavaAnnotationDesc> annotationDescs = new ArrayList<>();
		// 设置列注解描述
		EcJavaAnnotationDesc columnAnnotationDesc = new EcJavaAnnotationDesc();
		columnAnnotationDesc.setFullClassType(Column.class.getName());
		columnAnnotationDesc.setSimpleClassType(Column.class.getSimpleName());
		columnAnnotationDesc.setName(Column.class.getSimpleName());
		// 获取column参数描述列表
		List<EcJavaAnnotationParamDesc> columnAnnotationParamDescs = new ArrayList<>();
		columnAnnotationParamDescs
				.add(new EcJavaAnnotationParamDesc("name", resultSet.getString(EcColumnLabel.COLUMN_NAME)));
		columnAnnotationDesc.setParams(columnAnnotationParamDescs);

		annotationDescs.add(columnAnnotationDesc);
		return annotationDescs;
	}

	public String getCreateDateStr() {
		return EcCodeGenerateConfig.CREATE_DATE;
	}

	public String getAuthor() {
		if (EcStringUtils.isEmpty(author)) {
			return EcCodeGenerateConfig.AUTHOR_DEFAULT;
		}
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	public List<EcJavaContentDesc> getExtendsParentClasss() {
		return extendsParentClasss;
	}

	public void setExtendsParentClasss(List<EcJavaContentDesc> extendsParentClasss) {
		this.extendsParentClasss = extendsParentClasss;
	}

	public List<EcJavaImplInterfaceContentDesc> getImplementsInterfaces() {
		return implementsInterfaces;
	}

	public void setImplementsInterfaces(List<EcJavaImplInterfaceContentDesc> implementsInterfaces) {
		this.implementsInterfaces = implementsInterfaces;
	}

	public List<EcJavaMethodContentDesc> getConstructors() {
		return constructors;
	}

	public void setConstructors(List<EcJavaMethodContentDesc> constructors) {
		this.constructors = constructors;
	}

	public List<EcJavaFieldContentDesc> getFields() {
		return fields;
	}

	public void setFields(List<EcJavaFieldContentDesc> fields) {
		this.fields = fields;
	}

	public List<EcJavaMethodContentDesc> getMethods() {
		return methods;
	}

	public void setMethods(List<EcJavaMethodContentDesc> methods) {
		this.methods = methods;
	}

	public Set<String> getImportFullClassTypes() {
		return importFullClassTypes;
	}

	public void setImportFullClassTypes(Set<String> importFullClassTypes) {
		this.importFullClassTypes = importFullClassTypes;
	}

	public Map<String, Boolean> getIgnoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(Map<String, Boolean> ignoreFields) {
		this.ignoreFields = ignoreFields;
	}
}
