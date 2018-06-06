package com.easy.cloud.core.reptile.dynamicbean.pojo.dto;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;

public class EcDynamicBeanDTO extends EcBaseDTO {
	private static final long serialVersionUID = 645249019204333113L;

	/** 匹配的url 不一定是精确的url */
	private String matchUrl;
	/** 匹配的url类型 */
	private Integer matchUrlType;
	/** 动态bean的编号 */
	private Integer dynamicBeanNo;
	/** 动态bean的父编号 */
	private Integer dynamicBeanParentNo;
	/** 处理数据的管道名称 */
	private String pipelineName;
	/** 动态bean名称 建议自己指定需要保证唯一生成算法采用beanName主体加随机值 */
	private String beanName;
	/** 动态bean类型 1:htmlBean 2:jsonBean */
	private Integer beanType;

	public String getMatchUrl() {
		return matchUrl;
	}

	public void setMatchUrl(String matchUrl) {
		this.matchUrl = matchUrl;
	}

	public Integer getMatchUrlType() {
		return matchUrlType;
	}

	public void setMatchUrlType(Integer matchUrlType) {
		this.matchUrlType = matchUrlType;
	}

	public Integer getDynamicBeanNo() {
		return dynamicBeanNo;
	}

	public void setDynamicBeanNo(Integer dynamicBeanNo) {
		this.dynamicBeanNo = dynamicBeanNo;
	}

	public Integer getDynamicBeanParentNo() {
		return dynamicBeanParentNo;
	}

	public void setDynamicBeanParentNo(Integer dynamicBeanParentNo) {
		this.dynamicBeanParentNo = dynamicBeanParentNo;
	}

	public String getPipelineName() {
		return pipelineName;
	}

	public void setPipelineName(String pipelineName) {
		this.pipelineName = pipelineName;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public Integer getBeanType() {
		return beanType;
	}

	public void setBeanType(Integer beanType) {
		this.beanType = beanType;
	}
}
