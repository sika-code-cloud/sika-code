package com.easy.cloud.core.common.generator.code.xml.desc;

import java.util.ArrayList;
import java.util.List;

import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.xml.pojo.dto.EcGenerateXmlBaseDTO;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 
 * <p>
 * xml内容元素描述类
 * </p>
 *
 * @author daiqi 创建时间 2018年3月29日 上午10:26:27
 */
public class EcXmlContentElementDesc extends EcXmlContentBaseDesc {
	/** 子节点元素描述列表 */
	List<EcXmlContentElementDesc> childrenElements;
	/** 元素元素名称 */
	private String elementName;
	/** xml元素属性列表 */
	private List<EcXmlContentElementAttributeDesc> attributes;
	/** xml元素的text */
	private String text;
	
	public EcXmlContentElementDesc() {
		super();
	}

	public EcXmlContentElementDesc(EcGenerateRule generateRule) {
		super(generateRule);
	}

	public EcXmlContentElementDesc(EcGenerateXmlBaseDTO generateXmlBaseDTO) {
		super(generateXmlBaseDTO);
	}

	public EcXmlContentElementDesc addAttribute(String key, String value) {
		if (EcStringUtils.isEmpty(key) || EcStringUtils.isEmpty(value)) {
			return this;
		}
		if (EcCollectionsUtils.isEmpty(this.attributes)) {
			this.attributes = new ArrayList<>();
		}
		attributes.add(new EcXmlContentElementAttributeDesc(key, value));
		return this;
	}
	
	public EcXmlContentElementDesc addAttribute(EcXmlContentElementAttributeDesc attributeDesc) {
		if (EcCollectionsUtils.isEmpty(this.attributes)) {
			this.attributes = new ArrayList<>();
		}
		attributes.add(attributeDesc);
		return this;
	}
	
	public EcXmlContentElementDesc addAllAttribute(List<EcXmlContentElementAttributeDesc> attributeDescs) {
		if (EcCollectionsUtils.isEmpty(this.attributes)) {
			this.attributes = new ArrayList<>();
		}
		attributes.addAll(attributeDescs);
		return this;
	}
	public EcXmlContentElementDesc addElement(EcXmlContentElementDesc chidren) {
		if (EcCollectionsUtils.isEmpty(this.childrenElements)) {
			this.childrenElements = new ArrayList<>();
		}
		childrenElements.add(chidren);
		return this;
	}

	public EcXmlContentElementDesc addAllElement(List<EcXmlContentElementDesc> chidrens) {
		if (EcCollectionsUtils.isEmpty(this.childrenElements)) {
			this.childrenElements = new ArrayList<>();
		}
		childrenElements.addAll(chidrens);
		return this;
	}

	public String getAttributesStr() {
		if (EcCollectionsUtils.isEmpty(attributes)) {
			return null;
		}
		StringBuilder sb = EcStringUtils.newStringBuilder();
		for (EcXmlContentElementAttributeDesc attributeDesc : attributes) {
			sb.append(attributeDesc.getKey()).append(EcSymbol.EQUAL).append(EcSymbol.DOUBLE_QUOTES);
			sb.append(attributeDesc.getValue()).append(EcSymbol.DOUBLE_QUOTES).append(EcSymbol.SPACE);
		}
		return sb.toString();
	}
	

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public List<EcXmlContentElementAttributeDesc> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<EcXmlContentElementAttributeDesc> attributes) {
		this.attributes = attributes;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<EcXmlContentElementDesc> getChildrenElements() {
		return childrenElements;
	}

	public void setChildrenElements(List<EcXmlContentElementDesc> childrenElements) {
		this.childrenElements = childrenElements;
	}
}
