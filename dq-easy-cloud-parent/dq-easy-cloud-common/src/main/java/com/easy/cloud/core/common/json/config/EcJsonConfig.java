package com.dq.easy.cloud.module.common.json.config;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.dq.easy.cloud.module.common.json.constant.DqJsonConstant.DqJsonConfigKey;
import com.dq.easy.cloud.module.common.json.pojo.dto.DqJsonFilterDTO;
import com.dq.easy.cloud.module.common.log.pojo.dto.DqLogDTO;

/**
 * 
 * <p>
 * json基础配置类
 * </p>
 *
 * <pre>
 *  说明：所有子服务都应该使用@Component注解进行注入
 *  约定：所有子服务的日志配置类都应该继承该类,配置不需要json序列化的过滤规则
 *  命名规范：UserLogConfig
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间     2018年2月28日 下午3:31:35
 */
@Component
public class DqJsonConfig {
	/** json过滤数据传输对象得map集合 */
	private static Map<String, DqJsonFilterDTO>  JSON_FILTER_DTO_MAP = new HashMap<>();
		
	static {
		setJsonFilterDTOMap(new DqJsonFilterDTO(DqJsonConfigKey.FILTER_BUFFER_IMAGE_KEY, DqLogDTO.class, "targetReturnValue", BufferedImage.class));
	}
	
	protected static void setJsonFilterDTOMap(DqJsonFilterDTO dqJsonFilterDTO){
		JSON_FILTER_DTO_MAP.put(dqJsonFilterDTO.getKey(), dqJsonFilterDTO);
	}
	
	public static Map<String, DqJsonFilterDTO> getJsonFilterDTOMap() {
		return JSON_FILTER_DTO_MAP;
	}
}

