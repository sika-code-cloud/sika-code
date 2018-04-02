package com.dq.easy.cloud.module.common.generator.code.java.desc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.base.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqColumnLabel;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;
import com.dq.easy.cloud.module.common.generator.code.base.utils.DqCodeGenerateUtils;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqIgnoreField;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqMethodTypeEnum;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationParamDesc;
import com.dq.easy.cloud.module.common.generator.code.java.rule.DqGenerateJavaClassRule;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * java类的内容描述类
 * 
 * @author daiqi
 * @date 2018年3月24日 上午9:45:43
 */
@SuppressWarnings({ "unchecked" })
public class DqJavaClassContentDesc extends DqJavaContentDesc {
	/** 作者 */
	private String author;
	/** 继承的父类 */
	private List<DqJavaContentDesc> extendsParentClasss;
	/** 实现的接口列表 */
	private List<DqJavaImplInterfaceContentDesc> implementsInterfaces;
	/** 构造函数列表 */
	private List<DqJavaMethodContentDesc> constructors;
	/** 属性列表 */
	private List<DqJavaFieldContentDesc> fields;
	/** 方法列表 */
	private List<DqJavaMethodContentDesc> methods;
	/** 导入的完整的类类型 */
	private Set<String> importFullClassTypes = new HashSet<>();

	public DqJavaClassContentDesc() {
		super();
	}

	public DqJavaClassContentDesc(DqGenerateRule generateRule) {
		super(generateRule);
	}

	@Override
	public String getJavaContentSign() {
		return getName();
	}
	
	public void addExtendsParentClass(DqJavaContentDesc extendsParentClass) {
		List<DqJavaContentDesc> extendsParentClasss = new ArrayList<>();
		extendsParentClasss.add(extendsParentClass);
		addExtendsParentClasss(extendsParentClasss);
	}

	public void addExtendsParentClasss(List<DqJavaContentDesc> extendsParentClasss) {
		// 增加过滤后的desc---过滤name重复的desc
		this.extendsParentClasss = getFilterDesc(extendsParentClasss, this.extendsParentClasss);

	}
	
	public void addImplementsInterface(DqJavaImplInterfaceContentDesc implementsInterface) {
		List<DqJavaImplInterfaceContentDesc> implementsInterfaces = new ArrayList<>();
		implementsInterfaces.add(implementsInterface);
		addImplementsInterfaces(implementsInterfaces);
	}

	public void addImplementsInterfaces(List<DqJavaImplInterfaceContentDesc> implementsInterfaces) {
		// 增加过滤后的desc---过滤name重复的desc
		this.implementsInterfaces = getFilterDesc(implementsInterfaces, this.implementsInterfaces);

	}

	public void addConstructor(DqJavaMethodContentDesc constructor) {
		List<DqJavaMethodContentDesc> constructors = new ArrayList<>();
		constructors.add(constructor);
		addConstructors(constructors);
	}

	public void addConstructors(List<DqJavaMethodContentDesc> constructors) {
		// 增加过滤后的desc---过滤name重复的desc
		this.constructors = getFilterDesc(constructors, this.constructors);
	}

	public void addField(DqJavaFieldContentDesc field) {
		List<DqJavaFieldContentDesc> fields = new ArrayList<>();
		fields.add(field);
		addFields(fields);
	}

	public void addFields(List<DqJavaFieldContentDesc> fields) {
		// 增加过滤后的desc---过滤name重复的desc
		this.fields = getFilterDesc(fields, this.fields);
	}

	public void addMethod(DqJavaMethodContentDesc method) {
		List<DqJavaMethodContentDesc> methods = new ArrayList<>();
		methods.add(method);
		addMethods(methods);
	}

	public void addMethods(List<DqJavaMethodContentDesc> methods) {
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
		if (DqCollectionsUtils.isNotEmpty(getAnnotations())) {
			for (DqJavaContentBaseDesc annotation : getAnnotations()) {
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
		StringBuilder classHeaderBuild = DqStringUtils.newStringBuilderDefault();
		String modifersStr = getModifiersStr();
		if (DqStringUtils.isNotEmpty(modifersStr)) {
			classHeaderBuild.append(modifersStr);
		}
		if (DqStringUtils.isNotEmpty(getName())) {
			classHeaderBuild.append(DqSymbol.EMPTY).append(getSimpleClassTypeFullStr());
		}
		// 设置继承的父类字符串		
		String extendsParentClasssStr = getExtendsParentClasssStr();
		if (DqStringUtils.isNotEmpty(extendsParentClasssStr)) {
			classHeaderBuild.append(DqSymbol.EMPTY).append("extends").append(DqSymbol.EMPTY).append(extendsParentClasssStr);
		}
		String implementsInterfacesStr = getImplementsInterfacesStr();
		if (DqStringUtils.isNotEmpty(implementsInterfacesStr)) {
			classHeaderBuild.append(DqSymbol.EMPTY).append("implements").append(DqSymbol.EMPTY).append(implementsInterfacesStr);
		}
		return classHeaderBuild.toString();
	}

	/** 获取继承父类的字符串 */
	private String getExtendsParentClasssStr() {
		if (DqCollectionsUtils.isEmpty(extendsParentClasss)) {
			return null;
		}
		StringBuilder extendsParentClassBuild = DqStringUtils.newStringBuilderDefault();
		for (int i = 0; i < extendsParentClasss.size(); ++i) {
			DqJavaContentDesc extendsParentClass = extendsParentClasss.get(i);
			extendsParentClassBuild.append(extendsParentClass.getSimpleClassTypeFullStr());
			if (i < extendsParentClasss.size() - 1) {
				extendsParentClassBuild.append(DqSymbol.COMMA);
			}
		}
		return extendsParentClassBuild.toString();
	}
	/** 获取实现接口的字符串 */
	private String getImplementsInterfacesStr() {
		if (DqCollectionsUtils.isEmpty(implementsInterfaces)) {
			return null;
		}
		StringBuilder implementsInterfaceBuild = DqStringUtils.newStringBuilderDefault();
		for (int i = 0; i < implementsInterfaces.size(); ++i) {
			DqJavaImplInterfaceContentDesc implementsInterface = implementsInterfaces.get(i);
			implementsInterfaceBuild.append(implementsInterface.getSimpleClassTypeFullStr());
			if (i < implementsInterfaces.size() - 1) {
				implementsInterfaceBuild.append(DqSymbol.COMMA);
			}
		}
		return implementsInterfaceBuild.toString();
	}

	/** 根据属性列表构建构造函数 */
	public DqJavaClassContentDesc buildConstructorsDataByFields() {
		List<DqJavaMethodContentDesc> constructors = new ArrayList<>();
		DqJavaMethodContentDesc constructor = new DqJavaMethodContentDesc();
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
	public DqFileContentBaseDesc buildDataByDatabaseSources(DqDatabaseDataSources databaseDataSources) {
		if (DqBaseUtils.isNull(databaseDataSources)) {
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
	public DqJavaClassContentDesc buildMethodsByFields() {
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
	public DqFileContentBaseDesc buildJavaMethodsByFields(List<DqJavaFieldContentDesc> fields) {
		if (DqCollectionsUtils.isEmpty(fields)) {
			return this;
		}
		if (DqCollectionsUtils.isEmpty(this.methods)) {
			this.methods = new ArrayList<>();
		}
		for (DqJavaFieldContentDesc fieldContentDesc : fields) {
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
		if (DqCollectionsUtils.isEmpty(getModifiers())) {
			return false;
		}
		for (DqJavaModifierDesc modifierDesc : getModifiers()) {
			if (modifierDesc.getCode() != null && DqModifierMappingEnum.ENUM.getModifier() == modifierDesc.getCode()) {
				return true;
			}
		}
		return false;
	}

	/** add导入继承父类的完整类类型 */
	private void addImportExtendsParentClasssFullClassType() {
		// 新增继承的类需要导入的完整类类型
		if (DqCollectionsUtils.isNotEmpty(extendsParentClasss)) {
			for (DqJavaContentDesc extendsParentClass : extendsParentClasss) {
				// 导入泛型完整类类型
				addImportGenericityFullClassTypeByDesc(extendsParentClass);
				doAddImportFullClassTypes(extendsParentClass.getFullClassType());
			}
		}

	}

	/** add导入实现接口的完整类类型 */
	private void addImportImplementsInterfacesFullClassType() {
		// 新增实现的接口需要导入的完整类类型
		if (DqCollectionsUtils.isNotEmpty(implementsInterfaces)) {
			for (DqJavaContentBaseDesc implementsInterface : implementsInterfaces) {
				// 导入泛型完整类类型
				addImportGenericityFullClassTypeByDesc(implementsInterface);
				doAddImportFullClassTypes(implementsInterface.getFullClassType());
			}
		}
	}

	/** add导入属性列表的完整类类型 */
	private void addImportFieldsFullClassType() {
		DqGenerateJavaClassRule javaClassRule = (DqGenerateJavaClassRule) getGenerateRule();
		boolean returnCondition = DqCollectionsUtils.isEmpty(fields) || DqBaseUtils.isNull(javaClassRule)
				|| !javaClassRule.isGenerateField();
		if (returnCondition) {
			return;
		}
		for (DqJavaFieldContentDesc field : fields) {
			doAddImportFullClassTypes(field.getFullClassType());
			if (DqCollectionsUtils.isEmpty(field.getAnnotations())) {
				continue;
			}
			// 导入注解完整类类型
			addImportAnnotationFullClassTypeByDesc(field);
			// 导入泛型完整类类型
			addImportGenericityFullClassTypeByDesc(field);
		}
	}

	/** 根据desc将该desc对应的注解类添加到类的importFullClassTypes中 */
	private void addImportAnnotationFullClassTypeByDesc(DqJavaContentDesc desc) {
		if (DqBaseUtils.isNull(desc) || DqCollectionsUtils.isEmpty(desc.getAnnotations())) {
			return;
		}
		for (DqJavaAnnotationDesc fileAnnotationDesc : desc.getAnnotations()) {
			doAddImportFullClassTypes(fileAnnotationDesc.getFullClassType());
		}
	}

	/** 根据desc将该desc对应的泛型类添加到类的importFullClassTypes中 */
	private void addImportGenericityFullClassTypeByDesc(DqJavaContentBaseDesc desc) {
		if (DqBaseUtils.isNull(desc) || DqCollectionsUtils.isEmpty(desc.getGenericitys())) {
			return;
		}
		for (DqJavaGenericityContentDesc genericity : desc.getGenericitys()) {
			doAddImportFullClassTypes(genericity.getFullClassType());
		}
	}

	/** 增加导入方法列表的完整类类型 */
	private void addImportMethodsFullClassType() {
		DqGenerateJavaClassRule javaClassRule = (DqGenerateJavaClassRule) getGenerateRule();
		boolean returnCondition = DqCollectionsUtils.isEmpty(methods) || DqBaseUtils.isNull(javaClassRule);
		if (returnCondition) {
			return;
		}
		for (DqJavaMethodContentDesc method : methods) {
			// 需要跳出当前循环的条件
			boolean getContinue = DqMethodTypeEnum.isGet(method.getType()) && !javaClassRule.isGenerateGetMethod();
			boolean setContinue = DqMethodTypeEnum.isSet(method.getType()) && !javaClassRule.isGenerateSetMethod();
			boolean buildContinue = DqMethodTypeEnum.isBuild(method.getType())
					&& !javaClassRule.isGenerateBuildMethod();
			if (getContinue || setContinue || buildContinue) {
				continue;
			}
			doAddImportFullClassTypes(method.getReturnFullClassType());
			if (DqCollectionsUtils.isNotEmpty(method.getAnnotations())) {
				for (DqJavaAnnotationDesc methodAnnotationDesc : method.getAnnotations()) {
					doAddImportFullClassTypes(methodAnnotationDesc.getFullClassType());
				}
			}
			if (DqCollectionsUtils.isNotEmpty(method.getGenericitys())) {
				for (DqJavaGenericityContentDesc genericity : method.getGenericitys()) {
					doAddImportFullClassTypes(genericity.getFullClassType());
				}
			}
			if (DqCollectionsUtils.isNotEmpty(method.getArgs())) {
				for (DqJavaFieldContentDesc arg : method.getArgs()) {
					doAddImportFullClassTypes(arg.getFullClassType());
					if (DqCollectionsUtils.isNotEmpty(arg.getAnnotations())) {
						for (DqJavaAnnotationDesc argAnnotation : arg.getAnnotations()) {
							doAddImportFullClassTypes(argAnnotation.getFullClassType());
						}
					}
				}
			}
		}
	}

	private void doAddImportFullClassTypes(String fullClassType) {
		if (DqBaseUtils.isNull(importFullClassTypes)) {
			importFullClassTypes = new HashSet<>();
		}
		if (DqStringUtils.isEmpty(fullClassType)) {
			return;
		}

		if (DqStringUtils.equals(fullClassType, void.class.getName())) {
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
	private void addJavaMethodDescOfDefault(DqJavaFieldContentDesc fieldContentDesc) {
		this.methods.add(buildJavaMethodDescOfGetByField(fieldContentDesc, DqMethodTypeEnum.GET));
		this.methods.add(buildJavaMethodDescOfSetByField(fieldContentDesc, DqMethodTypeEnum.SET));
		this.methods.add(buildJavaMethodDescOfBuildByField(fieldContentDesc, DqMethodTypeEnum.BUILD));
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
	private DqJavaMethodContentDesc buildJavaMethodDescOfGetByField(DqJavaFieldContentDesc fieldContentDesc,
			DqMethodTypeEnum methodTypeEnum) {
		DqJavaMethodContentDesc methodContentDesc = getJavaDefaultMethodDesc(fieldContentDesc, methodTypeEnum);

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
	private DqJavaMethodContentDesc buildJavaMethodDescOfSetByField(DqJavaFieldContentDesc fieldContentDesc,
			DqMethodTypeEnum methodTypeEnum) {
		DqJavaMethodContentDesc methodContentDesc = getJavaDefaultMethodDesc(fieldContentDesc, methodTypeEnum);

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
	private DqJavaMethodContentDesc buildJavaMethodDescOfBuildByField(DqJavaFieldContentDesc fieldContentDesc,
			DqMethodTypeEnum methodTypeEnum) {
		DqJavaMethodContentDesc methodContentDesc = getJavaDefaultMethodDesc(fieldContentDesc, methodTypeEnum);

		methodContentDesc.setReturnSimpleClassType(this.getSimpleClassType());
		methodContentDesc.setReturnFullClassType(this.getFullClassType());
		return methodContentDesc;
	}

	/** 获取默认的方法描述 */
	private DqJavaMethodContentDesc getJavaDefaultMethodDesc(DqJavaFieldContentDesc fieldContentDesc,
			DqMethodTypeEnum methodTypeEnum) {
		DqJavaMethodContentDesc methodContentDesc = new DqJavaMethodContentDesc(methodTypeEnum);
		methodContentDesc.setComment(fieldContentDesc.getComment());
		// 设置方法modifiers
		List<DqJavaModifierDesc> methodModifiers = new ArrayList<>();
		methodModifiers.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
		methodContentDesc.setModifiers(methodModifiers);
		methodContentDesc.setName(fieldContentDesc.getName());
		methodContentDesc.setSimpleClassType(fieldContentDesc.getSimpleClassType());
		methodContentDesc.setFullClassType(fieldContentDesc.getFullClassType());
		return methodContentDesc;
	}

	/** 根据数据源构建属性列表 */
	private DqJavaClassContentDesc buildFieldsByDatabaseSources(DqDatabaseDataSources databaseDataSources) {
		ResultSet resultSet = databaseDataSources.getResultSet();
		if (DqBaseUtils.isNull(resultSet)) {
			return this;
		}
		if (DqCollectionsUtils.isEmpty(this.fields)) {
			this.fields = new ArrayList<>();
		}
		try {
			while (resultSet.next()) {
				String name = DqStringUtils
						.replaceUnderLineAndUpperCase(resultSet.getString(DqColumnLabel.COLUMN_NAME));
				// id字段略过
				if (DqIgnoreField.isIgnoreField(name)) {
					continue;
				}
				DqJavaFieldContentDesc fieldContentDesc = new DqJavaFieldContentDesc();
				// 字段在数据库的注释
				fieldContentDesc.setComment(resultSet.getString(DqColumnLabel.REMARKS));
				// 设置属性注解列表信息
				List<DqJavaAnnotationDesc> fieldAnnotationDescs = getFieldsAnnotationsByResultSet(resultSet);
				fieldContentDesc.setAnnotations(fieldAnnotationDescs);

				// 设置modifer列表信息
				List<DqJavaModifierDesc> fieldModifierDescs = new ArrayList<>();
				fieldModifierDescs.add(new DqJavaModifierDesc(DqModifierMappingEnum.PUBLIC));
				fieldContentDesc.setModifiers(fieldModifierDescs);

				// 设置属性类型
				fieldContentDesc.setSimpleClassType(
						databaseDataSources.getFieldTypeByColumnType(resultSet.getString(DqColumnLabel.TYPE_NAME)));
				// 设置属性名称
				fieldContentDesc.setName(name);
				// 设置属性完整类型
				fieldContentDesc.setFullClassType(
						DqCodeGenerateUtils.getCompleteClassName(fieldContentDesc.getSimpleClassType()));
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
	private List<DqJavaAnnotationDesc> getFieldsAnnotationsByResultSet(ResultSet resultSet) throws SQLException {
		// 设置属性注解
		List<DqJavaAnnotationDesc> annotationDescs = new ArrayList<>();
		// 设置列注解描述
		DqJavaAnnotationDesc columnAnnotationDesc = new DqJavaAnnotationDesc();
		columnAnnotationDesc.setFullClassType(Column.class.getName());
		columnAnnotationDesc.setSimpleClassType(Column.class.getSimpleName());
		columnAnnotationDesc.setName(Column.class.getSimpleName());
		// 获取column参数描述列表
		List<DqJavaAnnotationParamDesc> columnAnnotationParamDescs = new ArrayList<>();
		columnAnnotationParamDescs
				.add(new DqJavaAnnotationParamDesc("name", resultSet.getString(DqColumnLabel.COLUMN_NAME)));
		columnAnnotationDesc.setParams(columnAnnotationParamDescs);

		annotationDescs.add(columnAnnotationDesc);
		return annotationDescs;
	}

	public String getCreateDateStr() {
		return DqCodeGenerateConfig.CREATE_DATE;
	}

	public String getAuthor() {
		if (DqStringUtils.isEmpty(author)) {
			return DqCodeGenerateConfig.AUTHOR_DEFAULT;
		}
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	public List<DqJavaContentDesc> getExtendsParentClasss() {
		return extendsParentClasss;
	}

	public void setExtendsParentClasss(List<DqJavaContentDesc> extendsParentClasss) {
		this.extendsParentClasss = extendsParentClasss;
	}

	public List<DqJavaImplInterfaceContentDesc> getImplementsInterfaces() {
		return implementsInterfaces;
	}

	public void setImplementsInterfaces(List<DqJavaImplInterfaceContentDesc> implementsInterfaces) {
		this.implementsInterfaces = implementsInterfaces;
	}

	public List<DqJavaMethodContentDesc> getConstructors() {
		return constructors;
	}

	public void setConstructors(List<DqJavaMethodContentDesc> constructors) {
		this.constructors = constructors;
	}

	public List<DqJavaFieldContentDesc> getFields() {
		return fields;
	}

	public void setFields(List<DqJavaFieldContentDesc> fields) {
		this.fields = fields;
	}

	public List<DqJavaMethodContentDesc> getMethods() {
		return methods;
	}

	public void setMethods(List<DqJavaMethodContentDesc> methods) {
		this.methods = methods;
	}

	public Set<String> getImportFullClassTypes() {
		return importFullClassTypes;
	}

	public void setImportFullClassTypes(Set<String> importFullClassTypes) {
		this.importFullClassTypes = importFullClassTypes;
	}
}
