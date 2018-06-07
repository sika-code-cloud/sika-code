package com.easy.cloud.core.reptile.datafield.service;

import java.util.List;
import java.util.Set;

import com.easy.cloud.core.reptile.datafield.pojo.dto.EcReptileDataFieldDTO;

/**
 * 描述：服务接口
 * 
 * @author THINK
 * @date 2018-06-07 17:02:14
 */
public interface EcReptileDataFieldService {
	List<EcReptileDataFieldDTO> findDataFieldsByDynamicBeanIds(Set<Long> dynamicBeanIds);
}
