package com.easy.cloud.core.reptile.engine.pojo.query;

import com.easy.cloud.core.basic.pojo.query.EcBaseQuery;
import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;

/**
 * 描述：查询类
 * 
 * @author daiqi
 * @date 2018-06-11 10:59:59
 */
public class EcReptileEngineQuery extends EcBaseQuery {
	private Integer reptileEngineNo;

	private EcReptileDynamicBeanDTO reptileDynamicBeanDTO;

	public EcReptileDynamicBeanDTO getReptileDynamicBeanDTO() {
		return reptileDynamicBeanDTO;
	}

	public void setReptileDynamicBeanDTO(EcReptileDynamicBeanDTO reptileDynamicBeanDTO) {
		this.reptileDynamicBeanDTO = reptileDynamicBeanDTO;
	}

	public Integer getReptileEngineNo() {
		return reptileEngineNo;
	}

	public void setReptileEngineNo(Integer reptileEngineNo) {
		this.reptileEngineNo = reptileEngineNo;
	}

}
