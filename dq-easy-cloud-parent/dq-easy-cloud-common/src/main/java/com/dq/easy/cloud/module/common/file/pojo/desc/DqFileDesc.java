package com.dq.easy.cloud.module.common.file.pojo.desc;

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

}
