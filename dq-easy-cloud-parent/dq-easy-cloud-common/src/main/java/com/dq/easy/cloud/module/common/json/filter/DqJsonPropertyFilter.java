package com.dq.easy.cloud.module.common.json.filter;

import java.util.Map;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.json.pojo.dto.DqJsonFilterDTO;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * json属性过滤器--不序列化byte数组以及filterDTOMap中的属性
 * @author daiqi
 * @date 2018年2月28日 下午8:10:33
 */
public class DqJsonPropertyFilter implements PropertyFilter{
	
	private Map<String, DqJsonFilterDTO> filterDTOMap;

	public DqJsonPropertyFilter(Map<String, DqJsonFilterDTO> filterDTOMap) {
		this.filterDTOMap = filterDTOMap;
	}

	@Override
	public boolean apply(Object targetObj, String targetPropertyName, Object targetPropertyValue) {
//		目标对象或者或目标对象的属性值为空直接返回true
		if (DqBaseUtils.isNull(targetObj) || DqBaseUtils.isNull(targetPropertyValue)) {
			return true;
		}
		if (targetPropertyValue.getClass().isArray()) {
			return false;
		}
		for (Map.Entry<String, DqJsonFilterDTO> item : this.filterDTOMap.entrySet()) {
			// isAssignableFrom()，用来判断类型间是否有继承关系
			DqJsonFilterDTO filterDTO = item.getValue();
			if (targetObj.getClass().isAssignableFrom(filterDTO.getFilterPropertyClass())){
				return false;
			}
			if (!filterDTO.getTargetObjClass().isAssignableFrom(targetObj.getClass())){
				continue;
			}
//			过滤属性class为空--校验属性名称
			if (DqBaseUtils.isNull(filterDTO.getFilterPropertyClass())){
//				属性名称为空直接返回
				if (DqStringUtils.isEmpty(filterDTO.getTargetObjPropertyName())){
					continue;
				}
//				属性相等,不参与json序列号
				if (DqStringUtils.equalsIgnoreCase(filterDTO.getTargetObjPropertyName(), targetPropertyName)){
					return false;
				}
			}
			if (filterDTO.getFilterPropertyClass().isAssignableFrom(targetPropertyValue.getClass())){
				return false;
			}
		}
		return true;
	}

	public DqJsonPropertyFilter buildFilterDTOMap(Map<String, DqJsonFilterDTO> filterDTOMap) {
		this.filterDTOMap = filterDTOMap;
		return this;
	}


}
