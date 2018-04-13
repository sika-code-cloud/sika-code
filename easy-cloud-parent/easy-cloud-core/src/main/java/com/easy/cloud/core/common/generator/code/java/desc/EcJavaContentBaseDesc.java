package com.easy.cloud.core.common.generator.code.java.desc;

import java.util.ArrayList;
import java.util.List;

import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.file.pojo.desc.EcFileContentBaseDesc;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.base.sources.database.EcDatabaseDataSources;
import com.easy.cloud.core.common.generator.code.base.utils.EcCodeGenerateUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * java文件类容基础描述类
 * 
 * @author daiqi
 * @date 2018年3月24日 上午9:22:56
 */
public abstract class EcJavaContentBaseDesc extends EcFileContentBaseDesc {

	/** java文件所在包名 */
	private String packageName;
	/** 类的类型简称 */
	private String simpleClassType;
	/** 完整的类类型 */
	private String fullClassType;
	/** 名称---java类描述就是类名---属性就是属性名---方法就是方法名称 */
	private String name;
	/** 泛型列表 */
	private List<EcJavaGenericityContentDesc> genericitys;

	public EcJavaContentBaseDesc() {
		super();
	}

	public EcJavaContentBaseDesc(EcGenerateRule generateRule) {
		super(generateRule);
	}
	/** 获取java类内容的唯一标识 */
	public abstract String getJavaContentSign();
	public void addGenericity(EcJavaGenericityContentDesc genericity) {
		if (EcCollectionsUtils.isEmpty(getGenericitys())) {
			this.genericitys = new ArrayList<>();
		}
		getGenericitys().add(genericity);
	}

	public void addGenericitys(List<EcJavaGenericityContentDesc> genericitys) {
		if (EcCollectionsUtils.isEmpty(getGenericitys())) {
			this.genericitys = new ArrayList<>();
		}
		getGenericitys().addAll(genericitys);
	}

	/** 获取简称类类型的完整字符串包括泛型参数 */
	public String getSimpleClassTypeFullStr() {
		StringBuilder build = EcStringUtils.newStringBuilderDefault();
		build.append(this.getSimpleClassType());
		String genericitysStr = getGenericitysStr();
		if (EcStringUtils.isNotEmpty(genericitysStr)) {
			build.append(genericitysStr);
		}
		return build.toString();
	}

	/** 获取泛型列表字符串 */
	public String getGenericitysStr() {
		StringBuilder genericitysBuild = EcStringUtils.newStringBuilderDefault();
		if (EcCollectionsUtils.isNotEmpty(getGenericitys())) {
			genericitysBuild.append(EcSymbol.LESS_THAN);
			for (int i = 0; i < getGenericitys().size(); ++i) {
				genericitysBuild.append(getGenericitys().get(i).getSimpleClassType());
				if (i < getGenericitys().size() - 1) {
					genericitysBuild.append(EcSymbol.COMMA);
					genericitysBuild.append(EcSymbol.EMPTY);
				}
			}
			genericitysBuild.append(EcSymbol.GREATER_THAN);
		}
		return genericitysBuild.toString();
	}

	public void buildFullClassType() {
		boolean isBuild = EcStringUtils.isEmpty(fullClassType) && EcStringUtils.isNotEmpty(packageName)
				&& EcStringUtils.isNotEmpty(simpleClassType);
		if (isBuild) {
			setFullClassType(EcCodeGenerateUtils.getFullClassType(packageName, simpleClassType));
		}
	}

	@Override
	public EcFileContentBaseDesc buildDataByDatabaseSources(EcDatabaseDataSources databaseDataSources) {
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

	public List<EcJavaGenericityContentDesc> getGenericitys() {
		return genericitys;
	}

	public void setGenericitys(List<EcJavaGenericityContentDesc> genericitys) {
		this.genericitys = genericitys;
	}
	
}
