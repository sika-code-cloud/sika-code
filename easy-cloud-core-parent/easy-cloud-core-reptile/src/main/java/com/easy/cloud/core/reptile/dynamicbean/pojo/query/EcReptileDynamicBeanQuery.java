package com.easy.cloud.core.reptile.dynamicbean.pojo.query;

import com.easy.cloud.core.basic.pojo.query.EcBaseQuery;

/**
 * 描述：查询类
 * 
 * @author daiqi
 * @date 2018-06-07 17:20:59
 */
public class EcReptileDynamicBeanQuery extends EcBaseQuery {
	private Integer beanType;
	private Integer dynamicBeanNo;

	public Integer getBeanType() {
		return beanType;
	}

	public void setBeanType(Integer beanType) {
		this.beanType = beanType;
	}

	public Integer getDynamicBeanNo() {
		return dynamicBeanNo;
	}

	public void setDynamicBeanNo(Integer dynamicBeanNo) {
		this.dynamicBeanNo = dynamicBeanNo;
	}

}
