package com.easy.cloud.core.reptile.dynamicbean.pojo.entity;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

/**
 * 
 * <p>
 * 爬虫动态bean持久化类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年6月6日 下午3:19:46
 */
public class EcDynamicBeanEntity extends EcBaseEntity {
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
	/** 动态bean名称主体 */
	private String beanNameBody;
	/** beanName后缀 */
	private String beanNameSuffix;
	
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

	public String getBeanNameBody() {
		return beanNameBody;
	}

	public void setBeanNameBody(String beanNameBody) {
		this.beanNameBody = beanNameBody;
	}

	public String getBeanNameSuffix() {
		return beanNameSuffix;
	}

	public void setBeanNameSuffix(String beanNameSuffix) {
		this.beanNameSuffix = beanNameSuffix;
	}

	public Integer getBeanType() {
		return beanType;
	}

	public void setBeanType(Integer beanType) {
		this.beanType = beanType;
	}

}
