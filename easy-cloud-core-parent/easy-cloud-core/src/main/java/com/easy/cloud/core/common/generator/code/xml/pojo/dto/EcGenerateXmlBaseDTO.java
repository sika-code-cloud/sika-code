package com.easy.cloud.core.common.generator.code.xml.pojo.dto;

import com.easy.cloud.core.common.generator.code.base.pojo.dto.EcGenerateBaseDTO;

/**
 * 
 * <p>
 * xml基础数据传输对象
 * </p>
 *
 * @author daiqi 创建时间 2018年3月29日 上午11:41:29
 */
public class EcGenerateXmlBaseDTO extends EcGenerateBaseDTO {
	/** 源代码相对路径 */
	private String sourceCodeRelativePath;
	/** 子路径 */
	private String subPath;

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
