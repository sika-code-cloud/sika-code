package com.dq.easy.cloud.model.common.json.config;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.common.json.constant.DqJsonConstant.DqJsonConfigKey;
import com.dq.easy.cloud.model.common.json.pojo.dto.DqJsonFilterDTO;
import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogDTO;

/**
 * 
 * <p>
 * json配置类--子类可以继承此类进行配置不需要json序列化的过滤规则
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月28日 下午3:31:35
 */
@Component
public class DqJsonConfig {
	/** json过滤数据传输对象得map集合 */
	private static Map<String, DqJsonFilterDTO>  JSON_FILTER_DTO_MAP = new HashMap<>();
		
	static {
		setJsonFilterDTOMap(new DqJsonFilterDTO(DqJsonConfigKey.FILTER_BUFFER_IMAGE_KEY, DqLogDTO.class, "targetReturnValue", BufferedImage.class));
	}
	
	private static void setJsonFilterDTOMap(DqJsonFilterDTO dqJsonFilterDTO){
		JSON_FILTER_DTO_MAP.put(dqJsonFilterDTO.getKey(), dqJsonFilterDTO);
	}
	
	public static boolean isCantBeSerializedClass(Class<?> clazz){
		return JSON_FILTER_DTO_MAP.get(clazz) == null ? false : true;
	}
	
	public static Map<String, DqJsonFilterDTO> getJsonFilterDTOMap() {
		return JSON_FILTER_DTO_MAP;
	}
}

