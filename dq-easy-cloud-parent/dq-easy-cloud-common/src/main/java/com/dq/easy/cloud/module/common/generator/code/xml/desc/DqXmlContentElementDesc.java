package com.dq.easy.cloud.module.common.generator.code.xml.desc;

import java.util.List;

/**
 * 
 * <p>
 * xml内容元素描述类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月29日 上午10:26:27
 */
public class DqXmlContentElementDesc {
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
}
