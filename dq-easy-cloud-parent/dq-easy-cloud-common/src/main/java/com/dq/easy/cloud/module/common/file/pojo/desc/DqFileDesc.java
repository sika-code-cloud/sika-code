package com.dq.easy.cloud.module.common.file.pojo.desc;

import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.file.constant.error.DqFileErrorCodeEnum;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.module.exception.bo.DqBaseBusinessException;

/**
 * 文件描述
 * 
 * @author daiqi
 * @date 2018年3月23日 下午11:37:33
 */
public class DqFileDesc {
	/** 文件目录完整路径 */
	private String fileDirectoryFullPath;
	/** 文件完整路径 */
	private String fileFullPath;
	/** 文件名称 */
	private String fileName;
	/** 文件后缀 */
	private String fileSuffix;
	/** 项目名称 */
	private String projectName;
	/** 文件覆盖开关---true不论文件是否存在都覆盖---false--文件存在不覆盖 */
	private boolean coverSwitch;

	public String getFileDirectoryFullPath() {
		return fileDirectoryFullPath;
	}

	public void setFileDirectoryFullPath(String fileDirectoryFullPath) {
		this.fileDirectoryFullPath = fileDirectoryFullPath;
	}

	public String getFileFullPath() {
		return fileFullPath;
	}

	public void setFileFullPath(String fileFullPath) {
		this.fileFullPath = fileFullPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public boolean isCoverSwitch() {
		return coverSwitch;
	}

	public void setCoverSwitch(boolean coverSwitch) {
		this.coverSwitch = coverSwitch;
	}

	/**
	 * 
	 * <p>
	 * 构建文件完整路径
	 * </p>
	 *
	 * <pre></pre>
	 *
	 * @return
	 *
	 * 		author daiqi 创建时间 2018年3月24日 上午12:29:31
	 */
	public void initFileDescData() {
		if (DqStringUtils.isEmpty(getFileFullPath())) {
			StringBuilder fileFullPathBuild = DqStringUtils.newStringBuilderDefault();
			fileFullPathBuild.append(getFileDirectoryFullPath()).append(DqSymbol.BACK_SLASH);
			fileFullPathBuild.append(getFileName()).append(DqSymbol.STOP).append(getFileSuffix());
			setFileFullPath(fileFullPathBuild.toString());
		}
	}

	/**
	 * 
	 * <p>
	 * 校验文件描述数据
	 * </p>
	 *
	 * <pre></pre>
	 *
	 * @return
	 *
	 * 		author daiqi 创建时间 2018年3月24日 上午12:20:45
	 */
	public void verifyFileDescData() {
		if (DqBaseUtils.isNull(getFileDirectoryFullPath())) {
			throw new DqBaseBusinessException(DqFileErrorCodeEnum.FILE_DIRECTORY_FULL_PATH_CANT_EMPTY);
		}
		if (DqBaseUtils.isNull(getFileFullPath())) {
			throw new DqBaseBusinessException(DqFileErrorCodeEnum.FILE_FULL_PATH_CANT_EMPTY);
		}
		if (DqBaseUtils.isNull(getFileName())) {
			throw new DqBaseBusinessException(DqFileErrorCodeEnum.FILE_NAME_CANT_EMPTY);
		}
		if (DqBaseUtils.isNull(getFileSuffix())) {
			throw new DqBaseBusinessException(DqFileErrorCodeEnum.FILE_SUFFIX_CANT_EMPTY);
		}
	}
}
