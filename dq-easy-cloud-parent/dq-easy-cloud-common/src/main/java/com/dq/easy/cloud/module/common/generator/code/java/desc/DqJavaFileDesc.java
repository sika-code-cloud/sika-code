package com.dq.easy.cloud.module.common.generator.code.java.desc;

import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileDesc;
import com.dq.easy.cloud.module.common.file.utils.DqFileUtils;
import com.dq.easy.cloud.module.common.generator.code.base.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.module.common.generator.code.base.constant.error.DqCodeGenerateErrorCodeEnum;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.module.exception.bo.DqBaseBusinessException;

/**
 * 
 * <p>
 * java文件描述类
 * </p>
 *
 * @author daiqi 创建时间 2018年3月26日 下午2:29:40
 */
public class DqJavaFileDesc extends DqFileDesc {
	/** 项目名称 */
	private String projectName;
	/** 项目完整路径 */
	private String projectFullPath;
	/** 项目源代码的相对路径 */
	private String sourceCodeRelativePath;
	/** 文件所在包名的相对路径 */
	private String packageRelativePath;
	
	public DqJavaFileDesc() {
		super();
	}

	public DqJavaFileDesc(String projectName, String sourceCodeRelativePath,
			String packageRelativePath, String fileName, String fileSuffix) {
		this.projectName = projectName;
		this.sourceCodeRelativePath = sourceCodeRelativePath;
		this.packageRelativePath = packageRelativePath;
		setFileName(fileName);
		setFileSuffix(fileSuffix);
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
		String projectFullPath = DqFileUtils.getTargetProjectPath(null, projectName, DqCodeGenerateConfig.getNeedFilterDirectoryName());
		if (DqStringUtils.isEmpty(projectFullPath)) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.GET_PROJECT_PATH_FAIL);
		}
		StringBuilder targetPathBuild = DqStringUtils.newStringBuilderDefault();
		targetPathBuild.append(projectFullPath).append(DqSymbol.BACK_SLASH);
		targetPathBuild.append(sourceCodeRelativePath).append(DqSymbol.BACK_SLASH);
		targetPathBuild.append(packageRelativePath).append(DqSymbol.BACK_SLASH);
//		设置文件目录完整路径
		setFileDirectoryFullPath(targetPathBuild.toString());
//		设置文件完整路径
		targetPathBuild.append(getFileName()).append(DqSymbol.STOP).append(getFileSuffix());
		setFileFullPath(targetPathBuild.toString());
	}
	
}
