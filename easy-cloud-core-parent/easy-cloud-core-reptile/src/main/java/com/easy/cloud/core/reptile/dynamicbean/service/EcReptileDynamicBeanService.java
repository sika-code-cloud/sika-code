package com.easy.cloud.core.reptile.dynamicbean.service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;
import com.easy.cloud.core.reptile.dynamicbean.pojo.query.EcReptileDynamicBeanQuery;

/**
 * 描述：服务接口
 * 
 * @author THINK
 * @date 2018-06-07 17:20:59
 */
public interface EcReptileDynamicBeanService {
	
	EcBaseServiceResult saveReptileDynamicBean(EcReptileDynamicBeanDTO dynamicBeanDTO);
	
	EcBaseServiceResult registerToGeccoEngine(EcReptileDynamicBeanQuery dynamicBeanQuery);
}
