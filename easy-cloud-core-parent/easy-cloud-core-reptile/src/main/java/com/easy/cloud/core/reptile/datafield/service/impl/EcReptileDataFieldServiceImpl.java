package com.easy.cloud.core.reptile.datafield.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.reptile.datafield.dao.EcReptileDataFieldDAO;
import com.easy.cloud.core.reptile.datafield.pojo.dto.EcReptileDataFieldDTO;
import com.easy.cloud.core.reptile.datafield.service.EcReptileDataFieldService;

/**
 * 描述：服务实现类
 * 
 * @author THINK
 * @date 2018-06-07 17:02:14
 */
@Service(value = "ecReptileDataFieldService")
public class EcReptileDataFieldServiceImpl extends EcBaseService implements EcReptileDataFieldService {
	/** 数据处理接口 */
	@Autowired
	private EcReptileDataFieldDAO reptileDataFieldDAO;

	@Override
	public List<EcReptileDataFieldDTO> findDataFieldsByDynamicBeanIds(Set<Long> dynamicBeanIds) {
		return null;
	}
		
		
}
