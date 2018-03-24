package com.dq.easy.cloud.module.common.file.pojo.bo;

import java.io.File;

import com.dq.easy.cloud.module.basic.constant.error.DqBaseErrorCodeEnum;
import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.file.constant.error.DqFileErrorCodeEnum;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileDesc;
import com.dq.easy.cloud.module.common.file.utils.DqFileUtils;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.module.exception.bo.DqBaseBusinessException;

/**
 * 文件业务逻辑处理类
 * @author daiqi
 * @date 2018年3月23日 下午11:44:29
 */
public class DqFileBO {
	private DqFileDesc fileDescDTO;

	public DqFileBO(DqFileDesc fileDescDTO) {
		this.fileDescDTO = fileDescDTO;
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
		initFileDescData();
//		校验
		verifyFileDescData();
//		创建目录
		DqFileUtils.mkdirs(fileDescDTO.getFileDirectoryFullPath());
//		创建文件
		return DqFileUtils.newFile(fileDescDTO.getFileFullPath());
	}
	
	/**
	 * 
	 * <p>数据初始化</p>
	 *
	 * <pre></pre>
	 *
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年3月24日 上午12:20:45
	 */
	public DqFileBO initFileDescData() {
		buildFileFullPath();
		return this;
	}
	
	/**
	 * 
	 * <p>校验文件描述数据</p>
	 *
	 * <pre></pre>
	 *
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年3月24日 上午12:20:45
	 */
	public DqFileBO verifyFileDescData () {
		if (DqBaseUtils.isNull(fileDescDTO)) {
			throw new DqBaseBusinessException(DqBaseErrorCodeEnum.DTO_OBJ_CANT_NULL);
		}
		if (DqBaseUtils.isNull(fileDescDTO.getFileDirectoryFullPath())) {
			throw new DqBaseBusinessException(DqFileErrorCodeEnum.FILE_DIRECTORY_FULL_PATH_CANT_EMPTY);
		}
		if (DqBaseUtils.isNull(fileDescDTO.getFileFullPath())) {
			throw new DqBaseBusinessException(DqFileErrorCodeEnum.FILE_FULL_PATH_CANT_EMPTY);
		}
		if (DqBaseUtils.isNull(fileDescDTO.getFileName())) {
			throw new DqBaseBusinessException(DqFileErrorCodeEnum.FILE_NAME_CANT_EMPTY);
		}
		if (DqBaseUtils.isNull(fileDescDTO.getFileSuffix())) {
			throw new DqBaseBusinessException(DqFileErrorCodeEnum.FILE_SUFFIX_CANT_EMPTY);
		}
		return this;
	}
	
	/**
	 * 
	 * <p>构建文件完整路径</p>
	 *
	 * <pre></pre>
	 *
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年3月24日 上午12:29:31
	 */
	public DqFileBO buildFileFullPath() {
		if (DqStringUtils.isEmpty(fileDescDTO.getFileFullPath())) {
			StringBuilder fileFullPathBuild = DqStringUtils.newStringBuilderDefault();
			fileFullPathBuild.append(fileDescDTO.getFileDirectoryFullPath()).append(DqSymbol.BACK_SLASH);
			fileFullPathBuild.append(fileDescDTO.getFileName()).append(DqSymbol.STOP).append(fileDescDTO.getFileSuffix());
			fileDescDTO.setFileFullPath(fileFullPathBuild.toString());
		}
		return this;
	}
	
}
