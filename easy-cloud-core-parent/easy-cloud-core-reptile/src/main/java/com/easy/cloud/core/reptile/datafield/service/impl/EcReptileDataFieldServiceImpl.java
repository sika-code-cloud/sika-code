package com.easy.cloud.core.reptile.datafield.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.reptile.datafield.dao.EcReptileDataFieldDAO;
import com.easy.cloud.core.reptile.datafield.pojo.dto.EcReptileDataFieldDTO;
import com.easy.cloud.core.reptile.datafield.pojo.entity.EcReptileDataFieldEntity;
import com.easy.cloud.core.reptile.datafield.pojo.query.EcReptileDataFieldQuery;
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
	public EcBaseServiceResult saveReptileDataField(EcReptileDataFieldDTO dataFieldDTO) {
		EcReptileDataFieldEntity dataFieldEntity = EcJSONUtils.parseObject(dataFieldDTO, EcReptileDataFieldEntity.class);
		reptileDataFieldDAO.save(dataFieldEntity);
		return EcBaseServiceResult.newInstanceOfSucResult(dataFieldEntity);
	}


	@Override
	public EcBaseServiceResult updateReptileDataField(EcReptileDataFieldDTO dataFieldDTO) {
		EcReptileDataFieldEntity dataFieldEntity = reptileDataFieldDAO.findById(dataFieldDTO.getId());
		dataFieldEntity.setAvailable(dataFieldDTO.getAvailable());
		reptileDataFieldDAO.save(dataFieldEntity);
		return EcBaseServiceResult.newInstanceOfSucResult(dataFieldEntity);
	}


	@Override
	public List<EcReptileDataFieldDTO> findDataFieldsByDynamicBeanNos(Set<Integer> dynamicBeanNos) {
		EcReptileDataFieldQuery reptileDataFieldQuery = new EcReptileDataFieldQuery();
		reptileDataFieldQuery.setDynamicBeanNos(dynamicBeanNos);
		List<EcReptileDataFieldEntity> dataFieldEntities = reptileDataFieldDAO.listByQuery(reptileDataFieldQuery);
		return EcJSONUtils.parseArray(dataFieldEntities, EcReptileDataFieldDTO.class);
	}
}
