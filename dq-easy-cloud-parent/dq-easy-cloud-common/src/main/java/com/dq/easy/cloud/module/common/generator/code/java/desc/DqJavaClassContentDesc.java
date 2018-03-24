package com.dq.easy.cloud.module.common.generator.code.java.desc;

import java.util.List;
import java.util.Set;

/**
 * java类的内容描述类
 * @author daiqi
 * @date 2018年3月24日 上午9:45:43
 */
public class DqJavaClassContentDesc extends DqJavaContentDesc{
	/** java文件所在包名 */
	private String packageName;
	/** 导入的类名列表 */
	private Set<String> importClassNames;
	/** 继承的父类 */
	private DqJavaContentBaseDesc extendsParentClass;
	/** 实现的接口列表 */
	private List<DqJavaContentBaseDesc> implementsInterfaces;
	/** 属性列表 */
	private List<DqJavaContentDesc> fields;
	/** 方法列表 */
	private List<DqJavaContentDesc> methods;
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Set<String> getImportClassNames() {
		return importClassNames;
	}
	public void setImportClassNames(Set<String> importClassNames) {
		this.importClassNames = importClassNames;
	}
	public DqJavaContentBaseDesc getExtendsParentClass() {
		return extendsParentClass;
	}
	public void setExtendsParentClass(DqJavaContentBaseDesc extendsParentClass) {
		this.extendsParentClass = extendsParentClass;
	}
	public List<DqJavaContentBaseDesc> getImplementsInterfaces() {
		return implementsInterfaces;
	}
	public void setImplementsInterfaces(List<DqJavaContentBaseDesc> implementsInterfaces) {
		this.implementsInterfaces = implementsInterfaces;
	}
	public List<DqJavaContentDesc> getFields() {
		return fields;
	}
	public void setFields(List<DqJavaContentDesc> fields) {
		this.fields = fields;
	}
	public List<DqJavaContentDesc> getMethods() {
		return methods;
	}
	public void setMethods(List<DqJavaContentDesc> methods) {
		this.methods = methods;
	}
	
}
