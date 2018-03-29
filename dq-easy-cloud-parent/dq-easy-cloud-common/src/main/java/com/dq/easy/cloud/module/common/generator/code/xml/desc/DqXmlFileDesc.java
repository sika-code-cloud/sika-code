package com.dq.easy.cloud.module.common.generator.code.xml.desc;

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
 * xml文件描述类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月29日 下午3:16:49
 */
public class DqXmlFileDesc extends DqFileDesc{
	/** 项目源代码的相对路径 */
	private String sourceCodeRelativePath;
	/** 子路径 */
	private String subPath;
	
	@Override
	public void initFileDescData() {
		String projectFullPath = DqFileUtils.getTargetProjectPath(null, getProjectName(), DqCodeGenerateConfig.getNeedFilterDirectoryName());
		if (DqStringUtils.isEmpty(projectFullPath)) {
			throw new DqBaseBusinessException(DqCodeGenerateErrorCodeEnum.GET_PROJECT_PATH_FAIL);
		}
		StringBuilder targetPathBuild = DqStringUtils.newStringBuilderDefault();
		targetPathBuild.append(projectFullPath).append(DqSymbol.BACK_SLASH);
		targetPathBuild.append(sourceCodeRelativePath).append(DqSymbol.BACK_SLASH);
		if (DqStringUtils.isNotEmpty(this.getSubPath())) {
			targetPathBuild.append(getSubPath()).append(DqSymbol.BACK_SLASH);
		}
//		设置文件目录完整路径
		setFileDirectoryFullPath(targetPathBuild.toString());
//		设置文件完整路径
		targetPathBuild.append(getFileName()).append(DqSymbol.STOP).append(getFileSuffix());
		setFileFullPath(targetPathBuild.toString());
	}
	public String getSourceCodeRelativePath() {
		return sourceCodeRelativePath;
	}

	public void setSourceCodeRelativePath(String sourceCodeRelativePath) {
		this.sourceCodeRelativePath = sourceCodeRelativePath;
	}
	public String getSubPath() {
		return subPath;
	}
	public void setSubPath(String subPath) {
		this.subPath = subPath;
	}
}
