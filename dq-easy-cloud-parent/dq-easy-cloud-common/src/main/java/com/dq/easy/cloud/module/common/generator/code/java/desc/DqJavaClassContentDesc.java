package com.dq.easy.cloud.module.common.generator.code.java.desc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.module.common.generator.code.common.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.java.desc.anno.DqJavaAnnotationDesc;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * java类的内容描述类
 * 
 * @author daiqi
 * @date 2018年3月24日 上午9:45:43
 */
public class DqJavaClassContentDesc extends DqJavaContentDesc {

	/** 继承的父类 */
	private DqJavaContentBaseDesc extendsParentClass;
	/** 实现的接口列表 */
	private List<DqJavaInterfaceContentDesc> implementsInterfaces;
	/** 属性列表 */
	private List<DqJavaFieldContentDesc> fields;
	/** 方法列表 */
	private List<DqJavaMethodContentDesc> methods;
	/** 导入的类名列表 */
	private Set<String> importClassNames = new HashSet<>();

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

	public DqJavaContentBaseDesc getExtendsParentClass() {
		return extendsParentClass;
	}

	public void setExtendsParentClass(DqJavaContentBaseDesc extendsParentClass) {
		this.extendsParentClass = extendsParentClass;
	}

	public List<DqJavaInterfaceContentDesc> getImplementsInterfaces() {
		return implementsInterfaces;
	}

	public void setImplementsInterfaces(List<DqJavaInterfaceContentDesc> implementsInterfaces) {
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

	public Set<String> getImportClassNames() {
		return importClassNames;
	}

	public void setImportClassNames(Set<String> importClassNames) {
		this.importClassNames = importClassNames;
	}

	public void addImportClassName() {
		if (DqBaseUtils.isNull(importClassNames)) {
			importClassNames = new HashSet<>();
		}
		if (DqCollectionsUtils.isNotEmpty(getAnnotations())) {
			for (DqJavaContentBaseDesc annotation : getAnnotations()) {
				importClassNames.add(annotation.getFullClassType());
			}
		}
		if (DqBaseUtils.isNotNull(extendsParentClass)
				&& DqStringUtils.isNotEmpty(extendsParentClass.getFullClassType())) {
			importClassNames.add(extendsParentClass.getFullClassType());
		}
		if (DqCollectionsUtils.isNotEmpty(implementsInterfaces)) {
			for (DqJavaContentBaseDesc implementsInterface : implementsInterfaces) {
				importClassNames.add(implementsInterface.getFullClassType());
			}
		}
		if (DqCollectionsUtils.isNotEmpty(fields)) {
			for (DqJavaFieldContentDesc field : fields) {
				importClassNames.add(field.getFullClassType());
				for (DqJavaAnnotationDesc fileAnnotationDesc : field.getAnnotations()) {
					importClassNames.add(fileAnnotationDesc.getFullClassType());
				}
			}
		}
		if (DqCollectionsUtils.isNotEmpty(methods)) {
			for (DqJavaContentBaseDesc method : methods) {
				importClassNames.add(method.getFullClassType());
			}
		}
	}

}
