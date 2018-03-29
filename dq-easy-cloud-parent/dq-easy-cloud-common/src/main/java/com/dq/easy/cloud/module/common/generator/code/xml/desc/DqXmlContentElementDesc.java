package com.dq.easy.cloud.module.common.generator.code.xml.desc;

import java.util.List;

import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;

/**
 * 
 * <p>
 * xml内容元素描述类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月29日 上午10:26:27
 */
public class DqXmlContentElementDesc extends DqXmlContentBaseDesc{
	/** 子节点元素描述列表 */
	List<DqXmlContentElementDesc> contentElementDescs;
	/** 元素元素名称 */
	private String elementName;
	/** xml元素属性列表 */
	private List<DqXmlContentElementAttributeDesc> attributes;
	/** xml元素的text */
	private String text;
	
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public List<DqXmlContentElementAttributeDesc> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<DqXmlContentElementAttributeDesc> attributes) {
		this.attributes = attributes;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<DqXmlContentElementDesc> getContentElementDescs() {
		return contentElementDescs;
	}
	public void setContentElementDescs(List<DqXmlContentElementDesc> contentElementDescs) {
		this.contentElementDescs = contentElementDescs;
	}
	
	@Override
	public DqFileContentBaseDesc buildDataByDatabaseSources(DqDatabaseDataSources databaseDataSources) {
		return super.buildDataByDatabaseSources(databaseDataSources);
	}
	
}
