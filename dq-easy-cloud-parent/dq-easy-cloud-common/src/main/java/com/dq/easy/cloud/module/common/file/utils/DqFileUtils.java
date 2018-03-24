package com.dq.easy.cloud.module.common.file.utils;

import java.io.File;

import com.dq.easy.cloud.module.basic.constant.error.DqBaseErrorCodeEnum;
import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.file.constant.error.DqFileErrorCodeEnum;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileDesc;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.module.exception.bo.DqBaseBusinessException;


/**
 * 文件工具类
 * @author daiqi
 * @date 2018年3月23日 下午11:49:33
 */
public class DqFileUtils {
	
	
	/**
	 * 
	 * <p>创建目录</p>
	 *
	 * <pre>
	 * 目录不存在则创建
	 * </pre>
	 *
	 * @param filePath
	 *
	 * author daiqi
	 * 创建时间  2018年3月24日 上午12:02:43
	 */
	public static File mkdirs(String directoryPath) {
		if (DqStringUtils.isEmpty(directoryPath)) {
			throw new DqBaseBusinessException(DqFileErrorCodeEnum.FILE_DIRECTORY_FULL_PATH_CANT_EMPTY);
		}
		File directory = new File(directoryPath);
		if (directory.isDirectory() && !directory.exists()) {
			directory.mkdirs();
		}
		return directory;
	}
	
	/**
	 * 
	 * <p>创建文件</p>
	 *
	 * <pre></pre>
	 *
	 * @param filePath
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年3月24日 上午12:08:24
	 */
	public static File newFile(String filePath) {
		if (DqStringUtils.isEmpty(filePath)) {
			throw new DqBaseBusinessException(DqFileErrorCodeEnum.FILE_FULL_PATH_CANT_EMPTY);
		}
		return new File(filePath);
	}
	
}
