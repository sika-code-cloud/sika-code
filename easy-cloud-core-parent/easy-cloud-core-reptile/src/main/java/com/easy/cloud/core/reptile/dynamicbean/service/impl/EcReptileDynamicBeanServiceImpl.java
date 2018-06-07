package com.easy.cloud.core.reptile.dynamicbean.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.reptile.common.constant.EcReptileErrorCodeEnum;
import com.easy.cloud.core.reptile.common.utils.EcReptileUtils;
import com.easy.cloud.core.reptile.datafield.pojo.dto.EcReptileDataFieldDTO;
import com.easy.cloud.core.reptile.datafield.service.EcReptileDataFieldService;
import com.easy.cloud.core.reptile.dynamicbean.dao.EcReptileDynamicBeanDAO;
import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;
import com.easy.cloud.core.reptile.dynamicbean.pojo.entity.EcReptileDynamicBeanEntity;
import com.easy.cloud.core.reptile.dynamicbean.pojo.query.EcReptileDynamicBeanQuery;
import com.easy.cloud.core.reptile.dynamicbean.service.EcReptileDynamicBeanService;
import com.geccocrawler.gecco.GeccoEngine;

/**
 * 描述：服务实现类
 * 
 * @author THINK
 * @date 2018-06-07 17:20:59
 */
@Service(value = "ecReptileDynamicBeanService")
public class EcReptileDynamicBeanServiceImpl extends EcBaseService implements EcReptileDynamicBeanService {
	/** 数据处理接口 */
	@Autowired
	private EcReptileDynamicBeanDAO reptileDynamicBeanDAO;
	@Autowired
	private EcReptileDataFieldService reptileDynamicBeanService;
	/** 导入数据引擎 */
	@Autowired
	private GeccoEngine geccoEngine;

	@Override
	public EcBaseServiceResult saveReptileDynamicBean(EcReptileDynamicBeanDTO dynamicBeanDTO) {
		EcReptileDynamicBeanEntity dynamicBeanEntity = EcJSONUtils.parseObject(dynamicBeanDTO, EcReptileDynamicBeanEntity.class);
		
		dynamicBeanEntity.setBeanNameBody("TlBabuDynamicBean");
		dynamicBeanEntity.setBeanNameSuffix(String.valueOf(EcDateUtils.getCurrentTimeSec()));
		return EcBaseServiceResult.newInstanceOfSucResult(dynamicBeanEntity);
	}
	/**
	 * 
	 * <p>
	 * 将规则class注册到爬虫引擎
	 * </p>
	 *
	 * @param dynamicBeanQuery
	 * @author daiqi
	 * @创建时间 2018年6月7日 下午9:05:11
	 */
	public EcBaseServiceResult registerToGeccoEngine(EcReptileDynamicBeanQuery dynamicBeanQuery) {
		// 1 从数据库中获取dynamicBeanEntity列表
		List<EcReptileDynamicBeanEntity> dynamicBeanEntities = reptileDynamicBeanDAO.listByQuery(dynamicBeanQuery);
		if (EcCollectionsUtils.isEmpty(dynamicBeanEntities)) {
			throw new EcBaseBusinessException(EcReptileErrorCodeEnum.REPTILE_DYNAMIC_BEAN_DATAS_CANT_NULL);
		}
		// 2 根据爬虫动态bean持久化对象列表构建reptileDynamicBeanDTO列表
		List<EcReptileDynamicBeanDTO> dynamicBeanDTOs = buildDynamicBeanDTOs(dynamicBeanEntities);
		// 3 构建获取spiderBeanClazzs列表
		List<Class<?>> spiderBeanClazzs = new ArrayList<>(); 
		for (EcReptileDynamicBeanDTO dynamicBeanDTO : dynamicBeanDTOs) {
			spiderBeanClazzs.add(EcReptileUtils.buildSpiderBeanClass(dynamicBeanDTO));
		}
		// 4 将spiderBeanClazzs注册到爬虫引擎中
		EcReptileUtils.registerToReptileEngine(geccoEngine, spiderBeanClazzs);
		return EcBaseServiceResult.newInstanceOfSuccess();
	}
	
	/**
	 * 
	 * <p>
	 * 根据dynamicBeanEntities列表构建EcReptileDynamicBeanDTO列表
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param dynamicBeanEntities
	 * @return
	 * @author daiqi
	 * @创建时间 2018年6月7日 下午8:59:01
	 */
	private List<EcReptileDynamicBeanDTO> buildDynamicBeanDTOs(List<EcReptileDynamicBeanEntity> dynamicBeanEntities) {
		// 1 entity转换为dto
		List<EcReptileDynamicBeanDTO> dynamicBeanDTOs = EcJSONUtils.parseArray(dynamicBeanEntities, EcReptileDynamicBeanDTO.class);
		// 2 构建dynamicBeanIds
		Set<Long> dynamicBeanIds = new HashSet<>();
		for (EcReptileDynamicBeanEntity dynamicBean : dynamicBeanEntities) {
			dynamicBeanIds.add(dynamicBean.getId());
		}
		// 3 根据dynamicBeanIds获取数据属性数据传输对象列表
		List<EcReptileDataFieldDTO> dataFieldDTOs = reptileDynamicBeanService.findDataFieldsByDynamicBeanIds(dynamicBeanIds);
		if (EcCollectionsUtils.isEmpty(dataFieldDTOs)) {
			throw new EcBaseBusinessException(EcReptileErrorCodeEnum.REPTILE_DATA_FIELD_DATAS_CANT_NULL);
		}
		// 4 将数据属性对象列表放置到对应的dynamicBeanDTO中
		for (EcReptileDynamicBeanDTO dynamicBeanDTO : dynamicBeanDTOs) {
			List<EcReptileDataFieldDTO> dataFieldDTOsTemp = new ArrayList<>();
			for (EcReptileDataFieldDTO dataFieldDTO : dataFieldDTOs) {
				if (EcBaseUtils.equals(dynamicBeanDTO.getId(), dataFieldDTO.getReptileDynamicBeanId())) {
					dataFieldDTOsTemp.add(dataFieldDTO);
				}
			}
			dynamicBeanDTO.setReptileDataFieldDTOs(dataFieldDTOsTemp);
		}
		// 5 返回列表 
		return dynamicBeanDTOs;
	}

	
}
