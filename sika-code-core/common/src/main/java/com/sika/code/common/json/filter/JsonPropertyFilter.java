package com.sika.code.common.json.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.json.pojo.dto.JsonFilterDTO;
import com.sika.code.common.string.util.StringUtil;

import java.util.Map;

/**
 * json属性过滤器--不序列化byte数组以及filterDTOMap中的属性
 * @author daiqi
 * @date 2018年2月28日 下午8:10:33
 */
public class JsonPropertyFilter implements PropertyFilter {
	static {
        //全局配置关闭循环引用检测
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
    }

	private Map<String, JsonFilterDTO> filterDTOMap;

	public JsonPropertyFilter(Map<String, JsonFilterDTO> filterDTOMap) {
		this.filterDTOMap = filterDTOMap;
	}

	@Override
	public boolean apply(Object targetObj, String targetPropertyName, Object targetPropertyValue) {
//		目标对象或者或目标对象的属性值为空直接返回true
		if (BaseUtil.isNull(targetObj) || BaseUtil.isNull(targetPropertyValue)) {
			return true;
		}
		if (targetPropertyValue.getClass().isArray()) {
			return false;
		}
		for (Map.Entry<String, JsonFilterDTO> item : this.filterDTOMap.entrySet()) {
			// isAssignableFrom()，用来判断类型间是否有继承关系
			JsonFilterDTO filterDTO = item.getValue();
			if (targetObj.getClass().isAssignableFrom(filterDTO.getFilterPropertyClass())){
				return false;
			}
			if (!filterDTO.getTargetObjClass().isAssignableFrom(targetObj.getClass())){
				continue;
			}
//			过滤属性class为空--校验属性名称
			if (BaseUtil.isNull(filterDTO.getFilterPropertyClass())){
//				属性名称为空直接返回
				if (StringUtil.isEmpty(filterDTO.getTargetObjPropertyName())){
					continue;
				}
//				属性相等,不参与json序列号
				if (StringUtil.equalsIgnoreCase(filterDTO.getTargetObjPropertyName(), targetPropertyName)){
					return false;
				}
			}
			if (filterDTO.getFilterPropertyClass().isAssignableFrom(targetPropertyValue.getClass())){
				return false;
			}
		}
		return true;
	}

	public JsonPropertyFilter buildFilterDTOMap(Map<String, JsonFilterDTO> filterDTOMap) {
		this.filterDTOMap = filterDTOMap;
		return this;
	}


}
