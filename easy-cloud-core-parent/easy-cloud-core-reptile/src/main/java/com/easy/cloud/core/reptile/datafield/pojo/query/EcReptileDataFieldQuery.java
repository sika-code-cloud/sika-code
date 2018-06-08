package com.easy.cloud.core.reptile.datafield.pojo.query;

import java.util.Set;

import com.easy.cloud.core.basic.pojo.query.EcBaseQuery;

/**
 * 描述：查询类
 * 
 * @author THINK
 * @date 2018-06-07 17:02:14
 */
public class EcReptileDataFieldQuery extends EcBaseQuery {
	private Set<Integer> dynamicBeanNos;

	public Set<Integer> getDynamicBeanNos() {
		return dynamicBeanNos;
	}

	public void setDynamicBeanNos(Set<Integer> dynamicBeanNos) {
		this.dynamicBeanNos = dynamicBeanNos;
	}



}
