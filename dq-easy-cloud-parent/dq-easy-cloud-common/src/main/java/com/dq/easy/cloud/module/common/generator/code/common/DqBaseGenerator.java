package com.dq.easy.cloud.module.common.generator.code.common;

import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileContentDesc;
import com.dq.easy.cloud.module.common.generator.code.common.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.sources.DqBaseDataSources;

/**
 * 基础生成者
 * @author daiqi
 * @date 2018年3月24日 上午10:57:46
 */
public class DqBaseGenerator {
	private DqBaseDataSources dataSources;
	private DqFileContentDesc fileContentDesc;
	private DqTemplateDesc templateDesc;
	public DqBaseGenerator(DqBaseDataSources dataSources, DqFileContentDesc fileContentDesc) {
		this.dataSources = dataSources;
		this.fileContentDesc = fileContentDesc;
	}
	public DqBaseDataSources getDataSources() {
		return dataSources;
	}
	public void setDataSources(DqBaseDataSources dataSources) {
		this.dataSources = dataSources;
	}
	public DqFileContentDesc getFileContentDesc() {
		return fileContentDesc;
	}
	public void setFileContentDesc(DqFileContentDesc fileContentDesc) {
		this.fileContentDesc = fileContentDesc;
	}
	public DqTemplateDesc getTemplateDesc() {
		return templateDesc;
	}
	public void setTemplateDesc(DqTemplateDesc templateDesc) {
		this.templateDesc = templateDesc;
	}
	
}
