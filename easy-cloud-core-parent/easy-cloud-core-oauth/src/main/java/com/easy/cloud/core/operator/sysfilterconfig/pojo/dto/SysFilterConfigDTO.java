package com.easy.cloud.core.operator.sysfilterconfig.pojo.dto;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;

/**
 * 描述：过滤配置信息表数据传输类
 * 
 * @author THINK
 * @date 2018-06-25 16:36:55
 */
public class SysFilterConfigDTO extends EcBaseDTO {
	/** url的匹配模式 */
	private String urlPattern;
	/** 过滤器的名称 */
	private String filterName;
	/** 从1 开始 序号越大越靠前 */
	private Integer orderNum;
		
	/** 获取url的匹配模式 */
	public String getUrlPattern() {
		return this.urlPattern;
	}

	/** 设置url的匹配模式 */
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	/** 获取过滤器的名称 */
	public String getFilterName() {
		return this.filterName;
	}

	/** 设置过滤器的名称 */
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	/** 获取从1 开始 序号越大越靠前 */
	public Integer getOrderNum() {
		return this.orderNum;
	}

	/** 设置从1 开始 序号越大越靠前 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

}
