package com.easy.cloud.core.reptile.dynamicbean.pojo.vo;

import com.easy.cloud.core.basic.pojo.vo.EcBaseVO;

/**
 * 描述：爬虫动态bean配置表视图类
 * 
 * @author daiqi
 * @date 2018-06-07 18:09:16
 */
public class EcReptileDynamicBeanVO extends EcBaseVO {
	/** 模糊匹配的{}可以匹配任意非空白字符串但是不包含斜杠(/) 详情参考gecco */
	private String matchUrl;
	/** 匹配的url类型 */
	private Integer matchUrlType;
	/** 动态bean的编号 */
	private Integer dynamicBeanNo;
	/** 动态bean的父编号 */
	private String dynamicBeanParentNo;
	/** 处理数据的管道名称 */
	private String pipelineNames;
	/** 动态bean名称主体 */
	private Integer beanNameBody;
	/** beanName后缀 */
	private String beanNameSuffix;
		
	/** 获取模糊匹配的{}可以匹配任意非空白字符串但是不包含斜杠(/) 详情参考gecco */
	public String getMatchUrl() {
		return this.matchUrl;
	}

	/** 设置模糊匹配的{}可以匹配任意非空白字符串但是不包含斜杠(/) 详情参考gecco */
	public void setMatchUrl(String matchUrl) {
		this.matchUrl = matchUrl;
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
	public Integer getBeanNameBody() {
		return this.beanNameBody;
	}

	/** 设置动态bean名称主体 */
	public void setBeanNameBody(Integer beanNameBody) {
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
