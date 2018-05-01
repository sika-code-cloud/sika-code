package com.easy.cloud.core.common.file.pojo.desc;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.file.constant.error.EcFileErrorCodeEnum;
import com.easy.cloud.core.common.file.utils.EcFileUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;

/**
 * 文件描述
 * 
 * @author daiqi
 * @date 2018年3月23日 下午11:37:33
 */
public class EcFileDesc {
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
		if (EcStringUtils.isEmpty(getFileFullPath())) {
			setFileFullPath(EcFileUtils.getFullFilePath(this));
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
		if (EcBaseUtils.isNull(getFileDirectoryFullPath())) {
			throw new EcBaseBusinessException(EcFileErrorCodeEnum.FILE_DIRECTORY_FULL_PATH_CANT_EMPTY);
		}
		if (EcBaseUtils.isNull(getFileFullPath())) {
			throw new EcBaseBusinessException(EcFileErrorCodeEnum.FILE_FULL_PATH_CANT_EMPTY);
		}
		if (EcBaseUtils.isNull(getFileName())) {
			throw new EcBaseBusinessException(EcFileErrorCodeEnum.FILE_NAME_CANT_EMPTY);
		}
		if (EcBaseUtils.isNull(getFileSuffix())) {
			throw new EcBaseBusinessException(EcFileErrorCodeEnum.FILE_SUFFIX_CANT_EMPTY);
		}
	}
}
