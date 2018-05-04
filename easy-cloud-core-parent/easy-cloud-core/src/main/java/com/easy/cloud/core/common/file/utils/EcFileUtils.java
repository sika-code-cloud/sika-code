package com.easy.cloud.core.common.file.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.file.constant.EcFileConstant;
import com.easy.cloud.core.common.file.constant.error.EcFileErrorCodeEnum;
import com.easy.cloud.core.common.file.pojo.desc.EcFileDesc;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;

/**
 * 文件工具类
 * 
 * @author daiqi
 * @date 2018年3月23日 下午11:49:33
 */
public class EcFileUtils {

	/**
	 * 
	 * <p>
	 * 创建目录
	 * </p>
	 *
	 * <pre>
	 * 目录不存在则创建
	 * </pre>
	 *
	 * @param filePath
	 *
	 *            author daiqi 创建时间 2018年3月24日 上午12:02:43
	 */
	public static File mkdirs(String directoryPath) {
		if (EcStringUtils.isEmpty(directoryPath)) {
			throw new EcBaseBusinessException(EcFileErrorCodeEnum.FILE_DIRECTORY_FULL_PATH_CANT_EMPTY);
		}
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		return directory;
	}

	/**
	 * 
	 * <p>
	 * 创建文件
	 * </p>
	 *
	 * <pre></pre>
	 *
	 * @param filePath
	 * @return
	 *
	 * 		author daiqi 创建时间 2018年3月24日 上午12:08:24
	 */
	public static File newFile(String filePath) {
		if (EcStringUtils.isEmpty(filePath)) {
			throw new EcBaseBusinessException(EcFileErrorCodeEnum.FILE_FULL_PATH_CANT_EMPTY);
		}
		return new File(filePath);
	}

	/**
	 * 
	 * <p>
	 * 根据项目名称获取目标项目的路径
	 * </p>
	 *
	 * @param currentPath
	 *            : String : 当前路径不传则为System.getProperty("user.dir")
	 * @param targetProjectName
	 *            : String : 目标的项目名称
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 下午3:23:53
	 */
	public static String getTargetProjectPath(String currentPath, String targetProjectName,
			Map<String, Object> needFilterFileDic) {
		if (EcStringUtils.isEmpty(currentPath)) {
			currentPath = EcFileConstant.USER_DIR;
		}
		File curentFile = new File(currentPath);
		if (curentFile.getParentFile() == null) {
			return null;
		}
		curentFile = curentFile.getParentFile();
		File[] fileList = curentFile.listFiles();
		for (File file : fileList) {
			if (file.isDirectory() && needFilterFileDic.get(file.getName()) == null) {
				File targetFile = getTargetFile(file, targetProjectName, needFilterFileDic);
				if (EcBaseUtils.isNull(targetFile)) {
					continue;
				}
				if (EcStringUtils.equals(targetFile.getName(), targetProjectName)) {
					return targetFile.getPath();
				}
			}
		}
		return getTargetProjectPath(curentFile.getPath(), targetProjectName, needFilterFileDic);
	}

	/**
	 * 
	 * <p>
	 * 获取目标文件
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param curentFile
	 * @param targetDirectoryName
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 下午6:05:42
	 */
	private static File getTargetFile(File curentFile, String targetDirectoryName,
			Map<String, Object> needFilterFileDic) {
		File[] files = curentFile.listFiles();
		if (files == null) {
			return null;
		}
		for (File f : files) {
			if (f.isDirectory() && needFilterFileDic.get(f.getName()) == null) {
				if (EcStringUtils.equals(targetDirectoryName, f.getName())) {
					return f;
				} else {
					getTargetFile(f, targetDirectoryName, needFilterFileDic);
				}
			} else {
				continue;
			}
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * 根据描述文件获取完整路径
	 * </p>
	 *
	 * @param fileDesc
	 * @return
	 * @author daiqi 创建时间 2018年3月31日 上午9:42:23
	 */
	public static String getFullFilePath(EcFileDesc fileDesc) {
		StringBuilder fileFullPathBuild = EcStringUtils.newStringBuilder();
		fileFullPathBuild.append(fileDesc.getFileDirectoryFullPath()).append(EcSymbol.BACK_SLASH);
		fileFullPathBuild.append(fileDesc.getFileName()).append(EcSymbol.STOP).append(fileDesc.getFileSuffix());
		return fileFullPathBuild.toString();
	}

	/**
	 * 
	 * <p>
	 * 根据fileName获取指定文件夹下该文件名对应的文件列表
	 * </p>
	 *
	 * @param folder : File : 文件夹
	 * @param findFileName : String : 需要查找的文件名称
	 * @return
	 * @author daiqi 创建时间 2018年3月31日 上午10:40:56
	 */
	public static List<File> getFilesByFileName(File folder, final String findFileName) {// 递归查找包含关键字的文件

		File[] subFolders = folder.listFiles(new FileFilter() {// 运用内部匿名类获得文件
			@Override
			public boolean accept(File pathname) {// 实现FileFilter类的accept方法
				if (pathname.isDirectory()
						|| (pathname.isFile() && EcStringUtils.equals(findFileName, pathname.getName()))) {
					return true;
				}
				return false;
			}
		});

		List<File> result = new ArrayList<File>();// 声明一个集合
		for (int i = 0; i < subFolders.length; i++) {// 循环显示文件夹或文件
			if (subFolders[i].isFile()) {// 如果是文件则将文件添加到结果列表中
				result.add(subFolders[i]);
			} else {// 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
				List<File> foldResult = getFilesByFileName(subFolders[i], findFileName);
				for (int j = 0; j < foldResult.size(); j++) {// 循环显示文件
					result.add(foldResult.get(j));// 文件保存到集合中
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * <p>
	 * 获取指定文件名的文件
	 * </p>
	 *
	 *
	 * @param folder : 
	 * @param findFileName
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月31日 上午10:45:28
	 */
	public static File getFileByFileName(String folderFile, final String findFileName) {
		File folder = new File(folderFile);
		if (!folder.exists()) {
			throw new RuntimeException("文件夹不存在");
		}
		if (!folder.isDirectory()) {
			throw new RuntimeException("不是一个目录");
		}
		List<File> files = getFilesByFileName(folder, findFileName);
		if (EcCollectionsUtils.isEmpty(files)) {
			return null;
		}
		return files.get(0);
	}
}
