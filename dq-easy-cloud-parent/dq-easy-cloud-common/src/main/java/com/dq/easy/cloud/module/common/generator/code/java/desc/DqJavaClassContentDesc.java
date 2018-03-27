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
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqIgnoreField;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqIgnoreField.DqModifierMappingEnum;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqMethodTypeEnum;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;
import com.dq.easy.cloud.module.common.generator.code.base.utils.DqCodeGenerateUtils;
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
public class DqJavaClassContentDesc extends DqJavaContentDesc {
	/** 作者 */
	private String author;
	/** 继承的父类 */
	private DqJavaContentBaseDesc extendsParentClass;
	/** 实现的接口列表 */
	private List<DqJavaImplInterfaceContentDesc> implementsInterfaces;
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

	public String getImplementsInterfacesStr() {
		if (DqCollectionsUtils.isEmpty(implementsInterfaces)) {
			return null;
		}
		StringBuilder implementsInterfaceBuild = DqStringUtils.newStringBuilderDefault();
		for (int i = 0; i < implementsInterfaces.size(); ++i) {
			DqJavaContentBaseDesc implementsInterface = implementsInterfaces.get(i);
			implementsInterfaceBuild.append(implementsInterface.getSimpleClassType());
			if (i < implementsInterfaces.size() - 1) {
				implementsInterfaceBuild.append(DqSymbol.COMMA);
			}
		}
		return implementsInterfaceBuild.toString();
	}

	/**
	 * 
	 * <p>
	 * 遍历类中所有需要导入的完整的class类型
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
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
		if (DqBaseUtils.isNotNull(extendsParentClass)
				&& DqStringUtils.isNotEmpty(extendsParentClass.getFullClassType())) {
			doAddImportFullClassTypes(extendsParentClass.getFullClassType());
		}
		// 新增实现的接口需要导入的完整类类型
		if (DqCollectionsUtils.isNotEmpty(implementsInterfaces)) {
			for (DqJavaContentBaseDesc implementsInterface : implementsInterfaces) {
				doAddImportFullClassTypes(implementsInterface.getFullClassType());
			}
		}
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
			classHeaderBuild.append(modifersStr).append(DqSymbol.EMPTY);
		}
		if (DqStringUtils.isNotEmpty(getName())) {
			classHeaderBuild.append(getName()).append(DqSymbol.EMPTY);
		}
		if (DqBaseUtils.isNotNull(getExtendsParentClass())) {
			classHeaderBuild.append("extends").append(DqSymbol.EMPTY)
					.append(getExtendsParentClass().getSimpleClassType());
		}
		String implementsInterfacesStr = getImplementsInterfacesStr();
		if (DqStringUtils.isNotEmpty(implementsInterfacesStr)) {
			classHeaderBuild.append("implements").append(DqSymbol.EMPTY).append(implementsInterfacesStr)
					.append(DqSymbol.EMPTY);
		}
		return classHeaderBuild.toString();
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
	public DqFileContentBaseDesc buildJavaMethodsByFields() {
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
			for (DqJavaAnnotationDesc fileAnnotationDesc : field.getAnnotations()) {
				doAddImportFullClassTypes(fileAnnotationDesc.getFullClassType());
			}
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
			if (DqCollectionsUtils.isEmpty(method.getAnnotations())) {
				continue;
			}
			for (DqJavaAnnotationDesc methodAnnotationDesc : method.getAnnotations()) {
				doAddImportFullClassTypes(methodAnnotationDesc.getFullClassType());
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

	public DqJavaContentBaseDesc getExtendsParentClass() {
		return extendsParentClass;
	}

	public void setExtendsParentClass(DqJavaContentBaseDesc extendsParentClass) {
		this.extendsParentClass = extendsParentClass;
	}

	public List<DqJavaImplInterfaceContentDesc> getImplementsInterfaces() {
		return implementsInterfaces;
	}

	public void setImplementsInterfaces(List<DqJavaImplInterfaceContentDesc> implementsInterfaces) {
		this.implementsInterfaces = implementsInterfaces;
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
