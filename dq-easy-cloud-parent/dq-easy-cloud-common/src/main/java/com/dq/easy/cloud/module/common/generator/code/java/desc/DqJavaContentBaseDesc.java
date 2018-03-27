package com.dq.easy.cloud.module.common.generator.code.java.desc;

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
public class DqJavaContentBaseDesc extends DqFileContentBaseDesc {
	
	/** java文件所在包名 */
	private String packageName;
	/** 类的类型简称 */
	private String simpleClassType;
	/** 完整的类类型 */
	private String fullClassType;
	/** 名称---java类描述就是类名---属性就是属性名---方法就是方法名称 */
	private String name;
	
	public DqJavaContentBaseDesc() {
		super();
	}

	public DqJavaContentBaseDesc(DqGenerateRule generateRule) {
		super(generateRule);
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
	public void buildFullClassType() {
		boolean isBuild = DqStringUtils.isEmpty(fullClassType) 
				&& DqStringUtils.isNotEmpty(packageName) 
				&& DqStringUtils.isNotEmpty(simpleClassType); 
		if (isBuild) {
			StringBuilder build = DqStringUtils.newStringBuilderDefault();
			build.append(packageName).append(DqSymbol.STOP).append(simpleClassType);
			setFullClassType(build.toString());
		}
	}
	@Override
	public DqFileContentBaseDesc buildDataByDatabaseSources(DqDatabaseDataSources databaseDataSources) {
		return new DqJavaContentBaseDesc();
	}
	
}
