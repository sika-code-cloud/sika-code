package com.dq.easy.cloud.module.common.generator.code.java.desc;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * java文件类容基础描述类
 * 
 * @author daiqi
 * @date 2018年3月24日 上午9:22:56
 */
public abstract class DqJavaContentBaseDesc extends DqFileContentBaseDesc {

	/** java文件所在包名 */
	private String packageName;
	/** 类的类型简称 */
	private String simpleClassType;
	/** 完整的类类型 */
	private String fullClassType;
	/** 名称---java类描述就是类名---属性就是属性名---方法就是方法名称 */
	private String name;
	/** 泛型列表 */
	private List<DqJavaGenericityContentDesc> genericitys;

	public DqJavaContentBaseDesc() {
		super();
	}

	public DqJavaContentBaseDesc(DqGenerateRule generateRule) {
		super(generateRule);
	}
	/** 获取java类内容的唯一标识 */
	public abstract String getJavaContentSign();
	public void addGenericity(DqJavaGenericityContentDesc genericity) {
		if (DqCollectionsUtils.isEmpty(getGenericitys())) {
			this.genericitys = new ArrayList<>();
		}
		getGenericitys().add(genericity);
	}

	public void addGenericitys(List<DqJavaGenericityContentDesc> genericitys) {
		if (DqCollectionsUtils.isEmpty(getGenericitys())) {
			this.genericitys = new ArrayList<>();
		}
		getGenericitys().addAll(genericitys);
	}

	/** 获取简称类类型的完整字符串包括泛型参数 */
	public String getSimpleClassTypeFullStr() {
		StringBuilder build = DqStringUtils.newStringBuilderDefault();
		build.append(this.getSimpleClassType());
		String genericitysStr = getGenericitysStr();
		if (DqStringUtils.isNotEmpty(genericitysStr)) {
			build.append(genericitysStr);
		}
		return build.toString();
	}

	/** 获取泛型列表字符串 */
	public String getGenericitysStr() {
		StringBuilder genericitysBuild = DqStringUtils.newStringBuilderDefault();
		if (DqCollectionsUtils.isNotEmpty(getGenericitys())) {
			genericitysBuild.append(DqSymbol.LESS_THAN);
			for (int i = 0; i < getGenericitys().size(); ++i) {
				genericitysBuild.append(getGenericitys().get(i).getSimpleClassType());
				if (i < getGenericitys().size() - 1) {
					genericitysBuild.append(DqSymbol.COMMA);
					genericitysBuild.append(DqSymbol.EMPTY);
				}
			}
			genericitysBuild.append(DqSymbol.GREATER_THAN);
		}
		return genericitysBuild.toString();
	}

	public void buildFullClassType() {
		boolean isBuild = DqStringUtils.isEmpty(fullClassType) && DqStringUtils.isNotEmpty(packageName)
				&& DqStringUtils.isNotEmpty(simpleClassType);
		if (isBuild) {
			StringBuilder build = DqStringUtils.newStringBuilderDefault();
			build.append(packageName).append(DqSymbol.STOP).append(simpleClassType);
			setFullClassType(build.toString());
		}
	}

	@Override
	public DqFileContentBaseDesc buildDataByDatabaseSources(DqDatabaseDataSources databaseDataSources) {
		return null;
	}

	public String getSimpleClassType() {
		return simpleClassType;
	}

	public void setSimpleClassType(String simpleClassType) {
		this.simpleClassType = simpleClassType;
	}

	public String getFullClassType() {
		return fullClassType;
	}

	public void setFullClassType(String fullClassType) {
		this.fullClassType = fullClassType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<DqJavaGenericityContentDesc> getGenericitys() {
		return genericitys;
	}

	public void setGenericitys(List<DqJavaGenericityContentDesc> genericitys) {
		this.genericitys = genericitys;
	}
	
}
