package com.easy.cloud.core.reptile.datafield.service;

import java.util.List;
import java.util.Set;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.reptile.datafield.pojo.dto.EcReptileDataFieldDTO;

/**
 * 描述：服务接口
 * 
 * @author THINK
 * @date 2018-06-07 17:02:14
 */
public interface EcReptileDataFieldService {

	/** 保存 */
	EcBaseServiceResult saveReptileDataField(EcReptileDataFieldDTO dataFieldDTO);

	/** 更新 */
	EcBaseServiceResult updateReptileDataField(EcReptileDataFieldDTO dataFieldDTO);

	/**
	 * 
	 * <p>
	 * 根据动态beanid列表获取数据属性列表信息
	 * </p>
	 *
	 * @param dynamicBeanNos
	 * @return
	 * @author daiqi
	 * @创建时间 2018年6月8日 上午10:58:00
	 */
	List<EcReptileDataFieldDTO> findDataFieldsByDynamicBeanNos(Set<Integer> dynamicBeanNos);
}
