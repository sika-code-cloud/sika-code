package com.easy.cloud.core.common.generator.code.java.desc;

import com.easy.cloud.core.common.file.pojo.desc.EcFileDesc;
import com.easy.cloud.core.common.file.utils.EcFileUtils;
import com.easy.cloud.core.common.generator.code.base.config.EcCodeGenerateConfig;
import com.easy.cloud.core.common.generator.code.base.constant.error.EcCodeGenerateErrorCodeEnum;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;

/**
 * 
 * <p>
 * java文件描述类
 * </p>
 *
 * @author daiqi 创建时间 2018年3月26日 下午2:29:40
 */
public class EcJavaFileDesc extends EcFileDesc {
	/** 项目完整路径 */
	private String projectFullPath;
	/** 项目源代码的相对路径 */
	private String sourceCodeRelativePath;
	/** 文件所在包名的相对路径 */
	private String packageRelativePath;
	
	public EcJavaFileDesc() {
		super();
	}

	public EcJavaFileDesc(String projectName, String sourceCodeRelativePath,
			String packageRelativePath, String fileName, String fileSuffix) {
		setProjectName(projectName);
		this.sourceCodeRelativePath = sourceCodeRelativePath;
		this.packageRelativePath = packageRelativePath;
		setFileName(fileName);
		setFileSuffix(fileSuffix);
	}

	public String getProjectFullPath() {
		return projectFullPath;
	}

	public void setProjectFullPath(String projectFullPath) {
		this.projectFullPath = projectFullPath;
	}

	public String getSourceCodeRelativePath() {
		return sourceCodeRelativePath;
	}

	public void setSourceCodeRelativePath(String sourceCodeRelativePath) {
		this.sourceCodeRelativePath = sourceCodeRelativePath;
	}


	public String getPackageRelativePath() {
		return packageRelativePath;
	}

	public void setPackageRelativePath(String packageRelativePath) {
		this.packageRelativePath = packageRelativePath;
	}

	@Override
	public void initFileDescData() {
		String projectFullPath = EcFileUtils.getTargetProjectPath(null, getProjectName(), EcCodeGenerateConfig.getNeedFilterDirectoryName());
		if (EcStringUtils.isEmpty(projectFullPath)) {
			throw new EcBaseBusinessException(EcCodeGenerateErrorCodeEnum.GET_PROJECT_PATH_FAIL);
		}
		StringBuilder targetPathBuild = EcStringUtils.newStringBuilderDefault();
		targetPathBuild.append(projectFullPath).append(EcSymbol.BACK_SLASH);
		targetPathBuild.append(sourceCodeRelativePath).append(EcSymbol.BACK_SLASH);
		targetPathBuild.append(packageRelativePath).append(EcSymbol.BACK_SLASH);
//		设置文件目录完整路径
		setFileDirectoryFullPath(targetPathBuild.toString());
//		设置文件完整路径
		targetPathBuild.append(getFileName()).append(EcSymbol.STOP).append(getFileSuffix());
		setFileFullPath(targetPathBuild.toString());
	}
	
}
