package com.easy.cloud.core.common.generator.code.xml.desc;

/**
 * 
 * <p>
 * xml元素属性内容描述类
 * </p>
 *
 * @author daiqi 创建时间 2018年3月29日 上午10:03:20
 */
public class EcXmlContentElementAttributeDesc extends EcXmlContentBaseDesc {

	private String key;
	private String value;

	public EcXmlContentElementAttributeDesc() {

	}

	public EcXmlContentElementAttributeDesc(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
