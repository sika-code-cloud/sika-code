package com.easy.cloud.core.generator.code.xml.desc;

import com.easy.cloud.core.common.file.pojo.desc.EcFileDesc;
import com.easy.cloud.core.common.file.utils.EcFileUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.generator.code.base.config.EcCodeGenerateConfig;
import com.easy.cloud.core.generator.code.base.constant.error.EcCodeGenerateErrorCodeEnum;

/**
 * 
 * <p>
 * xml文件描述类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月29日 下午3:16:49
 */
public class EcXmlFileDesc extends EcFileDesc{
	/** 项目源代码的相对路径 */
	private String sourceCodeRelativePath;
	/** 子路径 */
	private String subPath;
	
	@Override
	public void initFileDescData() {
		String projectFullPath = EcFileUtils.getTargetProjectPath(null, getProjectName(), EcCodeGenerateConfig.getNeedFilterDirectoryName());
		if (EcStringUtils.isEmpty(projectFullPath)) {
			throw new EcBaseBusinessException(EcCodeGenerateErrorCodeEnum.GET_PROJECT_PATH_FAIL);
		}
		StringBuilder targetPathBuild = EcStringUtils.newStringBuilder();
		targetPathBuild.append(projectFullPath).append(EcSymbol.BACK_SLASH);
		targetPathBuild.append(sourceCodeRelativePath).append(EcSymbol.BACK_SLASH);
		if (EcStringUtils.isNotEmpty(this.getSubPath())) {
			targetPathBuild.append(getSubPath()).append(EcSymbol.BACK_SLASH);
		}
//		设置文件目录完整路径
		setFileDirectoryFullPath(targetPathBuild.toString());
//		设置文件完整路径
		targetPathBuild.append(getFileName()).append(EcSymbol.STOP).append(getFileSuffix());
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
