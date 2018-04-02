package com.dq.easy.cloud.module.common.generator.code.xml.desc;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlBaseDTO;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * 
 * <p>
 * xml内容元素描述类
 * </p>
 *
 * @author daiqi 创建时间 2018年3月29日 上午10:26:27
 */
public class DqXmlContentElementDesc extends DqXmlContentBaseDesc {
	/** 子节点元素描述列表 */
	List<DqXmlContentElementDesc> childrenElements;
	/** 元素元素名称 */
	private String elementName;
	/** xml元素属性列表 */
	private List<DqXmlContentElementAttributeDesc> attributes;
	/** xml元素的text */
	private String text;
	
	public DqXmlContentElementDesc() {
		super();
	}

	public DqXmlContentElementDesc(DqGenerateRule generateRule) {
		super(generateRule);
	}

	public DqXmlContentElementDesc(DqGenerateXmlBaseDTO generateXmlBaseDTO) {
		super(generateXmlBaseDTO);
	}

	public DqXmlContentElementDesc addAttribute(String key, String value) {
		if (DqStringUtils.isEmpty(key) || DqStringUtils.isEmpty(value)) {
			return this;
		}
		if (DqCollectionsUtils.isEmpty(this.attributes)) {
			this.attributes = new ArrayList<>();
		}
		attributes.add(new DqXmlContentElementAttributeDesc(key, value));
		return this;
	}
	
	public DqXmlContentElementDesc addAttribute(DqXmlContentElementAttributeDesc attributeDesc) {
		if (DqCollectionsUtils.isEmpty(this.attributes)) {
			this.attributes = new ArrayList<>();
		}
		attributes.add(attributeDesc);
		return this;
	}
	
	public DqXmlContentElementDesc addAllAttribute(List<DqXmlContentElementAttributeDesc> attributeDescs) {
		if (DqCollectionsUtils.isEmpty(this.attributes)) {
			this.attributes = new ArrayList<>();
		}
		attributes.addAll(attributeDescs);
		return this;
	}
	public DqXmlContentElementDesc addElement(DqXmlContentElementDesc chidren) {
		if (DqCollectionsUtils.isEmpty(this.childrenElements)) {
			this.childrenElements = new ArrayList<>();
		}
		childrenElements.add(chidren);
		return this;
	}

	public DqXmlContentElementDesc addAllElement(List<DqXmlContentElementDesc> chidrens) {
		if (DqCollectionsUtils.isEmpty(this.childrenElements)) {
			this.childrenElements = new ArrayList<>();
		}
		childrenElements.addAll(chidrens);
		return this;
	}

	public String getAttributesStr() {
		if (DqCollectionsUtils.isEmpty(attributes)) {
			return null;
		}
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		for (DqXmlContentElementAttributeDesc attributeDesc : attributes) {
			sb.append(attributeDesc.getKey()).append(DqSymbol.EQUAL).append(DqSymbol.DOUBLE_QUOTES);
			sb.append(attributeDesc.getValue()).append(DqSymbol.DOUBLE_QUOTES).append(DqSymbol.EMPTY);
		}
		return sb.toString();
	}
	

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

	public List<DqXmlContentElementDesc> getChildrenElements() {
		return childrenElements;
	}

	public void setChildrenElements(List<DqXmlContentElementDesc> childrenElements) {
		this.childrenElements = childrenElements;
	}
}
