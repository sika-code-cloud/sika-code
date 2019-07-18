package com.sika.code.common.json.config;

import com.sika.code.common.json.pojo.dto.JsonFilterDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
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
public class JsonConfig {
    /**
     * json过滤数据传输对象得map集合
     */
    private static Map<String, JsonFilterDTO> JSON_FILTER_DTO_MAP = new HashMap<>();

    static {
    }

    protected static void setJsonFilterDTOMap(JsonFilterDTO ecJsonFilterDTO) {
        JSON_FILTER_DTO_MAP.put(ecJsonFilterDTO.getKey(), ecJsonFilterDTO);
    }

    public static Map<String, JsonFilterDTO> getJsonFilterDTOMap() {
        return JSON_FILTER_DTO_MAP;
    }
}

