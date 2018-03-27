package com.dq.easy.cloud.module.common.file.utils;

import java.io.File;
import java.util.Map;

import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.file.constant.error.DqFileErrorCodeEnum;
import com.dq.easy.cloud.module.common.generator.code.base.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.module.exception.bo.DqBaseBusinessException;

/**
 * 文件工具类
 * 
 * @author daiqi
 * @date 2018年3月23日 下午11:49:33
 */
public class DqFileUtils {

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
		if (DqStringUtils.isEmpty(directoryPath)) {
			throw new DqBaseBusinessException(DqFileErrorCodeEnum.FILE_DIRECTORY_FULL_PATH_CANT_EMPTY);
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
		if (DqStringUtils.isEmpty(filePath)) {
			throw new DqBaseBusinessException(DqFileErrorCodeEnum.FILE_FULL_PATH_CANT_EMPTY);
		}
		return new File(filePath);
	}

	/**
	 * 
	 * <p>
	 * 根据项目名称获取目标项目的路径
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param currentPath
	 *            : String : 当前路径不传则为System.getProperty("user.dir")
	 * @param targetProjectName
	 *            : String : 目标的项目名称
	 * @return
	 * @author daiqi 创建时间 2018年3月26日 下午3:23:53
	 */
	public static String getTargetProjectPath(String currentPath, String targetProjectName, Map<String, Object> needFilterFileDic) {
		if (DqStringUtils.isEmpty(currentPath)) {
			currentPath = System.getProperty("user.dir");
		}
		File curentFile = new File(currentPath);
		if (curentFile.getParentFile() == null) {
			return null;
		}
		curentFile = curentFile.getParentFile();
		File[] fileList = curentFile.listFiles();
		for (File file : fileList) {
			if (file.isDirectory()  && needFilterFileDic.get(file.getName()) == null) {
				File targetFile = getTargetFile(file, targetProjectName, needFilterFileDic);
				if (DqBaseUtils.isNull(targetFile)) {
					continue;
				}
				if (DqStringUtils.equals(targetFile.getName(), targetProjectName)) {
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
	 * @author daiqi
	 * 创建时间    2018年3月26日 下午6:05:42
	 */
	private static File getTargetFile(File curentFile, String targetDirectoryName, Map<String, Object> needFilterFileDic) {
		File[] files = curentFile.listFiles();
		if (files == null) {
			return null;
		}
		for (File f : files) {
			if (f.isDirectory() && needFilterFileDic.get(f.getName()) == null) {
				if (DqStringUtils.equals(targetDirectoryName, f.getName())) {
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
	
	public static void main(String[] args) throws Exception {
		 System.out.println(getTargetProjectPath(null,
		 "dq-easy-cloud-pay-common", DqCodeGenerateConfig.getNeedFilterDirectoryName()));
//		System.out.println(getTargetFile(new File("C:\\Users\\THINK\\git\\"), "dq-easy-cloud-config-client"));
//		List<String> list = new ArrayList<>();
//		findFiles("C:\\Users\\THINK\\git\\", "dq-easy-cloud-config-client", list);
//		System.out.println(list.size());
	}
}
