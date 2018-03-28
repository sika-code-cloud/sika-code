package com.dq.easy.cloud.module.common.file.pojo.bo;

import java.io.File;

import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileDesc;
import com.dq.easy.cloud.module.common.file.utils.DqFileUtils;

/**
 * 文件业务逻辑处理类
 * @author daiqi
 * @date 2018年3月23日 下午11:44:29
 */
public class DqFileBO {
	private DqFileDesc fileDesc;

	public DqFileBO(DqFileDesc fileDesc) {
		this.fileDesc = fileDesc;
	}

	/**
	 * 
	 * <p>创建文件</p>
	 *
	 * <pre></pre>
	 *
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年3月24日 上午12:20:45
	 */
	public File newFile() {
//		1 初始化
		fileDesc.initFileDescData();
//		校验
		fileDesc.verifyFileDescData();
//		创建目录
		DqFileUtils.mkdirs(fileDesc.getFileDirectoryFullPath());
//		根据路径获取文件
		File newFile = DqFileUtils.newFile(fileDesc.getFileFullPath());
		if (DqBaseUtils.isNull(newFile)) {
			return newFile;
		}
		if (newFile.exists() && !fileDesc.isCoverSwitch()) {
			return null;
		}
		return newFile;
	}
	
}
