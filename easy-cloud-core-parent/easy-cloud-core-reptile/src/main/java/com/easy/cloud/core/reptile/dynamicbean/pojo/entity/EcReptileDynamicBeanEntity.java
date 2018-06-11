package com.easy.cloud.core.reptile.dynamicbean.pojo.entity;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

/**
 * 描述：爬虫动态bean配置表持久化类
 * 
 * @author daiqi
 * @date 2018-06-07 21:08:48
 */
public class EcReptileDynamicBeanEntity extends EcBaseEntity {
	/** 爬虫引擎编号 */
	private Integer reptileEngineNo;
	/** 爬虫动态bean的类型 */
	private Integer beanType;
	/** 模糊匹配的{}可以匹配任意非空白字符串但是不包含斜杠(/) json数组的格式 详情参考gecco */
	private String matchUrls;
	/** 匹配的url类型 */
	private Integer matchUrlType;
	/** 动态bean的编号 */
	private Integer dynamicBeanNo;
	/** 动态bean的父编号 */
	private String dynamicBeanParentNo;
	/** 处理数据的管道名称 */
	private String pipelineNames;
	/** 动态bean名称主体 */
	private String beanNameBody;
	/** beanName后缀 */
	private String beanNameSuffix;
		
	
	public Integer getReptileEngineNo() {
		return reptileEngineNo;
	}

	public void setReptileEngineNo(Integer reptileEngineNo) {
		this.reptileEngineNo = reptileEngineNo;
	}

	/** 获取爬虫动态bean的类型 */
	public Integer getBeanType() {
		return this.beanType;
	}

	/** 设置爬虫动态bean的类型 */
	public void setBeanType(Integer beanType) {
		this.beanType = beanType;
	}

	/** 获取模糊匹配的{}可以匹配任意非空白字符串但是不包含斜杠(/) json数组的格式 详情参考gecco */
	public String getMatchUrls() {
		return this.matchUrls;
	}

	/** 设置模糊匹配的{}可以匹配任意非空白字符串但是不包含斜杠(/) json数组的格式 详情参考gecco */
	public void setMatchUrls(String matchUrls) {
		this.matchUrls = matchUrls;
	}

	/** 获取匹配的url类型 */
	public Integer getMatchUrlType() {
		return this.matchUrlType;
	}

	/** 设置匹配的url类型 */
	public void setMatchUrlType(Integer matchUrlType) {
		this.matchUrlType = matchUrlType;
	}

	/** 获取动态bean的编号 */
	public Integer getDynamicBeanNo() {
		return this.dynamicBeanNo;
	}

	/** 设置动态bean的编号 */
	public void setDynamicBeanNo(Integer dynamicBeanNo) {
		this.dynamicBeanNo = dynamicBeanNo;
	}

	/** 获取动态bean的父编号 */
	public String getDynamicBeanParentNo() {
		return this.dynamicBeanParentNo;
	}

	/** 设置动态bean的父编号 */
	public void setDynamicBeanParentNo(String dynamicBeanParentNo) {
		this.dynamicBeanParentNo = dynamicBeanParentNo;
	}

	/** 获取处理数据的管道名称 */
	public String getPipelineNames() {
		return this.pipelineNames;
	}

	/** 设置处理数据的管道名称 */
	public void setPipelineNames(String pipelineNames) {
		this.pipelineNames = pipelineNames;
	}

	/** 获取动态bean名称主体 */
	public String getBeanNameBody() {
		return this.beanNameBody;
	}

	/** 设置动态bean名称主体 */
	public void setBeanNameBody(String beanNameBody) {
		this.beanNameBody = beanNameBody;
	}

	/** 获取beanName后缀 */
	public String getBeanNameSuffix() {
		return this.beanNameSuffix;
	}

	/** 设置beanName后缀 */
	public void setBeanNameSuffix(String beanNameSuffix) {
		this.beanNameSuffix = beanNameSuffix;
	}

}
