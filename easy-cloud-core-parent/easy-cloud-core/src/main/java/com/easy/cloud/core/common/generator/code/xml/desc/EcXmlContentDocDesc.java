package com.easy.cloud.core.common.generator.code.xml.desc;

/**
 * 
 * <p>
 * xml内容文档描述类
 * </p>
 *
 * <pre>
 *  说明：所有xml内容描述文件的基础父类
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年3月28日 上午10:49:49
 */
public class EcXmlContentDocDesc extends EcXmlContentBaseDesc{
	/** xml的申明 */
	private String statement;
	/** 文档类型 */
	private String docType;
	/** 跟内容元素描述 */
	private EcXmlContentElementDesc rootElement;
	
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public EcXmlContentElementDesc getRootElement() {
		return rootElement;
	}
	public void setRootElement(EcXmlContentElementDesc rootElement) {
		this.rootElement = rootElement;
	}

}
