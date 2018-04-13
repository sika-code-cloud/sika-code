package com.easy.cloud.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.common.json.utils.EcJSONUtils;

@RestController
@RequestMapping("test")
public class ConfigTest {
	@Value(value="${from}")
	private String from;
	
	/**
	 * 测试分布式配置中心是否有效
	 * @return
	 */
	@RequestMapping("/getFrom")
	public String getFrom(){
		Map<String, Object> retMap = new HashMap<>();
		retMap.put("from", from);
		return EcJSONUtils.parseObject(retMap, String.class);
	}
}
